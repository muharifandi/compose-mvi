# Panduan Pengembangan Fitur

Ikuti langkah-langkah ini untuk menambahkan fitur baru ke dalam proyek.

## 1. Tambahkan Modul Baru
- Buat modul Android Library baru, misal: `:features:profile`.
- Daftarkan modul tersebut di `settings.gradle.kts`.
- Di `build.gradle.kts` modul fitur, tambahkan dependensi wajib:
  ```kotlin
  dependencies {
      implementation(project(":core:common"))
      implementation(project(":core:network"))
      implementation(project(":core:ui"))
      implementation(project(":navigation"))
      // ... dependensi Hilt & Compose lainnya
  }
  ```

## 2. Struktur Paket Fitur
Buat struktur paket berikut di dalam modul baru:
- `data`: API Service, DTO, Mapper, dan implementasi Repository.
- `domain`: Model Domain, kontrak Repository (Interface), dan Use Cases.
- `ui`: Screen (Composable), ViewModel, dan MVI classes (State, Intent, Effect).

## 3. Implementasi Network & Data
- Gunakan `SafeApiCall` di Repository untuk menangani request jaringan.
- Pastikan Service API mengembalikan data langsung (misal: `NewsResponse`) agar `SafeApiCall` dapat menangkap exception dengan benar.

```kotlin
// Contoh di Repository
override fun getProfile(): Flow<ResultState<Profile>> = 
    safeApiCall.flow { 
        apiService.getProfile().toDomain() 
    }
```

## 4. Implementasi UI & MVI
- Buat State sebagai data class immutable.
- Implementasikan ViewModel dengan mewarisi `BaseViewModel`.
- Gunakan `collectAsStateWithLifecycle()` di Screen untuk mengamati state.

## 5. Registrasi Navigasi
- Tambahkan rute baru di modul `:navigation` pada file `Destinations.kt`.
- Tambahkan rute tersebut ke `AppNavHost` di modul `:app`.

## 6. Dependency Injection (Hilt)
- Gunakan `@HiltViewModel` untuk ViewModel.
- Buat module Hilt di dalam folder `data/di` untuk menyediakan (provide/bind) repository dan API service.
