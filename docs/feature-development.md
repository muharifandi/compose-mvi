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

## 3. Registrasi Fitur & Navigasi (PENTING)
Agar fitur dapat dikenali oleh aplikasi dan tidak menyebabkan error "Unresolved Reference", ikuti langkah wajib ini:

### Step 1: Daftarkan di Modul API
Buat interface di `:features:<name>:api`:
```kotlin
interface MyFeatureApi : FeatureApi {
    fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController)
}
```

### Step 2: Tambahkan Dependensi di Modul Navigasi
Buka `:navigation/build.gradle.kts` (atau modul tempat NavHost berada) dan tambahkan:
```kotlin
dependencies {
    implementation(project(":features:<name>:impl"))
}
```

### Step 3: Implementasi & Binding Hilt
Di dalam modul `:impl`, buat modul DI untuk membinding API ke Implementasi agar Hilt bisa melakukan *Multi-Binding*:
```kotlin
@Module
@InstallIn(SingletonComponent::class)
interface MyNavigationModule {
    @Binds
    @IntoSet
    fun bindMyFeatureApi(impl: MyFeatureApiImpl): FeatureApi
}
```

### Step 4: Daftarkan di settings.gradle.kts
Pastikan modul baru sudah terdaftar (biasanya otomatis jika menggunakan skrip generator):
```kotlin
include(":features:<name>:api")
include(":features:<name>:impl")
```

---

## 4. Cara Memanggil Fitur dari Modul Lain
Untuk menjaga *decoupling* (agar antar modul tidak saling kenal), gunakan mekanisme callback:

1. **Di Modul Pemanggil (misal Master):** Tambahkan parameter lambda di Screen.
   ```kotlin
   @Composable
   fun MasterScreen(onNavigateToDetail: (String) -> Unit)
   ```
2. **Di Navigasi Utama:** Hubungkan rute secara nyata.
   ```kotlin
   // Di dalam registerGraph
   MasterScreen(
       onNavigateToDetail = { id -> 
           navController.navigate(DetailDestinations(id)) 
       }
   )
   ```

---

## 5. Troubleshooting: Kenapa Fitur Saya Error?
| Error | Penyebab Utama | Solusi |
| :--- | :--- | :--- |
| `Unresolved reference` | Dependensi Gradle belum ditambahkan. | Cek `build.gradle.kts` di modul pemanggil. |
| `Hilt Missing Binding` | `@Binds` atau `@IntoSet` belum dibuat. | Cek Step 3 (Modul DI Navigasi). |
| `Route not found` | `registerGraph` belum dipanggil. | Pastikan `FeatureApi` sudah masuk ke dalam `Set<FeatureApi>` di NavHost. |
| `Module not found` | Modul belum ada di `settings.gradle`. | Cek `settings.gradle.kts` di root. |
