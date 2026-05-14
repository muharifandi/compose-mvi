# Engineering Playbook: My Application

Dokumen ini adalah panduan standar operasional untuk pengembangan aplikasi menggunakan **Highly Scalable Engineering Foundation**.

## 1. Menambahkan Fitur Baru (Pola API/Impl)
Setiap fitur baru HARUS dipisah menjadi dua modul:
1.  `:features:X:api`: Berisi rute navigasi (`@Serializable`), model data minimal, dan interface jika diperlukan.
2.  `:features:X:impl`: Berisi UI (Compose), ViewModel, Repository Implementation, dan UseCase.

**Kenapa?** Untuk mencegah dependensi sirkular dan mempercepat build. Modul lain hanya perlu mengimpor `:api` untuk melakukan navigasi.

## 2. Manajemen Build (Convention Plugins)
Dilarang keras menulis konfigurasi manual di `build.gradle.kts`. Gunakan plugin yang tersedia:
- `myapp.android.feature`: Untuk modul `:impl`.
- `myapp.kotlin.library`: Untuk modul `:api` atau modul Pure Kotlin.
- `myapp.android.room`: Otomatis menyetel KSP dan runtime Room.
- `myapp.android.paging`: Otomatis menyetel Paging 3.

## 3. Aturan Navigasi
Navigasi dilakukan secara *Decoupled* menggunakan `NavigationManager`:
```kotlin
// Di dalam ViewModel
navigator.navigateTo(FeatureXDestinations.Detail(id))
```
Hindari memanggil `navController` langsung di dalam UI Composable kecuali untuk kasus yang sangat sederhana.

## 4. Layer Domain
- Layer Domain HARUS 100% Pure Kotlin.
- Dilarang mengimpor `android.*` atau library pihak ketiga di layer ini.
- Gunakan `ResultState` dari `:core:model` untuk membungkus aliran data.

## 5. Performa
Selalu jalankan `:baselineprofile:generateBaselineProfile` sebelum merilis versi produksi untuk memastikan startup aplikasi tetap instan.
