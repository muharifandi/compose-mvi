# Design System & UI Foundation

Dokumen ini menjelaskan fondasi visual dan sistem tema yang digunakan untuk memastikan konsistensi UI di seluruh modul aplikasi.

## 1. Sistem Tema (Material3)
Kami menggunakan **Material Design 3** sebagai basis sistem tema. Tema didefinisikan secara terpusat di modul `:core:ui`.

### A. Skema Warna (Color Scheme)
Warna mendukung **Dark Mode** secara otomatis.
- **Primary:** Warna utama brand.
- **Secondary/Tertiary:** Warna aksen.
- **Surface/Background:** Warna latar belakang komponen.

### B. Tipografi (Typography)
Tipografi menggunakan skala standar Material3 (Display, Headline, Title, Body, Label).
- **Font:** Inter atau Roboto (Tergantung konfigurasi).
- **Penggunaan:** Selalu gunakan `MaterialTheme.typography.bodyMedium` daripada mendefinisikan `FontSize` secara manual.

### C. Spasi (Spacing)
Jangan menggunakan angka ajaib (*magic numbers*). Gunakan sistem kelipatan 4dp/8dp.
```kotlin
object AppSpacing {
    val Small = 4.dp
    val Medium = 8.dp
    val Large = 16.dp
    val ExtraLarge = 24.dp
}
```

---

## 2. Dark Mode Support
Semua komponen di `:core:ui` harus menggunakan warna dari `MaterialTheme.colorScheme` agar dapat berubah secara otomatis saat sistem berpindah ke Dark Mode. Hindari penggunaan `Color.White` atau `Color.Black` secara langsung.

---

## 3. Aksesibilitas (Accessibility)
UI harus inklusif bagi semua pengguna.
- **Content Description:** Wajib untuk Image/Icon yang memiliki fungsi.
- **Click Labels:** Gunakan `Modifier.semantics` untuk menjelaskan aksi tombol bagi pengguna TalkBack.
- **Minimum Touch Target:** Pastikan area klik minimal 48x48dp.

---

## 4. Struktur Modul `:core:ui`
Modul ini adalah "Rumah" bagi Design System:
- `theme/`: Definisi Color, Type, dan Theme.
- `component/`: Reusable components (Button, Card, TextField).
- `util/`: Modifier extensions dan UI helpers.

---

## 5. Kesimpulan
Design System bukan hanya tentang estetika, tapi tentang efisiensi pengembangan. Dengan sistem yang matang, penambahan fitur baru menjadi lebih cepat karena komponen visual sudah tersedia.
