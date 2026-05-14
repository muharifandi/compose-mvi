# Asset Management Guidelines

Dokumen ini menjelaskan bagaimana mengelola aset visual (gambar, ikon, font) secara terorganisir di seluruh modul.

## 1. Penempatan Aset

### A. Global Assets (`:core:ui`)
Tempat untuk aset yang digunakan di seluruh aplikasi.
- **Icon:** Ikon navigasi (Home, Search, Profile), Ikon aksi (Add, Delete).
- **Logo:** Logo utama brand.
- **Font:** Semua file font (.ttf, .otf).

### B. Feature Assets (`:features:<name>:impl`)
Tempat untuk aset yang hanya relevan untuk fitur tersebut.
- **Ilustrasi:** Gambar onboarding, empty state khusus fitur.
- **Background:** Gambar latar belakang spesifik halaman.

---

## 2. Guideline Format File

- **SVG / VectorDrawable:** Prioritas utama untuk semua Ikon dan Ilustrasi sederhana.
  - Keuntungan: Ukuran sangat kecil, tidak pecah saat di-zoom.
- **WebP:** Gunakan untuk foto atau gambar kompleks.
  - Keuntungan: Kompresi lebih baik daripada PNG/JPG.
- **PNG:** Hanya gunakan jika WebP tidak memungkinkan (transparansi sangat kompleks).

---

## 3. Naming Convention (Snake Case)
Semua aset harus menggunakan nama yang deskriptif dan konsisten.
- **Icon:** `ic_<name>.xml` (contoh: `ic_arrow_back.xml`).
- **Image:** `img_<context>_<name>.<ext>` (contoh: `img_onboarding_welcome.webp`).
- **Illustration:** `il_<name>.xml` (contoh: `il_empty_search.xml`).

---

## 4. Ownership & Maintenance
- **Desainer:** Menyediakan aset melalui Figma (Export ke SVG/WebP).
- **Developer:** Melakukan optimasi aset menggunakan tool seperti **Vector Asset Studio** atau **Squoosh** sebelum memasukkannya ke project.
- **Cleanup:** Secara berkala jalankan `Refactor -> Remove Unused Resources` untuk menjaga ukuran APK tetap kecil.

---

## 5. Asset Ownership Rule
Jangan menduplikasi aset. Jika modul fitur butuh ikon yang sudah ada di modul fitur lain, pindahkan ikon tersebut ke `:core:ui`.

---

## 6. Kesimpulan
Manajemen aset yang rapi mencegah pembengkakan ukuran APK dan mempermudah pencarian aset saat melakukan perubahan desain (rebranding).
