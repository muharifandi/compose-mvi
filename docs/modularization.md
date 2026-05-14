# Modularisasi (Scalable Architecture)

Dokumen ini menjelaskan bagaimana aplikasi dipecah menjadi modul-modul kecil untuk skalabilitas tim dan performa build yang lebih cepat.

## 1. Jenis Modul

### A. Core Modules (`:core`)
Modul yang berisi kode yang digunakan oleh banyak fitur.
- `:core:ui`: Design system, komponen Compose global, tema.
- `:core:network`: Konfigurasi Retrofit, OkHttp, dan base API service.
- `:core:architecture`: Base class untuk MVI, ViewModel, dan Navigasi.
- `:core:common`: Utility, Extension, dan Security logic.

### B. Feature Modules (`:features`)
Setiap fitur besar harus dipisah. Kami menggunakan pola **API/Impl Split**.
- **`:api` module:** Berisi kontrak navigasi (Destination) dan data model publik. Ringan dan cepat di-compile.
- **`:impl` module:** Berisi UI (Compose), ViewModel, UseCase, dan Repository.

---

## 2. Mengapa Memisahkan API & Impl?
1. **Build Speed:** Saat modul `:impl` berubah, modul fitur lain yang hanya tergantung pada `:api` tidak perlu di-compile ulang.
2. **Encapsulation:** Detail implementasi (seperti library internal) tidak bocor ke modul lain.
3. **Decoupling:** Menghindari *Cyclic Dependency*.

---

## 3. Aturan Dependensi (Dependency Direction)
- `Feature Impl` -> `Feature Api`
- `Feature Impl` -> `Core Modules`
- `App Module` -> Semua `Feature Impl` (untuk Injeksi Hilt)

---

## 4. Kapan Harus Membuat Modul Baru?
- **YA:** Jika fitur tersebut memiliki pemilik tim yang berbeda.
- **YA:** Jika kode tersebut akan digunakan di aplikasi lain (reusable).
- **TIDAK:** Jika hanya berisi 1-2 kelas kecil yang tidak akan dibagikan.
- **TIDAK:** Jika pemisahan justru membuat koordinasi antar file menjadi sangat sulit tanpa alasan performa.

---

## 5. Menghindari Cyclic Dependency
Jika Modul A butuh Modul B, dan B butuh A:
1. Pindahkan kode yang dibutuhkan ke `:core:common` atau `:core:architecture`.
2. Gunakan **Interface Delegation** atau **Event Bus/Navigation Manager**.

---

## 6. Contoh Struktur Fitur
```text
:features:news
  ├── :api   (Kontrak Navigasi & Model)
  └── :impl  (UI & Bisnis Logika)
```

Dengan struktur ini, aplikasi kita siap untuk **Dynamic Feature Module** di masa depan jika diperlukan.
