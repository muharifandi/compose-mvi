# Arsitektur Aplikasi (Clean Architecture)

Dokumen ini menjelaskan fondasi arsitektur yang digunakan dalam aplikasi ini untuk memastikan kode yang mudah diuji, dipelihara, dan dikembangkan oleh tim besar.

## 1. Filosofi Arsitektur
Kami mengadopsi **Clean Architecture** yang dipopulerkan oleh Robert C. Martin (Uncle Bob). Tujuan utamanya adalah pemisahan perhatian (*Separation of Concerns*) sehingga logika bisnis tidak tergantung pada framework UI, database, atau library pihak ketiga.

### Mengapa Clean Architecture?
- **Maintainability:** Mudah mengubah library (misalnya ganti Retrofit ke Ktor) tanpa merusak logika bisnis.
- **Testability:** Logika bisnis (Domain) dapat diuji secara mandiri dengan Unit Test tanpa emulator.
- **Independence:** UI bisa berubah total (XML ke Compose) tanpa menyentuh repository.

---

## 2. Layer Arsitektur

### A. Presentation Layer (UI)
Bertanggung jawab untuk menampilkan data ke layar dan menangkap interaksi pengguna.
- **Teknologi:** Jetpack Compose & ViewModel.
- **Pola:** MVI (Model-View-Intent).
- **Dependency:** Tergantung pada **Domain Layer**.

### B. Domain Layer (Bisnis)
Pusat dari aplikasi. Berisi logika bisnis murni.
- **Komponen:** UseCase, Entity, dan Repository Interface.
- **Aturan Emas:** Tidak boleh memiliki dependensi ke layer lain (Android framework-free).

### C. Data Layer (Infrastructure)
Implementasi dari pengambilan data.
- **Komponen:** Repository Implementation, Data Source (Remote & Local), Mapper, DTO.
- **Dependency:** Tergantung pada **Domain Layer** (melalui implementasi interface).

---

## 3. Dependency Direction & Modularization
Arah dependensi selalu **masuk ke dalam** (menuju Domain Layer) dan mengikuti pola **Feature-API/Impl**.

### Visualisasi Struktur Modul
```mermaid
graph TD
    subgraph App_Layer
        APP[":app"]
    end

    subgraph Feature_Layer
        subgraph News_Feature
            NEWS_API[":features:news:api"]
            NEWS_IMPL[":features:news:impl"]
        end
        subgraph About_Feature
            ABOUT_API[":features:about:api"]
            ABOUT_IMPL[":features:about:impl"]
        end
    end

    subgraph Navigation_Layer
        NAV[":navigation"]
    end

    subgraph Core_Layer
        CORE_NET[":core:network"]
        CORE_UI[":core:ui"]
        CORE_ARC[":core:architecture"]
        CORE_MOD[":core:model"]
    end

    APP --> NEWS_IMPL
    APP --> ABOUT_IMPL
    APP --> NAV

    NEWS_IMPL --> NEWS_API
    NEWS_IMPL --> CORE_NET
    NEWS_IMPL --> CORE_ARC
    NEWS_IMPL --> CORE_UI
    NEWS_IMPL --> CORE_MOD

    ABOUT_IMPL --> ABOUT_API
    ABOUT_IMPL --> CORE_UI

    NAV --> NEWS_API
    NAV --> ABOUT_API
    NAV --> CORE_ARC
```

Pola ini menggunakan **Dependency Inversion Principle (DIP)** dari SOLID. Modul `:impl` bergantung pada `:api` miliknya sendiri dan modul `:core`. Modul lain hanya bergantung pada `:api` untuk melakukan navigasi atau menggunakan model data publik.

---

## 4. Contoh Implementasi

### Repository Interface (Domain)
```kotlin
interface NewsRepository {
    suspend fun getTopHeadlines(): Flow<ResultState<List<Article>>>
}
```

### UseCase (Domain)
```kotlin
class GetTopHeadlinesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke() = repository.getTopHeadlines()
}
```

---

## 5. Anti-Pattern yang Harus Dihindari
- **God ViewModel:** Menaruh logika bisnis atau parsing JSON di dalam ViewModel.
- **Context Leak:** Mengirim `Context` ke Repository atau UseCase.
- **Circular Dependency:** Module A butuh B, Module B butuh A.
- **Direct DB Access:** Fragment/Compose langsung memanggil DAO.

---

## 6. Kesimpulan
Dengan mematuhi struktur ini, aplikasi kita akan memiliki batas arsitektur (*boundary*) yang jelas, mengurangi risiko regresi saat ada perubahan kode di masa depan.
