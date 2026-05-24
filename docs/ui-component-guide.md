# Saka Design System - UI Component Guide

Dokumen ini adalah katalog resmi dan panduan penggunaan seluruh komponen UI yang tersedia di modul `:core:ui`. Komponen ini dirancang untuk memastikan konsistensi visual dan aksesibilitas di seluruh aplikasi **SakaAndroid**.

## Daftar Isi
1. [Prinsip Design System](#prinsip-design-system)
2. [Fondasi & Tema](#1-fondasi--tema)
3. [Tombol (SakaButton)](#2-tombol-sakabutton)
4. [Input Teks (SakaTextField)](#3-input-teks-sakatextfield)
5. [Navigasi Atas (TopAppBar & NavigationBar)](#4-navigasi-atas-topappbar--navigationbar)
6. [Navigasi Utama (TabBar & ModalDrawer)](#5-navigasi-utama-tabbar--modaldrawer)
7. [Kontainer (SakaCard & SakaNewsCard)](#6-kontainer-sakacard--sakanewscard)
8. [Kontrol (SakaSwitch)](#7-kontrol-sakaswitch)
9. [Feedback States (Loading, Error, Empty)](#8-feedback-states-loading-error-empty)
10. [Media & Pencarian (AsyncImage & SearchBar)](#9-media--pencarian-asyncimage--searchbar)
11. [Label & Badge (SakaTextLabel)](#10-label--badge-sakatextlabel)

---

## Prinsip Design System

### A. Dark Mode Support
Semua komponen di `:core:ui` wajib mendukung **Dark Mode**. Gunakan warna dari `SakaTheme.colors` agar dapat berubah secara otomatis. Hindari penggunaan `Color.White` secara hardcoded.

### B. Aksesibilitas (Accessibility)
UI harus inklusif bagi semua pengguna:
- **Content Description:** Wajib untuk Image/Icon fungsional.
- **Minimum Touch Target:** Pastikan area klik minimal 48x48dp.

### C. Spasi (Spacing)
Gunakan sistem kelipatan 4dp/8dp untuk margin dan padding. Gunakan konstanta dari modul `:core:ui` jika tersedia.

---

## 1. Fondasi & Tema
Seluruh komponen menggunakan identitas visual **Saka**.
- **Warna**: `SakaTheme.colors` (Primary, Neutral, Semantic).
- **Tipografi**: Font **Poppins** via `SakaTheme.typography`.

---

## 2. Tombol (SakaButton)
Komponen tombol tunggal yang mendukung berbagai tipe melalui parameter `type`.

### `SakaButton`
| Properti | Tipe | Deskripsi |
| :--- | :--- | :--- |
| `type` | `SakaButtonType` | `PRIMARY`, `ERROR`, `NEUTRAL`, `ICON`, `LINK`. |
| `text` | `String?` | Label tombol. |
| `icon` | `ImageVector?` | Ikon (khusus tipe `ICON`). |
| `isLoading` | `Boolean` | Menampilkan loader (khusus tipe `PRIMARY`). |

**Contoh Kode:**
```kotlin
// Tombol Utama
SakaButton(type = SakaButtonType.PRIMARY, text = "Sign In", onClick = { })

// Tombol Loading
SakaButton(type = SakaButtonType.PRIMARY, text = "Sign In", isLoading = true, onClick = { })

// Tombol Bahaya (Merah)
SakaButton(type = SakaButtonType.ERROR, text = "Delete Account", onClick = { })

// Tombol Ikon (Lingkaran)
SakaButton(type = SakaButtonType.ICON, icon = Icons.Default.Add, onClick = { })
```

---

## 3. Input Teks (SakaTextField)
Tersedia dalam berbagai varian untuk kebutuhan form yang berbeda.

### `SakaTextField` (Base)
Mendukung label, asterisk (*), dan pesan error.
```kotlin
SakaTextField(
    value = text,
    onValueChange = { text = it },
    label = "Username",
    isRequired = true,
    isError = text.isEmpty(),
    errorMessage = "Wajib diisi"
)
```

### `SakaPasswordField`
Input password dengan toggle mata otomatis serta dukungan validasi error.
```kotlin
SakaPasswordField(
    value = pass, 
    onValueChange = { pass = it }, 
    label = "Password",
    isError = pass.length < 8,
    errorMessage = "Password minimal 8 karakter"
)
```

### `SakaCurrencyField`
Input dengan suffix mata uang dan garis pemisah.
```kotlin
SakaCurrencyField(value = amt, onValueChange = { amt = it }, label = "Amount", currency = "USD")
```

---

## 4. Navigasi Atas (TopAppBar & NavigationBar)

### `SakaTopAppBar`
Header standar untuk layar fitur.
```kotlin
SakaTopAppBar(title = "Detail News", onBackClick = { navigator.back() })
```

### `SakaNavigationBar`
Header khusus alur login/onboarding dengan judul rata kiri.
```kotlin
SakaNavigationBar(title = "Forgot password", onBackClick = { })
```

---

## 5. Navigasi Utama (TabBar & ModalDrawer)

### `SakaTabBar`
Tab bar bawah dinamis dengan animasi smooth. Otomatis bisa di-scroll jika item > 4.
```kotlin
SakaTabBar(
    items = listOf(SakaTabItem("Home", Icons.Default.Home), ...),
    selectedIndex = activeIndex,
    onItemSelected = { activeIndex = it }
)
```

### `SakaModalDrawer`
Drawer samping yang bisa muncul dari `LEFT` atau `RIGHT`.
```kotlin
SakaModalDrawer(
    side = SakaDrawerSide.RIGHT,
    drawerState = state,
    drawerContent = {
        SakaDrawerItem(label = "Profile", icon = Icons.Default.Person, isSelected = true, onClick = { })
    }
) {
    // Content
}
```

---

## 6. Kontainer (SakaCard & SakaNewsCard)

### `SakaCard`
Base container dengan bayangan (shadow) Figma-compliant.
```kotlin
SakaCard(isSmallShadow = false) {
    Text("Konten di dalam kartu")
}
```

### `SakaNewsCard`
Komponen kartu siap pakai untuk daftar item berita.
```kotlin
SakaNewsCard(
    title = "Judul Berita",
    imageUrl = "https://...",
    description = "Ringkasan berita...",
    onClick = { }
)
```

---

## 7. Kontrol (SakaSwitch)

### `SakaSwitch`
Tombol geser modern dengan animasi warna.
```kotlin
SakaSwitch(checked = isEnabled, onCheckedChange = { isEnabled = it })
```

---

## 8. Feedback States (Loading, Error, Empty)

### `SakaLoadingView`
```kotlin
SakaLoadingView(message = "Memuat data...")
```

### `SakaErrorView`
```kotlin
SakaErrorView(message = "Koneksi gagal", onRetry = { fetchData() })
```

### `SakaEmptyView`
```kotlin
SakaEmptyView(message = "Tidak ada riwayat transaksi")
```

---

## 9. Media & Pencarian (AsyncImage & SearchBar)

### `SakaAsyncImage`
Pemuatan gambar asinkron yang dioptimalkan dengan Coil.
```kotlin
SakaAsyncImage(model = "url_gambar", modifier = Modifier.size(100.dp))
```

### `SakaSearchBar`
Input pencarian terintegrasi untuk header list.
```kotlin
SakaSearchBar(onSearch = { query -> /* Filter list */ })
```

---

## 10. Label & Badge (SakaTextLabel)

### `SakaTextLabel`
Komponen label status atau tag kustom dengan berbagai varian warna.
| Properti | Tipe | Deskripsi |
| :--- | :--- | :--- |
| `text` | `String` | Teks yang ditampilkan. |
| `variant` | `SakaTextLabelVariant` | `PRIMARY`, `SUCCESS`, `WARNING`, `ERROR`, `INFO`, `NEUTRAL`. |

**Contoh Kode:**
```kotlin
// Label Success
SakaTextLabel(text = "Lunas", variant = SakaTextLabelVariant.SUCCESS)

// Label Error
SakaTextLabel(text = "Gagal", variant = SakaTextLabelVariant.ERROR)
```

---

## Aturan Engineering UI
1. **Stateless**: Komponen tidak boleh memegang ViewModel.
2. **Interactive Preview**: Setiap file wajib memiliki fungsi `@Preview`.
3. **KDoc**: Dokumentasi parameter wajib ditulis dalam Bahasa Indonesia.
