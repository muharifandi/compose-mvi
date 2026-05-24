# Dokumentasi Fitur - Onboarding

Fitur ini menangani perkenalan aplikasi kepada pengguna baru melalui serangkaian slide informatif.

## Deskripsi Fungsional
* Menampilkan informasi keunggulan aplikasi.
* Mendukung navigasi swipe manual (HorizontalPager) atau tombol "Next".
* Menyimpan status "Onboarding Completed" (opsional) untuk mencegah pengulangan.

## Arsitektur UI (MVI)
* **State**: `OnboardingState` (items, currentPage, isLastPage).
* **Intent**: `OnboardingIntent` (NextPage, GetStarted).
* **Effect**: `OnboardingEffect` (NavigateToLogin).

## Visualisasi Alur (Interaction Graph)
```mermaid
graph TD
    Start((Mulai)) --> Page1[Slide 1: Kelola Saldo]
    Page1 -- Swipe/Next --> Page2[Slide 2: Transfer Aman]
    Page2 -- Swipe/Next --> Page3[Slide 3: Investasi Masa Depan]
    Page3 -- "Mulai (Get Started)" --> LoginFlow{{Navigasi ke Login}}
    
    subgraph UI_Interaction [Interaksi Per Halaman]
        P[Pager] --> Content[Gambar + Judul + Deskripsi]
        Content --> Button[Tombol Dinamis: Next/Mulai]
    end
```
