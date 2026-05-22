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

## 🧭 Panduan Navigasi & Pengiriman Data

Proyek ini menggunakan **Jetpack Compose Navigation Type-Safe** (berbasis rute objek/class).

### 1. Navigasi Tanpa Data (Simple)
Cukup panggil objek `Destinations` yang sudah dibuat otomatis.
```kotlin
navController.navigate(OnboardingDestinations)
```

### 2. Mengirim Data Teks atau Primitif (String, Int, dll)
Ubah `data object` menjadi `data class` di file `Destinations.kt` pada modul API.
```kotlin
// Di features/news/api/.../NewsDestinations.kt
@Serializable
data class NewsDetail(val newsId: String)
```
**Cara Kirim:**
```kotlin
navController.navigate(NewsDetail(newsId = "123"))
```
**Cara Terima:**
```kotlin
// Di FeatureApiImpl
composable<NewsDetail> { backStackEntry ->
    val detail: NewsDetail = backStackEntry.toRoute()
    NewsDetailScreen(id = detail.newsId)
}
```

### 3. Mengirim Object / Data Class Kompleks
Untuk mengirim objek, Anda harus mendaftarkan `CustomNavType`. Namun, **sangat disarankan** hanya mengirim ID (String/Long) dan membiarkan layar tujuan mengambil data dari Repository/Database/Network.

Jika *terpaksa* harus mengirim objek:
1. Pastikan class tersebut `@Serializable`.
2. Gunakan `Json.encodeToString` untuk mengubahnya menjadi string.

### 4. Navigasi Antar Fitur (Integrasi)
Navigasi antar fitur dilakukan melalui callback di `FeatureApiImpl`. Hal ini menjaga agar modul fitur tidak saling bergantung langsung.

**Contoh di Onboarding ke Login:**
```kotlin
// OnboardingFeatureApiImpl.kt
composable<OnboardingDestinations> {
    OnboardingScreen(
        onNavigateToLogin = {
            navController.navigate(LoginDestinations) {
                // Menghapus onboarding dari backstack agar tidak bisa kembali
                popUpTo(OnboardingDestinations) { inclusive = true }
            }
        }
    )
}
```

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
