# Changelog

Semua perubahan signifikan pada proyek **SakaAndroid** akan didokumentasikan di file ini. Format ini mengikuti standar [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [1.1.0] - 2026-05-25 15:45 WIB

### Added
- **Domain Layer**: Implementasi UseCase untuk fitur Master (`GetCreditCardsUseCase`, `GetMenuItemsUseCase`).
- **Domain Layer**: Implementasi UseCase untuk fitur Auth (`LoginUseCase`, `RegisterUseCase`, `ForgotpasswordUseCase`).
- **Domain Layer**: Implementasi UseCase untuk fitur Onboarding (`GetOnboardingPagesUseCase`).
- **Data Layer**: Implementasi Repository (`MasterRepositoryImpl`, `LoginRepositoryImpl`, `RegisterRepositoryImpl`, `ForgotpasswordRepositoryImpl`, `OnboardingRepositoryImpl`).
- **UI Mapper**: `MasterMapper.kt` untuk konversi domain model (Pure Kotlin) ke objek spesifik Compose (Color, Icon).
- **Metadata**: Header identitas author di seluruh file fitur Master, Search, Home, Auth, dan Onboarding.
- **Documentation**: Diagram Mermaid untuk visualisasi alur Clean Architecture di `master-feature.md`.
- **Documentation**: Troubleshooting guide untuk registrasi fitur baru di `feature-development.md`.

### Changed
- **Refactor**: Transformasi Domain Model (`MasterMenuItem`, `CreditCardInfo`) menjadi Pure Kotlin dengan menghapus dependensi framework Android/Compose.
- **Refactor**: Standarisasi pola MVI + Clean Architecture di seluruh modul utama (Master, Auth, Onboarding).
- **UI/UX**: Perbaikan visual `SearchScreen.kt` (TopBar kontras, padding kartu, dan tipografi bold) sesuai desain.
- **State Management**: Pemindahan logika data `searchItems` dari Composable ke `SearchViewModel` melalui `SearchState`.
- **Architecture**: Pemisahan tanggung jawab yang lebih ketat antara Presentation, Domain, dan Data Layer.

### Fixed
- **Code Smell**: Menghapus inisialisasi data hardcoded di dalam fungsi Composable.
- **Navigation**: Memperbaiki visibilitas ikon back dan callback navigasi pada `SearchScreen`.
- **Consistency**: Menghilangkan duplikasi data menu grid dashboard dengan menggunakan *Single Source of Truth* di Repository.

---
*Catatan: Modul `:features:news` sengaja dikecualikan dari refactor ini sesuai instruksi.*
