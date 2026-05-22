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
1. **User Action**: User melakukan aksi (klik tombol) -> **Intent**.
2. **ViewModel Processing**: **ViewModel** menerima Intent -> Proses ke Repository/UseCase.
3. **State Update**: **ViewModel** memperbarui **State** secara atomik menggunakan `setState`.
4. **Effect Processing**: Jika diperlukan aksi satu kali, ViewModel memanggil `sendEffect`.
5. **UI Recomposition**: **Compose** mengamati State via `collectAsStateWithLifecycle()` -> **Recomposition** UI.

## 3. Implementasi Teknis (BaseViewModel)
Semua ViewModel dalam proyek ini mewarisi `BaseViewModel` dari `:core:architecture` untuk standarisasi boilerplate.

```kotlin
abstract class BaseViewModel<S : UiState, I : UiIntent, E : UiEffect>(
    initialState: S
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effect = Channel<E>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    abstract fun onIntent(intent: I)

    protected fun setState(reduce: S.() -> S) {
        _state.update { it.reduce() }
    }

    protected fun sendEffect(effect: E) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
```

### Contoh Implementasi ViewModel
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
    // ... loadNews implementation ...
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
