# MVI & Data Flow

Proyek ini menggunakan pola **MVI (Model-View-Intent)** dengan **Unidirectional Data Flow (UDF)** untuk memastikan konsistensi state UI.

## 1. Komponen Utama MVI

### State (`UiState`)
Objek tunggal (data class) yang mewakili kondisi layar secara utuh.
- **Immutable**: Hanya bisa diupdate dengan membuat copy baru.
- **Example**: `HomeState(isLoading = true, articles = emptyList())`.

### Intent (`UiIntent`)
Mewakili aksi atau niat pengguna.
- **Example**: `HomeIntent.SearchArticle(query)`, `HomeIntent.Refresh`.

### Effect (`UiEffect`)
Peristiwa satu kali (one-time events) untuk aksi yang tidak mengubah state jangka panjang.
- **Example**: `HomeEffect.ShowError(message)`, `HomeEffect.NavigateToDetail(url)`.

## 2. Implementasi Teknis (`BaseViewModel`)

Semua ViewModel mewarisi `BaseViewModel<State, Intent, Effect>`.

```kotlin
abstract class BaseViewModel<S : UiState, I : UiIntent, E : UiEffect>(
    initialState: S
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effect = Channel<E>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    abstract fun processIntent(intent: I)

    protected fun setState(reduce: S.() -> S) {
        _state.update { it.reduce() }
    }

    protected fun sendEffect(builder: () -> E) {
        viewModelScope.launch { _effect.send(builder()) }
    }
}
```

## 3. Alur Data Lengkap

1.  **User Action**: User melakukan input di Compose UI.
2.  **Dispatch Intent**: UI memanggil `viewModel.processIntent(Intent)`.
3.  **ViewModel Processing**: ViewModel menangani Intent, memanggil UseCase, dan mengamati hasil via `Flow<ResultState<T>>`.
4.  **Network/Data Layer**: `SafeApiCall` menangani eksekusi API dan penanganan error terpusat.
5.  **State Update**: ViewModel menerima `ResultState`:
    - `Loading`: Memanggil `setState { copy(isLoading = true) }`.
    - `Success`: Memanggil `setState { copy(isLoading = false, data = it.data) }`.
    - `Error`: Memanggil `sendEffect { ShowError(it.message) }`.
6.  **UI Recomposition**: Compose UI mengamati `state.collectAsStateWithLifecycle()` dan menggambar ulang layar secara otomatis.

## 4. Keuntungan Pendekatan Ini

- **Predictability**: Aliran data satu arah memudahkan debugging karena kita tahu persis di mana state berubah.
- **Separation of Concerns**: UI tidak tahu logika bisnis, hanya tahu cara mengirim Intent dan merender State.
- **Testability**: State dan Intent sangat mudah diuji menggunakan Unit Test karena sifatnya yang eksplisit.
