# Workflow Pengembangan (Engineering Workflow)

Dokumen ini menjelaskan proses kerja harian seorang developer dari mulai merancang fitur hingga melakukan rilis.

## 1. Siklus Pengembangan Fitur
1. **Planning:** Pahami spesifikasi dari Product/Design.
2. **Design Review:** Diskusikan struktur data dan UI bersama tim arsitek.
3. **Module Creation:** Jika diperlukan, buat modul `api` & `impl` baru.
4. **Domain Layer First:** Buat Entity, Repository Interface, dan UseCase.
5. **Data Layer:** Buat implementation Repository, API Service, dan DAO.
6. **Presentation Layer:** Buat State, Intent, Effect, ViewModel, dan UI Compose.
7. **Unit Testing:** Tulis test untuk UseCase dan ViewModel.
8. **Code Review:** Kirim Pull Request (PR) dan tunggu feedback.

---

## 2. Standar Konsistensi Kode
- **Detekt:** Semua PR wajib lolos pengecekan Detekt.
- **Naming:** Gunakan PascalCase untuk Class, camelCase untuk variabel, dan snake_case untuk resource XML.
- **KISS (Keep It Simple, Stupid):** Hindari overengineering. Jangan buat UseCase jika hanya memanggil Repository tanpa logika tambahan (optional, namun disarankan tetap pakai UseCase untuk konsistensi).

---

## 3. Architecture Governance
- **No VM in Compose:** Jangan inisialisasi ViewModel di dalam fungsi Composable terkecil.
- **Single Source of Truth:** Data harus mengalir dari Repository -> UseCase -> ViewModel -> UI.
- **UDF (Unidirectional Data Flow):** Jangan pernah mengubah state langsung dari UI. Selalu kirim Intent ke ViewModel.

---

## 4. Onboarding Checklist untuk Developer Baru
- [ ] Berhasil menjalankan build `./gradlew assembleDebug`.
- [ ] Memahami aliran data MVI.
- [ ] Mengetahui cara kerja Hilt dan modul navigasi.
- [ ] Memahami cara kerja Version Catalog (`libs.versions.toml`).

---

## 5. Kesimpulan
Workflow yang tertata rapi memastikan kualitas kode tetap tinggi meskipun tim terus bertambah besar. Disiplin dalam mengikuti alur ini adalah kunci kesuksesan project enterprise.
