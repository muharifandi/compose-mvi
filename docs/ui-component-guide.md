# Saka Design System - UI Component Guide

Dokumen ini adalah katalog resmi untuk seluruh komponen UI yang tersedia di modul `:core:ui`. Gunakan komponen ini untuk menjaga konsistensi visual dan mempercepat pengembangan fitur.

## Daftar Isi
- [1. Fondasi & Tema](#1-fondasi--tema)
- [2. Tombol (Buttons)](#2-tombol-buttons)
- [3. Input & Form (Text Fields)](#3-input--form-text-fields)
- [4. Navigasi (Navigation)](#4-navigasi)
- [5. Kontainer (Cards & Containers)](#5-kontainer)
- [6. Feedback & States](#6-feedback--states)
- [7. Kontrol (Controls)](#7-kontrol)

---

## 1. Fondasi & Tema
Seluruh komponen di bawah ini secara otomatis menggunakan identitas visual **SakaAndroid**.
- **Warna**: Terdefinisi di `SakaColors` (Primary, Neutral, Semantic).
- **Tipografi**: Menggunakan font **Poppins** yang dibungkus dalam `SakaTypography`.
- **Akses**: Gunakan `SakaTheme.colors` dan `SakaTheme.typography`.

---

## 2. Tombol (Buttons)
Lokasi: `SakaButton.kt`

### `SakaButton`
Komponen tombol serbaguna dengan sistem tipe.

| Properti | Tipe | Deskripsi |
| :--- | :--- | :--- |
| `onClick` | `() -> Unit` | Aksi saat diklik. |
| `type` | `SakaButtonType` | `PRIMARY`, `ERROR`, `NEUTRAL`, `ICON`, `LINK`. |
| `text` | `String?` | Label tombol. |
| `icon` | `ImageVector?` | Ikon (khusus tipe `ICON`). |
| `isLoading` | `Boolean` | Menampilkan loader (tipe `PRIMARY`). |

**Cara Pakai:**
```kotlin
SakaButton(
    text = "Sign In",
    type = SakaButtonType.PRIMARY,
    onClick = { /* login */ }
)
```

---

## 3. Input & Form (Text Fields)
Lokasi: `SakaTextField.kt`

### `SakaTextField`
Input teks standar dengan label, helper text, dan validasi.

| Properti | Tipe | Deskripsi |
| :--- | :--- | :--- |
| `isRequired` | `Boolean` | Menampilkan tanda (*) jika true. |
| `isError` | `Boolean` | Mengubah warna border menjadi merah. |
| `errorMessage`| `String?` | Pesan error di bawah input. |
| `helperText` | `String?` | Teks panduan di bawah input. |

### Varian Lain:
- **`SakaPasswordField`**: Input password dengan toggle mata.
- **`SakaSearchField`**: Input pencarian dengan ikon kaca pembesar.
- **`SakaCurrencyField`**: Input dengan suffix unit/mata uang dan divider.

---

## 4. Navigasi
Lokasi: `SakaNavigationBar.kt`, `SakaTabBar.kt`, `SakaDrawer.kt`

### `SakaNavigationBar`
Header layar khusus alur onboarding/form dengan judul rata kiri.

### `SakaTabBar`
Tab bar bawah dinamis dengan animasi smooth. Mendukung mode scroll jika item > 4.

### `SakaModalDrawer`
Drawer samping yang bisa muncul dari **Kiri** atau **Kanan**.

---

## 5. Kontainer
Lokasi: `SakaCard.kt`, `SakaNewsCard.kt`

### `SakaCard`
Base container dengan sistem bayangan (shadow) Figma-compliant (Blur/X/Y).

### `SakaNewsCard`
Komponen kartu siap pakai untuk daftar item berita, menggunakan `SakaCard` dan `SakaAsyncImage`.

---

## 6. Feedback & States
Lokasi: `SakaLoadingView.kt`, `SakaErrorView.kt`, `SakaEmptyView.kt`

- **`SakaLoadingView`**: Layar penuh loading dengan teks pesan.
- **`SakaErrorView`**: Pesan kesalahan dengan tombol "Coba Lagi".
- **`SakaEmptyView`**: Tampilan informatif saat data tidak ada.

---

## 7. Kontrol
Lokasi: `SakaSwitch.kt`

### `SakaSwitch`
Tombol geser modern dengan animasi warna smooth dari Saka Design System.

---

## Aturan Engineering UI
1. **Stateless**: Semua komponen di `:core:ui` tidak boleh memegang ViewModel.
2. **Preview**: Wajib menyediakan `@Preview` untuk setiap komponen baru.
3. **Documentation**: Wajib menulis KDoc dalam Bahasa Indonesia untuk setiap parameter.
