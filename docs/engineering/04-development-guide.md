# Panduan Pengembangan Fitur

Ikuti langkah-langkah ini untuk menambahkan fitur baru (misal fitur: `profile`).

## 1. Tambahkan Modul Baru
- Buat modul Android Library baru: `:features:profile`.
- Daftarkan di `settings.gradle.kts`.
- Tambahkan dependensi ke `:domain:[name]`, `:core:ui`, dan `:core:common` di `build.gradle.kts` fitur tersebut.

## 2. Definisikan Domain (Jika ada bisnis baru)
- Tambahkan model di `:domain:news`.
- Tambahkan interface repository di `:domain:news`.
- Tambahkan UseCase jika diperlukan.

## 3. Implementasi Data (di dalam Modul Fitur)
- Buat DTO (untuk API) dan Entity (untuk DB).
- Buat Mapper untuk konversi ke Domain Model.
- Buat implementasi Repository.

## 4. Buat UI & MVI (di dalam Modul Fitur)
- Buat `ProfileState`, `ProfileIntent`, dan `ProfileEffect`.
- Buat `ProfileViewModel` yang mewarisi `BaseViewModel`.
- Buat `ProfileScreen` menggunakan Jetpack Compose.

## 5. Daftarkan Navigasi
- Tambahkan entry baru di modul `:navigation` pada file `Destinations.kt`.
- Tambahkan composable baru di `NavHost` yang ada di modul `:app`.

## 6. Integrasi Dependency Injection
- Gunakan `@HiltViewModel` pada ViewModel.
- Gunakan `@Module` dan `@Binds` di modul fitur untuk menyediakan implementasi repository ke Hilt.
