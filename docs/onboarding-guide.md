# Panduan Onboarding Developer

Dokumen ini membantu developer baru memahami codebase dan mulai berkontribusi dalam waktu singkat.

## 1. Persiapan Lingkungan (Setup)
- **JDK:** Gunakan Java 17.
- **Android Studio:** Versi terbaru (Ladybug atau Koala).
- **Git:** Pastikan sudah dikonfigurasi dengan email perusahaan.

## 2. Urutan Memahami Codebase
Jangan mencoba memahami semuanya sekaligus. Ikuti urutan ini:
1. **[Deep Dive: Dependencies & Plugins](dependencies-deep-dive.md)**: Pahami "MENGAPA" kita menggunakan stack teknologi ini.
2. **`:core:architecture`**: Pahami base class MVI (`BaseViewModel`).
3. **`:features:splash:impl`**: Lihat implementasi fitur paling sederhana.
4. **`:navigation`**: Pahami bagaimana navigasi antar modul bekerja.
5. **`:app`**: Lihat bagaimana Hilt menyatukan semua modul.

## 3. Workflow Pengembangan
Aplikasi ini menggunakan **Git Flow** yang disederhanakan:
- **`main`**: Branch stabil (Production).
- **`develop`**: Branch integrasi fitur.
- **`feature/name`**: Branch untuk pengerjaan fitur baru.

### Langkah-langkah:
1. Buat branch dari `develop`: `git checkout -b feature/news-detail`.
2. Kerjakan fitur sesuai [Feature Development Guide](feature-development.md).
3. Jalankan unit test: `./gradlew test`.
4. Lakukan lint check: `./gradlew detekt`.
5. Buat Pull Request ke `develop`.

## 4. Standar Pull Request (PR)
PR yang baik harus berisi:
- Deskripsi fitur/perbaikan.
- Screenshot atau Screen Recording (jika ada perubahan UI).
- Bukti unit test berhasil dijalankan.

## 5. Code Review Guideline
- Berikan feedback yang konstruktif dan sopan.
- Fokus pada arsitektur dan potensi bug.
- Gunakan fitur "Request Changes" jika ada pelanggaran arsitektur fatal.
