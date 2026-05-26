# Dokumentasi Fitur - Onboarding

Fitur ini menangani perkenalan aplikasi kepada pengguna baru melalui serangkaian slide informatif dengan arsitektur yang terdekopel.

## Arsitektur (Clean MVI)
* **UseCase**: `GetOnboardingPagesUseCase` (Mengambil data slide dari repository).
* **State**: `OnboardingState` (items: List<OnboardingPage>, currentPage).
* **ViewModel**: `OnboardingViewModel` (Mengambil data saat init melalui UseCase).

## Deskripsi Fungsional
* Menampilkan informasi keunggulan aplikasi secara dinamis.
* Mendukung navigasi swipe manual (HorizontalPager) atau tombol "Next".
* Menggunakan data model `OnboardingPage` yang didefinisikan di layer domain.

## Visualisasi Alur (Interaction Graph)
```mermaid
graph TD
    Start((Mulai)) --> UC[GetOnboardingPagesUseCase]
    UC --> Repo[OnboardingRepository]
    Repo -- "List<Page>" --> VM[OnboardingViewModel]
    VM -- "Update State" --> UI[OnboardingScreen]
    
    UI --> Page1[Slide 1]
    Page1 -- Next --> Page2[Slide 2]
    Page2 -- Next --> Page3[Slide 3]
    Page3 -- "Get Started" --> LoginFlow{{Navigasi ke Login}}
    
    subgraph UI_Interaction [Komponen UI]
        P[HorizontalPager] --> Content[SakaAsyncImage + Text]
    end
```

## Konfigurasi Data
Data onboarding dapat diubah secara terpusat di `OnboardingRepositoryImpl.kt` tanpa menyentuh layer UI.
