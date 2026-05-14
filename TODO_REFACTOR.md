# TODO Refactor Status

## Task Selesai
- [x] Dokumentasi arsitektur di `docs/architecture-refactor.md`.
- [x] Pemindahan DTO dari `core:network` ke `features:home`.
- [x] Pemindahan Entity dari `core:database` ke `features:home`.
- [x] Pemindahan Repository Implementation, Mapper, dan PagingSource ke `features:home`.
- [x] Pemindahan generic UI components (NewsItem, NewsImage) ke module baru `:features:news:ui`.
- [x] Pembersihan module `:core:data` dan `:data:news`.
- [x] Penambahan mandatory header di file-file utama yang dimodifikasi.
- [x] Update package structure agar sesuai dengan ownership fitur.
- [x] Update `build.gradle.kts` di berbagai module.
- [x] Implementasi Advanced Security Hardening (SQLCipher, SSL Pinning, Root Detection).
- [x] Implementasi Security Convention Plugin (`myapp.android.security`).
- [x] Implementasi Feature-API/Impl separation untuk navigasi.
- [x] Layer Domain 100% Pure Kotlin.

## Task Mendatang / Monitoring
- [ ] Verifikasi build sukses setelah Gradle Sync penuh.
- [ ] Penambahan header untuk seluruh file Kotlin yang belum tersentuh.
- [ ] Verifikasi Unit Test dan UI Test tetap berjalan (diperlukan update import di file test).
- [ ] Pembersihan sisa-sisa build lama (./gradlew clean).

## Catatan
Refactor ini telah memindahkan ownership logic bisnis "News" dari layer core ke module fitur, sesuai dengan prinsip modularization yang enterprise-grade.
