# Arsitektur MVI (Model-View-Intent)

Dokumen ini menjelaskan implementasi pola MVI di lapisan presentasi menggunakan Jetpack Compose dan Kotlin Flow.

## 1. Komponen MVI

### A. State (Model)
Satu-satunya sumber kebenaran (Single Source of Truth) untuk UI. Bersifat **Immutable**.
```kotlin
data class HomeState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null
) : UiState
```

### B. Intent (User Action)
Mempresentasikan keinginan pengguna.
```kotlin
sealed class HomeIntent : UiIntent {
    object Refresh : HomeIntent()
    data class Search(val query: String) : HomeIntent()
}
```

### C. Effect (Side Effect)
Kejadian satu kali (one-time event) seperti navigasi atau menampilkan Toast.
```kotlin
sealed class HomeEffect : UiEffect {
    data class NavigateToDetail(val url: String) : HomeEffect()
    data class ShowError(val message: String) : HomeEffect()
}
```

---

## 2. Alur Data (Unidirectional Data Flow)
1. **User** melakukan aksi (klik tombol) -> **Intent**.
2. **ViewModel** menerima Intent -> Proses ke Repository/UseCase.
3. **ViewModel** memperbarui **State** secara atomik.
4. **Compose** mengamati State -> **Recomposition** UI.

---

## 3. Contoh Implementasi ViewModel
```kotlin
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetTopHeadlinesUseCase
) : BaseViewModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Refresh -> loadNews()
            is HomeIntent.Search -> searchNews(intent.query)
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            getNewsUseCase().collect { result ->
                when (result) {
                    is ResultState.Success -> setState { 
                        copy(isLoading = false, articles = result.data) 
                    }
                    is ResultState.Error -> {
                        setState { copy(isLoading = false) }
                        sendEffect(HomeEffect.ShowError(result.message))
                    }
                }
            }
        }
    }
}
```

---

## 4. Keuntungan MVI
- **Predictability:** State aplikasi mudah diprediksi karena hanya berubah di satu tempat.
- **Consistency:** Menghindari masalah UI yang "stuck" karena state yang saling bertabrakan.
- **Debugging:** Mudah melacak urutan Intent yang menyebabkan error.

---

## 5. Kesimpulan
MVI adalah pilihan terbaik untuk aplikasi berbasis Compose karena sinkronisasi state yang ketat, memastikan pengalaman pengguna yang mulus tanpa *flicker* atau data yang tidak konsisten.
