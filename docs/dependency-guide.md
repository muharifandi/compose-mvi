# Panduan Dependency & Library Project

Dokumen ini menjelaskan seluruh ekosistem library, plugin Gradle, dan dependensi arsitektur yang digunakan dalam project ini.

## 1. Tabel Ringkasan Library Utama

| Library | Purpose | Why Chosen | When To Use | When NOT To Use | Alternative | Scalability Impact |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Hilt** | Dependency Injection | Standard Google, boilerplate rendah. | Seluruh lifecycle aplikasi. | Project sangat kecil/scripting. | Koin, Dagger | Tinggi (Modularisasi mudah) |
| **Compose** | UI Framework | Declarative UI, modern, produktif. | Semua layer presentasi. | View-based legacy project. | XML Layouts | Sangat Tinggi (Reusable UI) |
| **Coroutines**| Concurrency | Ringan, native Kotlin, readable. | Operasi Async/Background. | Simple synchronous logic. | RxJava, Java Threads| Tinggi (Structured Concurrency)|
| **StateFlow** | State Management | Lifecycle-aware, cold/hot stream. | Mengirim data dari VM ke UI. | Simple callback. | LiveData, RxJava | Tinggi (MVI Friendly) |
| **Hilt Nav** | Navigation DI | Integrasi Hilt dengan Compose Nav. | Injeksi VM di NavGraph. | Tanpa navigasi Compose. | Manual Injection | Medium |

---

## 2. Dependency Management Strategy

### A. Version Catalog (`libs.versions.toml`)
Kami menggunakan **Gradle Version Catalog** sebagai *single source of truth* untuk versi library.
- **Konsep:** Mendefinisikan versi dan library di satu file pusat.
- **Tujuan:** Menghindari konflik versi antar modul.
- **Masalah yang diselesaikan:** "Dependency Hell" di mana modul A pakai versi 1.0 dan modul B pakai versi 2.0.

### B. Convention Plugins (`build-logic`)
Konfigurasi Gradle yang berulang dipindah ke plugin kustom.
- **Placement:** Semua modul `:features` menggunakan `myapp.android.feature`.
- **Relationship:** Memastikan semua modul memiliki konfigurasi ProGuard, SDK version, dan testing yang identik.

---

## 3. Deep Dive Library Arsitektur

### Hilt (Dagger Hilt)
- **Cara Kerja:** Melakukan code generation saat compile time untuk menyediakan objek.
- **Integration Flow:** `@HiltAndroidApp` -> `@AndroidEntryPoint` -> `@Inject`.
- **Performance:** Sedikit menambah waktu compile, namun nol impact pada runtime performance (tidak pakai refleksi).
- **Tradeoff:** Belajar konsep Dagger bisa sulit bagi pemula.

### Kotlin Coroutines & Flow
- **Kenapa dipilih:** Arsitektur MVI membutuhkan aliran data asinkron yang bisa dibatalkan (cancelable).
- **Misuse:** Menjalankan coroutine di `GlobalScope` (menyebabkan memory leak). Gunakan `viewModelScope` atau `lifecycleScope`.

### Testing Libraries (JUnit, MockK, Turbine)
- **JUnit 5:** Runner untuk unit test.
- **MockK:** Library mocking yang didesain khusus untuk Kotlin (mendukung `suspend` function).
- **Turbine:** Library kecil untuk mengetes Kotlin Flow tanpa perlu manual collection.

---

## 4. Dependency Ownership & Placement
- **`:core:ui`**: Memiliki dependensi Compose, Material3, Coil.
- **`:core:network`**: Memiliki dependensi Retrofit, OkHttp, Serialization.
- **`:core:architecture`**: Memiliki dependensi Hilt, ViewModel, Coroutines.
- **`:features:impl`**: Bergantung pada modul `:core` yang dibutuhkan melalui `implementation`.

---

## 5. Kesimpulan
Pemilihan library didasarkan pada **Android Modern Development (MAD)** yang didukung penuh oleh Google dan komunitas, memastikan dukungan jangka panjang dan kemudahan mencari tenaga ahli (talent).
