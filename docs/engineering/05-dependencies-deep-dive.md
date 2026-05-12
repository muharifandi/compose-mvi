# Deep Dive: Dependencies & Plugins

Dokumen ini memberikan penjelasan mendalam mengenai "alat-alat" yang membangun proyek ini. Kami akan menggunakan analogi dunia nyata agar Anda memahami **MENGAPA** kita memilih teknologi ini, bukan sekadar **APA** teknologinya.

---

## 1. Meta-Manager: Version Catalog (`libs.versions.toml`)

Sebelum masuk ke library, kita harus mengenal pengelolanya.
*   **Analogy**: Bayangkan Anda sedang membangun gedung besar. Alih-alih setiap mandor memesan semen sendiri-sendiri dengan merek berbeda, Anda memiliki **Buku Induk Logistik**. Semua orang harus memesan dari buku itu agar merek dan kualitasnya seragam.
*   **Fungsi**: Memastikan semua modul (14+ modul) menggunakan versi library yang sama. Jika ingin update Retrofit, cukup ubah satu angka di satu file, dan seluruh aplikasi akan terupdate.

---

## 2. Gradle Plugins (The Specialized Tools)

Plugin adalah "alat bantu" yang memberikan kemampuan baru pada sistem build Gradle kita.

### A. Android Application & Library
*   **Analogy**: Cetak biru (Blueprint) bangunan. Satu untuk gedung utama (App), satu untuk paviliun (Library).
*   **Kegunaan**: Memberitahu Gradle bagaimana cara mengubah kode Kotlin menjadi file `.apk` atau `.aar`.

### B. Kotlin Serialization & Gson
*   **Analogy**: Alat penerjemah bahasa (Translator).
*   **Contoh**: Data dari internet berbentuk teks (JSON). Kotlin Serialization mengubah teks itu menjadi objek Kotlin yang bisa kita olah.

### C. KSP (Kotlin Symbol Processing)
*   **Analogy**: Asisten cerdas yang menulis kode membosankan untuk Anda.
*   **Kegunaan**: Digunakan oleh Room untuk men-generate kode database. KSP jauh lebih cepat daripada pendahulunya (KAPT).

---

## 3. Core Libraries (The Building Materials)

### A. Jetpack Compose (The UI Artist)
*   **Analogy**: LEGO vs Tanah Liat.
    *   **Tradisional (XML)**: Seperti membentuk tanah liat. Susah diubah kalau sudah kering, dan kodenya terpisah dari logika.
    *   **Compose**: Seperti LEGO. Anda cukup bilang "Saya mau balok merah di sini", dan sistem akan menyusunnya. Jika State berubah, LEGO-nya otomatis tersusun ulang.
*   **Contoh**: Membuat daftar berita cukup dengan `LazyColumn`. Tidak perlu lagi `Adapter` atau `ViewHolder` yang rumit.

### B. Hilt (The Distribution System)
*   **Analogy**: Instalasi pipa air dan kabel listrik otomatis.
*   **Masalah**: Tanpa Hilt, Anda harus mengoper-oper objek (Repository) dari manual ke ViewModel, lalu ke Screen. Sangat repot.
*   **Solusi**: Anda cukup bilang `@Inject`, dan Hilt akan "menyambungkan kabel" yang tepat dari gudang data ke tempat yang membutuhkan secara otomatis.

### C. Retrofit & OkHttp (The Diplomat & Postman)
*   **Analogy**: Retrofit adalah **Diplomat** (yang menyusun surat resmi), OkHttp adalah **Kurir/Postman** (yang mengantar surat ke alamat tujuan).
*   **Fungsi**: Menangani komunikasi dengan server. OkHttp menangani hal teknis seperti *timeout*, *logging*, dan *cache*, sementara Retrofit memudahkan kita memanggil endpoint API seperti memanggil fungsi Kotlin biasa.

### D. Room (The Personal Library)
*   **Analogy**: Lemari arsip yang sangat rapi di dalam rumah.
*   **Fungsi**: Menyimpan data di dalam HP (Offline Cache). Room memastikan data yang kita simpan "aman secara tipe" (Type-safe), artinya kita tidak bisa memasukkan data "Kucing" ke dalam kotak "Artikel".

### E. Coroutines & Flow (The Conveyor Belt)
*   **Analogy**: Ban berjalan (Conveyor Belt) di pabrik.
*   **Fungsi**: Menangani pekerjaan berat di latar belakang agar layar HP tidak macet (ANR - App Not Responding). 
    *   **Coroutines**: Pekerjaan satu kali jalan.
    *   **Flow**: Aliran data terus-menerus (seperti air yang mengalir dari database ke layar).

### F. Paging 3 (The Infinite Scroll)
*   **Analogy**: Membaca buku halaman demi halaman.
*   **Masalah**: Jika Anda punya 1 juta berita, Anda tidak mungkin memuat semuanya sekaligus (HP akan meledak/lag).
*   **Solusi**: Paging 3 hanya memuat 20 berita pertama. Saat user scroll ke bawah, ia otomatis mengambil 20 lagi. User merasa daftar itu tak terhingga, padahal memori HP tetap hemat.

### G. Coil (The Instant Photo Frame)
*   **Analogy**: Bingkai foto ajaib yang bisa mendownload gambarnya sendiri.
*   **Fungsi**: Menampilkan gambar dari URL internet ke layar Compose. Coil sangat ringan dan dirancang khusus untuk Kotlin.

---

## 4. Testing Stack (The Quality Control)

*   **JUnit**: Alat uji dasar (Apakah 1+1 benar-benar 2?).
*   **MockK**: Alat pembuat "pemeran pengganti". Saat ngetes fitur Home, kita tidak perlu internet beneran, kita pakai MockK untuk pura-pura jadi internet.
*   **Turbine**: Khusus untuk ngetes **Flow** (aliran data). Memastikan air yang mengalir di pipa adalah air yang benar.

---

## Kesimpulan: Mengapa Repot Pakai Sebanyak Ini?

Mungkin Anda bertanya: *"Kenapa tidak pakai library standar saja?"*
Jawabannya adalah **Skalabilitas**. Dengan kombinasi library di atas:
1.  Aplikasi Anda **tidak akan lemot** saat data bertambah banyak (Paging 3 + Coroutines).
2.  Aplikasi Anda **tidak akan berantakan** saat fitur bertambah banyak (Hilt + Modularization).
3.  Aplikasi Anda **sangat mudah dites** sebelum dirilis ke user (MockK + JUnit).

Setiap library dipilih karena ia adalah yang **terbaik di kelasnya** dan didukung penuh oleh komunitas Android global.
