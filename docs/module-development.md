# Panduan Pembuatan Modul Baru

Dokumen ini menjelaskan kapan dan bagaimana membuat modul baru untuk menjaga skalabilitas aplikasi.

## 1. Kapan Membuat Modul Baru?
- **Business Domain Baru:** Fitur yang memiliki logika bisnis berbeda (misal: Payments, Chat).
- **Independent Ownership:** Modul yang akan dikerjakan oleh tim yang berbeda.
- **Build Optimization:** Jika aplikasi sudah terlalu besar, modularisasi membantu kompilasi paralel.

### Kapan TIDAK Perlu Modul Baru?
- Jika hanya berisi 1-2 kelas helper (masukkan ke `:core:common`).
- Jika fitur tersebut sangat kecil dan tidak akan dibagikan (masukkan ke modul fitur yang sudah ada).

---

## 2. Struktur API/Impl Split
Setiap fitur besar wajib memiliki dua modul:
1. **`:api`:** Ringan, berisi interface dan data model publik. Digunakan untuk komunikasi antar modul.
2. **`:impl`:** Berisi implementasi detail. Tidak boleh diakses langsung oleh modul lain selain `:app`.

---

## 3. Aturan Dependensi (Dependency Direction)
Aplikasi kita menggunakan **Directed Acyclic Graph (DAG)**:
- `:features:<name>:impl` -> `:features:<name>:api`
- `:features:<name>:impl` -> `:core:architecture`
- `:features:<name>:impl` -> `:core:ui`
- `:app` -> Semua `:impl`

**DILARANG:** Modul A butuh B dan Modul B butuh A (*Circular Dependency*).

---

## 4. Cara Membuat Modul (Step-by-Step)
1. Buat folder baru di bawah `features/`.
2. Buat file `build.gradle.kts` menggunakan **Convention Plugin** (`myapp.android.feature`).
3. Daftarkan modul di `settings.gradle.kts`.
4. Jalankan **Gradle Sync**.
5. Tambahkan dependensi di modul `:app` jika diperlukan untuk injeksi Hilt.

---

## 5. Menghindari "Module Explosion"
Jangan terlalu agresif membuat modul untuk hal-hal yang sangat kecil. Gabungkan fitur-fitur yang memiliki keterkaitan domain yang sangat erat (*Highly Coupled*) ke dalam satu modul fitur yang sama namun di folder package yang berbeda.

---

## 6. Checklist Modul Baru
- [ ] Apakah sudah menggunakan Convention Plugin?
- [ ] Apakah `api` dan `impl` sudah terpisah?
- [ ] Apakah ada potensi *Circular Dependency*?
- [ ] Apakah penamaan modul sudah sesuai standar (`:features:foo:api`)?
