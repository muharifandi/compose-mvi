# Checklist Pengembangan Fitur & Arsitektur

Gunakan checklist ini sebelum mengirim Pull Request untuk memastikan kualitas fitur.

## 1. Feature Checklist
- [ ] Fitur sudah modular (API/Impl).
- [ ] UI sudah mendukung Dark Mode.
- [ ] Semua Image memiliki `contentDescription`.
- [ ] String sudah diletakkan di `strings.xml` (tidak hardcoded).
- [ ] Area klik minimal 48x48dp.
- [ ] Fitur sudah terdaftar di `AppNavHost`.

## 2. Architecture Checklist
- [ ] Menggunakan MVI (State, Intent, Effect).
- [ ] ViewModel tidak memegang referensi Context/View.
- [ ] Domain Layer tidak memiliki dependensi Android framework.
- [ ] Repository Implementation berada di Data Layer.
- [ ] Menggunakan UseCase untuk logika bisnis.
- [ ] Data ditransformasi menggunakan Mapper (DTO -> Model).

## 3. Code Quality Checklist
- [ ] Tidak ada fungsi Composable yang lebih dari 200 baris.
- [ ] Tidak ada *Magic Numbers* (gunakan konstanta atau Dimens).
- [ ] Lolos static analysis (Detekt).
- [ ] Menambahkan Unit Test untuk UseCase (minimal 80% coverage).
- [ ] Menambahkan Unit Test untuk ViewModel.

## 4. Performance & Security Checklist
- [ ] Menggunakan `LazyColumn` untuk list data besar.
- [ ] Tidak ada komputasi berat di dalam body Composable.
- [ ] Data sensitif tidak disimpan di `SharedPreferences` (gunakan DataStore/Encrypted).
- [ ] API Call sudah menggunakan `ResultState` untuk handling error.

---

**Catatan:** Jika ada item yang tidak terpenuhi, harap berikan alasan yang kuat di dalam deskripsi Pull Request.
