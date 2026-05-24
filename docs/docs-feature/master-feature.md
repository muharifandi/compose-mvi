# Dokumentasi Fitur - Master (Dashboard Shell)

Fitur Master berfungsi sebagai container utama aplikasi setelah pengguna berhasil login. Fitur ini mengelola navigasi Bottom Bar dan menampilkan konten dashboard utama.

## Deskripsi Fungsional
* Menyediakan navigasi Bottom Bar dengan 4 menu: Home, Search, Message, dan Settings.
* Menggunakan `SakaTabBar` dengan animasi transisi yang halus.
* Menampilkan dashboard perbankan di menu Home (Informasi Saldo, Kartu Kredit, dan Menu Pintasan).

## Arsitektur UI (MVI)
* **State**: `MasterState` (selectedTab, isLoading).
* **Intent**: `MasterIntent` (SelectTab).
* **Effect**: `MasterEffect` (ShowError).

## Visualisasi Alur (Interaction Graph)
```mermaid
graph TD
    Entry((Layar Master)) --> TabSelection{Pilih Tab}
    TabSelection -- Home --> HomeView[Dashboard Home]
    TabSelection -- Search --> SearchView[Pencarian]
    TabSelection -- Message --> MessageView[Pesan/Inbox]
    TabSelection -- Settings --> SettingsView[Pengaturan]
    
    subgraph Home_Content [Konten Home]
        HomeView --> Profile[Profil Header]
        HomeView --> Card[Kartu Kredit/Saldo]
        HomeView --> Grid[Grid Menu Layanan]
    end
```

## Komponen UI yang Digunakan
1. **SakaTabBar**: Komponen navigasi bawah kustom.
2. **SakaCard**: Digunakan untuk kontainer kartu kredit dan item menu grid.
3. **SakaAsyncImage**: Untuk memuat foto profil pengguna.
4. **LazyVerticalGrid**: Untuk menampilkan 9 menu layanan di Dashboard.
