# Arsitektur My Application

Proyek ini menggunakan **Highly Scalable Engineering Foundation** berbasis pola Big Tech.

## Prinsip Utama
1. **Clean Architecture**: Pemisahan layer Domain (Pure Kotlin), Data, dan Presentation.
2. **Feature-API/Impl Pattern**: Setiap fitur dipisah menjadi modul `:api` (kontrak/navigasi) dan `:impl` (implementasi).
3. **Convention Plugins**: Logika build terpusat di modul `build-logic` untuk standarisasi.
4. **MVI State Management**: Aliran data satu arah (UDF) menggunakan State, Intent, dan Effect.
5. **Global Navigation Manager**: Navigasi decoupled dari UI framework via `NavigationManager`.

## Struktur Modul
- `:app`: Orchestrator utama.
- `:core`: Modul infrastruktur generik (UI, Network, Model, Common).
- `:features:X:api`: Kontrak publik fitur X.
- `:features:X:impl`: Implementasi privat fitur X.
- `:navigation`: Orchestrator navigasi antar modul API.
- `:build-logic`: Pusat aturan build Gradle.

## Alur Kerja Developer
1. Tambahkan rute baru di `:features:X:api`.
2. Implementasikan layar di `:features:X:impl`.
3. Daftarkan di `AppNavHost` pada modul `:app`.
