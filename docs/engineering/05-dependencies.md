# Dokumentasi Dependensi

Proyek ini menggunakan **Version Catalog** (`libs.versions.toml`) untuk manajemen dependensi yang terpusat.

| Library | Kegunaan | Alasan Dipilih | Alternatif |
| :--- | :--- | :--- | :--- |
| **Jetpack Compose** | UI Framework | Deklaratif, modern, performa tinggi, dan standar Google. | XML View System |
| **Hilt** | Dependency Injection | Mudah digunakan, terintegrasi dengan Android Lifecycle. | Koin, Dagger2 |
| **Retrofit** | Networking | Standar industri, sangat stabil, dukungan komunitas besar. | Ktor Client |
| **Room** | Local Database | Abstraksi SQLite yang kuat dan aman secara tipe (type-safe). | Realm, SQLDelight |
| **Kotlin Coroutines** | Asynchrony | Ringan, terintegrasi dengan Flow dan Lifecycle. | RxJava |
| **Coil** | Image Loading | Dibuat dengan Kotlin & Coroutines, ringan, Compose-friendly. | Glide, Fresco |
| **Paging 3** | Pagination | Menangani loading & error secara otomatis untuk daftar besar. | Custom Pagination |
| **Timber** | Logging | Memudahkan pengelolaan log secara aman di produksi. | Log.d |

## Strategi Pembaruan
Kami menyarankan pembaruan library secara berkala melalui Version Catalog untuk menjaga keamanan dan mendapatkan fitur terbaru. Selalu verifikasi perubahan melalui UI Testing setelah melakukan update library mayor.
