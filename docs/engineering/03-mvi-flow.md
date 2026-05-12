# MVI & Data Flow

Proyek ini menggunakan pola **MVI (Model-View-Intent)** dengan **Unidirectional Data Flow (UDF)**.

## 1. Komponen MVI

- **State**: Objek tunggal yang mewakili kondisi UI (apa yang dilihat user). Bersifat **Immutable**.
- **Intent**: Mewakili aksi atau niat pengguna (apa yang dilakukan user), misal: `SearchArticle`, `Refresh`.
- **Effect**: Peristiwa satu kali (one-time events) yang bukan bagian dari state, misal: Toast, Navigasi.

## 2. Alur Data (Data Flow)

Alur lengkap dari interaksi user hingga UI terupdate:

1.  **User Action**: User menekan tombol (Intent).
2.  **ViewModel**: Menangkap Intent, memproses logika (biasanya memanggil UseCase).
3.  **UseCase**: Menjalankan aturan bisnis.
4.  **Repository**: Mengambil data dari Remote (API) atau Local (DB).
5.  **Mapper**: Mengubah DTO/Entity menjadi Domain Model.
6.  **ViewModel**: Menerima hasil (Success/Error), lalu melakukan `setState` (mengupdate State).
7.  **UI (Compose)**: Mengamati perubahan State dan melakukan recomposition secara otomatis.

### Visualisasi:
`UI` -> `Intent` -> `ViewModel` -> `UseCase` -> `Repository` -> `Domain Model` -> `Update State` -> `UI (Recomposition)`

## 3. State Management
Kami menggunakan `StateFlow` untuk State dan `Channel` untuk Effect. State dikoleksi di UI menggunakan `collectAsStateWithLifecycle()` untuk memastikan keamanan siklus hidup (lifecycle-safety).
