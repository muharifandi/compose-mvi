# Tata Kelola Arsitektur (Architecture Governance)

Dokumen ini menjelaskan aturan keras (*hard rules*) untuk menjaga integritas Clean Architecture dan Modularisasi.

## 1. Boundary Modul (Aturan Larangan)
- **TIDAK BOLEH:** Modul `:core` bergantung pada modul `:features`.
- **TIDAK BOLEH:** Modul `:features:<name>:api` bergantung pada `:features:<name>:impl`.
- **TIDAK BOLEH:** Modul `:impl` diekspos ke modul fitur lain (Gunakan `:api`).
- **TIDAK BOLEH:** Modul `:core:ui` memiliki logika bisnis atau network call.

## 2. Layer Ownership (Tanggung Jawab)
- **Domain Layer:** Pemilik aturan bisnis. Tidak boleh tersentuh framework Android.
- **Data Layer:** Pemilik detail infrastruktur (Retrofit, Room). Harus menyembunyikan detail ini di balik interface Repository.
- **Presentation Layer:** Pemilik interaksi user. Hanya berkomunikasi dengan UseCase.

## 3. Anti-Pattern & Pelanggaran Arsitektur
- **God Module:** Menumpuk semua utility di `:core:common`. Jika terlalu besar, pecah menjadi sub-core modul.
- **Dependency Leak:** Mengirimkan DTO (Network Model) langsung ke UI tanpa di-map ke Domain Model.
- **Shared Module Pollution:** Menambah dependensi ke modul yang sering digunakan hanya untuk satu file kecil.

## 4. Contoh Benar vs Salah

### ❌ SALAH (Leaking Android Framework to Domain)
```kotlin
// Di Domain Layer
class MyUseCase(private val context: Context) { ... }
```

### ✅ BENAR (Pure Kotlin Domain)
```kotlin
// Di Domain Layer
class MyUseCase(private val stringProvider: StringProvider) { ... }
// Implementasi StringProvider di Core/Common (Data/UI layer)
```

## 5. SOLID & Clean Architecture Rules
- **Dependency Inversion:** UI tidak boleh buat instance Repository sendiri, gunakan Hilt.
- **Single Responsibility:** Jangan buat UseCase yang melakukan 5 hal sekaligus. Pecah!

## 6. Architecture Safety
Pelanggaran terhadap aturan ini akan mengakibatkan PR ditolak secara otomatis selama Code Review. Konsistensi arsitektur lebih penting daripada kecepatan pengiriman fitur jangka pendek.
