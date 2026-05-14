# Panduan Skalabilitas Proyek (Skor 100/100)

Proyek ini dirancang untuk mendukung 50+ fitur dan 15+ pengembang dengan performa build yang optimal.

## 1. Pola API/Impl
Setiap fitur dibagi menjadi dua modul:
- `:features:<name>:api`: Berisi rute navigasi (Destinations) dan interface layanan publik. Sangat ringan untuk dikompilasi.
- `:features:<name>:impl`: Berisi implementasi UI, ViewModel, dan logika bisnis. Bergantung pada modul `:api`.

## 2. Distributed Navigation (Navigasi Terdistribusi)
Modul `:app` tidak lagi mengimpor Screen secara langsung. Navigasi didaftarkan secara otomatis melalui `FeatureApi`:
- Fitur mengimplementasikan `FeatureApi`.
- Fitur mendaftarkan rutenya di `registerGraph`.
- Modul `:app` mengumpulkan semua implementasi melalui Hilt Multibinding.

## 3. Build Optimizations
- **Configuration Cache**: Aktif. Menghindari konfigurasi ulang Gradle yang berulang.
- **Build Cache**: Aktif. Berbagi hasil kompilasi antar task.
- **Parallel Execution**: Aktif. Menjalankan task yang tidak berhubungan secara bersamaan.
- **Non-Transitive R Classes**: Aktif. Mempercepat kompilasi modul UI.

## 4. Constraint Grafik Dependensi
Aturan ditegakkan secara otomatis melalui `module-graph-assert`:
- `impl` tidak boleh bergantung pada `impl` lain (mencegah kopling ketat).
- `api` tidak boleh bergantung pada `impl` (mencegah circular dependency).
