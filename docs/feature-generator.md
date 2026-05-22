# Saka Feature Generator 🚀

Dokumen ini menjelaskan cara menggunakan skrip `generate_feature.sh` untuk mempercepat pembuatan modul fitur baru di proyek SakaAndroid dengan standar arsitektur yang konsisten.

## 📝 Deskripsi
Skrip `generate_feature.sh` adalah utilitas otomatisasi untuk membuat struktur modul fitur yang mengikuti pola **API/Impl Split** dan arsitektur **MVI (Model-View-Intent)**. Skrip ini akan secara otomatis membuat folder, file konfigurasi Gradle, serta boilerplate code untuk UI dan State Management.

## 🚀 Cara Penggunaan

1.  Buka terminal di root direktori proyek.
2.  Pastikan skrip memiliki izin eksekusi:
    ```bash
    chmod +x generate_feature.sh
    ```
3.  Jalankan skrip dengan memberikan nama fitur (dalam format lowercase):
    ```bash
    ./generate_feature.sh <nama_fitur>
    ```
    Contoh:
    ```bash
    ./generate_feature.sh profile
    ```
4.  Lakukan **Gradle Sync** di Android Studio.

## 📦 Apa yang Dihasilkan?

Skrip ini akan menghasilkan struktur folder dan file berikut:

### 1. Struktur Modul
- `features/<nama_fitur>/api`: Modul kontrak (pure Kotlin).
- `features/<nama_fitur>/impl`: Modul implementasi (Android Library).

### 2. Boilerplate Arsitektur (Layer UI)
Skrip akan membuat file MVI dan Navigasi yang terpisah sesuai standar enterprise:
- `ui/state/<Feature>State.kt`: Definisi data class Immutable state.
- `ui/state/<Feature>Intent.kt`: Definisi sealed interface untuk user action.
- `ui/state/<Feature>Effect.kt`: Definisi sealed interface untuk side effects.
- `ui/<Feature>ViewModel.kt`: Implementasi `BaseViewModel`.
- `ui/<Feature>Screen.kt`: Composable screen utama (Stateful & Stateless) menggunakan `SakaScaffold`.
- `api/<Feature>Destinations.kt`: Definisi rute navigasi (Type-safe).
- `navigation/<Feature>FeatureApiImpl.kt`: Registrasi rute ke NavGraph.
- `di/NavigationModule.kt`: Hilt module untuk Dependency Injection navigasi.

### 3. Konfigurasi Otomatis
- Mendaftarkan modul baru di `settings.gradle.kts`.
- Mengkonfigurasi `build.gradle.kts` dengan dependensi standar (`core:ui`, `core:architecture`, dll).

## 🛠 Langkah Integrasi Manual (PENTING)

Setelah menjalankan skrip, fitur tidak akan langsung muncul di aplikasi. Anda harus:

1.  **Gradle Sync**: Tekan gajah di Android Studio.
2.  **Daftarkan di Modul Navigation**:
    Buka `navigation/build.gradle.kts` dan tambahkan:
    ```kotlin
    dependencies {
        api(project(":features:<nama_fitur>:api"))
    }
    ```
3.  **Navigasi**:
    Panggil fitur menggunakan `NavController`:
    ```kotlin
    navController.navigate(<Feature>Destinations)
    ```

## 🛠 Troubleshooting
Jika terjadi error "Unresolved reference: myapp", pastikan Anda menggunakan versi terbaru skrip yang menggunakan `id("myapp...")` alih-alih `alias(libs.plugins.myapp...)`, karena skrip ini merujuk langsung ke *convention plugins* internal.
