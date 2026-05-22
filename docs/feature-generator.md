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
Skrip akan membuat file MVI yang terpisah sesuai standar enterprise:
- `ui/state/<Feature>State.kt`: Definisi data class Immutable state.
- `ui/state/<Feature>Intent.kt`: Definisi sealed interface untuk user action.
- `ui/state/<Feature>Effect.kt`: Definisi sealed interface untuk side effects (navigasi, toast, dll).
- `ui/<Feature>ViewModel.kt`: Implementasi `BaseViewModel` dengan fungsi `processIntent`.
- `ui/<Feature>Screen.kt`: Composable screen utama dengan `collectAsStateWithLifecycle`.

### 3. Konfigurasi Otomatis
- Mendaftarkan modul baru di `settings.gradle.kts`.
- Mengkonfigurasi `build.gradle.kts` dengan plugin konvensi `myapp.kotlin.library` (API) dan `myapp.android.feature` (IMPL).
- Menambahkan dependensi dasar seperti `core:architecture`, `core:ui`, dan `navigation`.

## ⚠️ Hal yang Perlu Diperhatikan
- **Lower Case**: Selalu gunakan huruf kecil untuk parameter nama fitur.
- **Gradle Sync**: Setelah menjalankan skrip, Anda **wajib** melakukan Gradle Sync agar modul baru dikenali oleh Android Studio.
- **Manual Step**: Anda masih perlu mendaftarkan API implementasi di modul `navigation` dan melakukan Dependency Injection di modul `:app` jika diperlukan.

## 🛠 Troubleshooting
Jika terjadi error "Unresolved reference: myapp", pastikan Anda menggunakan versi terbaru skrip yang menggunakan `id("myapp...")` alih-alih `alias(libs.plugins.myapp...)`, karena skrip ini merujuk langsung ke *convention plugins* internal.
