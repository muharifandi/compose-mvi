# Standar Engineering, Tata Kelola & Workflow

Dokumen ini mendefinisikan standar penulisan kode, tata kelola arsitektur, dan alur kerja tim untuk menjaga kualitas dan keterbacaan codebase.

## 1. Naming Convention
- **Class:** `PascalCase` (Contoh: `HomeViewModel`).
- **Function/Variable:** `camelCase` (Contoh: `onRefreshClick`).
- **XML Resource:** `snake_case` (Contoh: `ic_back_button`).
- **Compose Composable:** `PascalCase` dan harus kata benda (Contoh: `PrimaryButton`).

## 2. Tata Kelola Arsitektur (Hard Rules)
- **Boundary Modul:** Modul `:core` dilarang bergantung pada modul `:features`.
- **API/Impl:** Modul `:api` dilarang bergantung pada `:impl`.
- **Pure Kotlin:** Domain Layer wajib 100% bebas dari framework Android (Context, Intent, dll).
- **Stateless UI:** Komponen di `:core:ui` dilarang memegang ViewModel.

## 3. Workflow Pengembangan Fitur
1. **Planning:** Pahami spesifikasi Produk & Desain.
2. **Module Creation:** Gunakan pola `:api` dan `:impl` jika fitur baru.
3. **Domain First:** Buat Entity, Repository Interface, dan UseCase terlebih dahulu.
4. **Implementation:** Selesaikan Data Layer (Retrofit/Room) dan UI Layer (Compose/MVI).
5. **Testing:** Wajib membuat Unit Test untuk UseCase dan ViewModel.
6. **Code Review:** Kirim PR dan pastikan lolos pengecekan Detekt.

## 4. Compose Architecture & Guidelines
- **State Hoisting:** Pindahkan state ke tingkat yang lebih tinggi agar komponen tetap stateless dan mudah diuji.
- **Immutable State:** Selalu gunakan data model yang immutable (`val`) untuk memicu rekomposisi yang efisien.
- **Optimasi Rekomposisi:**
    - Gunakan `remember` untuk menyimpan hasil komputasi berat.
    - Gunakan `derivedStateOf` saat state tergantung pada state lain yang sering berubah.
- **Anti-Pattern:**
    - Dilarang mengirim ViewModel ke sub-komponen kecil. Kirim hanya State dan Callback.
    - Hindari fungsi Composable lebih dari 200 baris.
    - Jangan lakukan komputasi berat (format tanggal, parsing) di dalam body Composable.

## 5. ViewModel & MVI
- **UDF (Unidirectional Data Flow):** Jangan pernah mengubah state langsung dari UI. Selalu kirim Intent ke ViewModel.
- **Immutable State:** Gunakan `data class` dengan `val` untuk State.
- **Lifecycle Aware:** Gunakan `collectAsStateWithLifecycle()` di Screen.

## 6. Documentation Convention
- Gunakan **KDoc** (`/** ... */`) untuk mendokumentasikan logika bisnis yang kompleks.
- Tulis penjelasan parameter fungsi dalam Bahasa Indonesia untuk komponen `:core:ui`.

## 7. Kesimpulan
Disiplin dalam mengikuti alur dan aturan ini adalah kunci kesuksesan proyek enterprise untuk mencegah penumpukan Technical Debt.
