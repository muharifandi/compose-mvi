# Panduan Pengembangan Fitur Baru

Dokumen ini adalah panduan *step-by-step* bagi developer untuk membuat fitur baru dengan standar Clean Architecture dan MVI.

## 1. Struktur Folder Standar
Setiap fitur di dalam modul `:features:<name>:impl` harus mengikuti struktur berikut:

```text
com.muh.arifandi.dicoding.features.<name>
├── data
│   ├── network (API Service, DTO)
│   ├── database (DAO, Entity)
│   ├── repository (Implementation)
│   └── mapper (DTO/Entity to Domain Model)
├── domain
│   ├── model (Domain Entities)
│   ├── usecase (Business Logic)
│   └── repository (Interface)
├── ui
│   ├── <screen_name>
│   │   ├── components (UI Components)
│   │   ├── state (State, Intent, Effect)
│   │   ├── <Name>Screen.kt
│   │   └── <Name>ViewModel.kt
├── di (Hilt Modules)
└── navigation (FeatureApi Implementation)
```

---

## 2. Template Implementasi MVI

### A. State, Intent, Effect
```kotlin
data class MyState(val isLoading: Boolean = false, val data: List<String> = emptyList()) : UiState

sealed class MyIntent : UiIntent {
    object LoadData : MyIntent()
}

sealed class MyEffect : UiEffect {
    data class ShowToast(val message: String) : MyEffect()
}
```

### B. ViewModel
```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(
    private val useCase: MyUseCase
) : BaseViewModel<MyState, MyIntent, MyEffect>(MyState()) {
    override fun onIntent(intent: MyIntent) {
        when (intent) {
            is MyIntent.LoadData -> { /* Logic */ }
        }
    }
}
```

---

## 3. Registrasi Fitur & Navigasi

### Step 1: Daftarkan di Modul API
Buat interface di `:features:<name>:api`:
```kotlin
interface MyFeatureApi : FeatureApi {
    fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController)
}
```

### Step 2: Implementasi di Modul Impl
```kotlin
class MyFeatureApiImpl @Inject constructor() : MyFeatureApi {
    override fun registerGraph(...) {
        navGraphBuilder.composable("my_route") {
            MyScreen(...)
        }
    }
}
```

### Step 3: Registrasi di Hilt
```kotlin
@Module
@InstallIn(SingletonComponent::class)
interface MyNavigationModule {
    @Binds
    @IntoSet
    fun bindMyFeatureApi(impl: MyFeatureApiImpl): FeatureApi
}
```

---

## 4. Best Practices
- **Mapper:** Selalu gunakan mapper untuk memisahkan data layer (DTO) dari domain layer (Model).
- **UseCase:** Satu UseCase hanya bertanggung jawab untuk satu aksi bisnis.
- **Compose:** Gunakan `State Hoisting` dan hindari menaruh logika bisnis di Composable.
