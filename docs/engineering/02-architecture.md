# Clean Architecture & Project Structure

Proyek ini menerapkan **Clean Architecture** yang dimodifikasi untuk mendukung modularisasi berbasis fitur.

## 1. Lapisan Arsitektur (Layers)

### Domain Layer (`:domain:[feature]`)
- **Tanggung Jawab**: Jantung dari aplikasi. Berisi logika bisnis murni.
- **Isi**: Model Domain, Interface Repository, UseCase.
- **Aturan Ketat**: Harus berupa **Pure Kotlin Module**. Tidak boleh ada dependensi ke Android SDK, Context, Compose, Room, atau Retrofit.

### Data Layer (Berada di dalam modul Fitur atau Shared Data)
- **Tanggung Jawab**: Implementasi dari interface repository. Mengelola sumber data (Remote & Local).
- **Isi**: Repository Implementation, DTO, Entity, DAO, Mapper.
- **Arah Dependensi**: Bergantung pada Domain Layer.

### Presentation Layer (Berada di dalam modul Fitur)
- **Tanggung Jawab**: Menampilkan data ke pengguna dan menangani interaksi pengguna.
- **Isi**: Composables (UI), ViewModel, MVI State/Intent/Effect.
- **Arah Dependensi**: Bergantung pada Domain Layer.

## 2. Struktur Modul

### `:app`
Modul "Glue" yang menyatukan semuanya. Berisi `MainActivity`, konfigurasi Hilt, dan Nav Host.

### `:features:[name]`
Modul yang berisi fitur spesifik (misal: `home`, `bookmark`). Modul ini memiliki ownership penuh terhadap UI dan logika data fiturnya sendiri.

### `:features:news:ui`
Modul UI bersama khusus untuk domain "News". Berisi komponen seperti `NewsItem` yang digunakan oleh banyak fitur.

### `:core:[name]`
Modul infrastruktur yang generic:
- `:core:ui`: Design System murni (Buttons, Colors, Theme).
- `:core:network`: Konfigurasi Retrofit & Interceptor.
- `:core:database`: Konfigurasi Room Database.
- `:core:common`: Base classes (MVI, ResultState).
- `:core:testing`: Test utilities & Robots.

### `:navigation`
Modul tipis yang mendefinisikan kontrak navigasi antar fitur untuk menghindari ketergantungan langsung antar modul fitur.

##  arah Dependensi (Dependency Direction)
Arah dependensi selalu mengarah ke dalam:
**Presentation -> Domain <- Data**
**Feature -> Core**
**Feature -> Domain**
