# Saka Design System - UI Component Guide

Dokumen ini adalah katalog resmi dan panduan penggunaan seluruh komponen UI yang tersedia di modul `:core:ui`. Komponen ini dirancang untuk memastikan konsistensi visual, aksesibilitas, dan skalabilitas di seluruh aplikasi **SakaAndroid**.

---

## 1. Fondasi & Tema
Seluruh komponen menggunakan identitas visual **Saka**.
- **Warna**: `SakaTheme.colors` (Primary, Neutral, Semantic).
- **Tipografi**: Font **Poppins** melalui `SakaTheme.typography`.

---

## 2. Perbankan (SakaCreditCard)
Komponen kartu kredit premium yang mendukung masking data sensitif dan latar belakang fleksibel (URL atau Drawable).

### Properti
| Properti | Tipe | Deskripsi |
| :--- | :--- | :--- |
| `holderName` | `String` | Nama pemilik kartu (di-mask jika `isVisible` false). |
| `cardType` | `String` | Kategori kartu (contoh: "Visa Gold", "Platinum"). |
| `cardNumber` | `String` | Nomor kartu 16 digit (di-mask jika `isVisible` false). |
| `balance` | `String` | Saldo tersedia (di-mask jika `isVisible` false). |
| `gradientColors` | `List<Color>` | Warna fallback jika `backgroundModel` null. |
| `isVisible` | `Boolean` | Status visibilitas data sensitif. |
| `onToggleVisibility` | `() -> Unit` | Callback saat ikon mata diklik. |
| `backgroundModel` | `Any?` | Sumber gambar (URL String, Drawable ID, atau File). |
| `modifier` | `Modifier` | Pengaturan layout (size, padding, dll). |

### Contoh Penggunaan
```kotlin
SakaCreditCard(
    holderName = "John Smith",
    cardType = "Amazon Platinum",
    cardNumber = "4756 1234 5678 9018",
    balance = "$3,469.52",
    isVisible = isVisible,
    onToggleVisibility = { isVisible = !isVisible },
    backgroundModel = R.drawable.ic_card_bg
)
```

---

## 3. Tombol (SakaButton)
Komponen tombol tunggal yang mendukung berbagai tipe melalui parameter `type`.

### Properti
| Properti | Tipe | Deskripsi |
| :--- | :--- | :--- |
| `text` | `String?` | Label tombol. |
| `onClick` | `() -> Unit` | Aksi saat tombol diklik. |
| `type` | `SakaButtonType` | `PRIMARY`, `ERROR`, `NEUTRAL`, `ICON`, `LINK`. |
| `icon` | `ImageVector?` | Ikon (khusus tipe `ICON`). |
| `isLoading` | `Boolean` | Menampilkan loader (khusus tipe `PRIMARY`). |
| `enabled` | `Boolean` | Status aktif tombol (Default: true). |

### Contoh Penggunaan
```kotlin
SakaButton(
    text = "Sign In",
    onClick = { /* login action */ },
    type = SakaButtonType.PRIMARY,
    isLoading = false
)
```

---

## 4. Input Teks (SakaTextField)
Tersedia dalam berbagai varian (Base, Password, Currency) untuk kebutuhan form.

### Properti
| Properti | Tipe | Deskripsi |
| :--- | :--- | :--- |
| `value` | `String` | Nilai teks input. |
| `onValueChange` | `(String) -> Unit` | Callback saat teks berubah. |
| `label` | `String` | Label di atas input. |
| `placeholder` | `String` | Teks bantuan di dalam input. |
| `isError` | `Boolean` | Menampilkan status error (merah). |
| `errorMessage` | `String?` | Pesan error di bawah input. |
| `isRequired` | `Boolean` | Menampilkan tanda bintang (*). |

### Contoh Penggunaan
```kotlin
SakaTextField(
    value = username,
    onValueChange = { username = it },
    label = "Username",
    isRequired = true
)
```

---

## 5. Navigasi (TopAppBar, NavigationBar & TabBar)

### `SakaTopAppBar`
Header standar untuk layar fitur utama.
- **Properti**: `title` (String), `onBackClick` (Unit), `actions` (RowScope).

### `SakaNavigationBar`
Header khusus alur login/onboarding dengan judul rata kiri.
- **Properti**: `title` (String), `onBackClick` (Unit), `backgroundColor` (Color).

### `SakaTabBar`
Tab bar bawah dinamis dengan animasi transisi halus.
- **Properti**: `items` (List<SakaTabItem>), `selectedIndex` (Int), `onItemSelected` (Unit).

---

## 6. Kontainer (SakaCard, SakaScaffold & SakaModalDrawer)

### `SakaCard`
Base container dengan sistem bayangan (*shadow*) kustom Saka.
- **Properti**: `isSmallShadow` (Boolean), `shape` (Shape), `backgroundColor` (Color).

### `SakaScaffold`
Struktur layar utama yang menangani insets sistem (status bar & navigation bar) secara otomatis.
- **Properti**: `topBar`, `bottomBar`, `floatingActionButton`, `applyNavigationPadding` (Boolean).

### `SakaModalDrawer`
Drawer navigasi samping yang mendukung orientasi dari kiri atau kanan.
- **Properti**: `side` (SakaDrawerSide), `drawerState` (DrawerState), `drawerContent` (Composable).

---

## 7. Kontrol (SakaSwitch & SakaCheckbox)

### `SakaSwitch`
Tombol geser modern untuk pengaturan status On/Off.
```kotlin
SakaSwitch(checked = isEnabled, onCheckedChange = { isEnabled = it })
```

### `SakaCheckbox`
Input pilihan centang standar.
```kotlin
SakaCheckbox(checked = isSelected, onCheckedChange = { isSelected = it })
```

---

## 8. Feedback States (Loading, Error, Empty)
Komponen standar untuk menangani berbagai kondisi state layar.

- **`SakaLoadingView(message: String)`**: Menampilkan animasi pemuatan.
- **`SakaErrorView(message: String, onRetry: () -> Unit)`**: Menampilkan pesan error dan tombol coba lagi.
- **`SakaEmptyView(message: String)`**: Menampilkan ilustrasi saat data kosong.

---

## 9. Media & Pencarian (AsyncImage & SearchBar)

### `SakaAsyncImage`
Pemuatan gambar asinkron Coil dengan dukungan URL, Drawable, dan File.
- **Properti**: `model` (Any), `contentScale`, `crossfade` (Boolean), `showPlaceholder` (Boolean).

### `SakaSearchBar`
Input pencarian terintegrasi dengan state internal otomatis.
- **Properti**: `onSearch` (String -> Unit), `placeholder` (String).

---

## 10. Label & Status (SakaTextLabel)

### `SakaTextLabel`
Komponen label status/tag dengan varian warna semantik.
- **Varian**: `PRIMARY`, `SUCCESS`, `WARNING`, `ERROR`, `INFO`, `NEUTRAL`.
- **Contoh**: `SakaTextLabel(text = "Completed", variant = SakaTextLabelVariant.SUCCESS)`

---

## 11. Tata Letak (SakaResponsiveLayout)

### `SakaResponsiveLayout`
Komponen pembantu untuk menangani perubahan layout otomatis berdasarkan ukuran layar (Phone vs Tablet).

---

## Aturan Engineering UI
1. **Stateless**: Komponen tidak boleh memegang ViewModel.
2. **Interactive Preview**: Setiap file wajib memiliki fungsi `@Preview`.
3. **KDoc**: Dokumentasi parameter wajib ditulis dalam Bahasa Indonesia.
