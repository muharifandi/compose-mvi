# Dokumentasi Fitur - Authentication (Login & Signup)

Modul ini menangani proses otentikasi pengguna untuk masuk atau mendaftar akun baru menggunakan pola **Clean Architecture**.

## Arsitektur Fitur
*   **Domain**: `LoginUseCase`, `RegisterUseCase`.
*   **Data**: `LoginRepositoryImpl`, `RegisterRepositoryImpl`.
*   **Presentation**: `LoginViewModel`, `RegisterViewModel` (MVI).

## 1. Login Screen
Menangani akses pengguna lama ke dalam aplikasi.
* **Input**: Email & Password.
* **Validasi**: Tombol aktif jika kedua field terisi.
* **Flow**: UI -> Intent -> ViewModel -> UseCase -> Repository -> Result.

### Alur Login (Interaction & Data Flow)
```mermaid
graph TD
    Entry((Layar Login)) --> Input[Input Email & Password]
    Input -- "Submit Intent" --> VM[LoginViewModel]
    VM -- "Invoke" --> UC[LoginUseCase]
    UC -- "Call" --> Repo[LoginRepository]
    Repo -- "Result success/failure" --> VM
    VM -- "Send Effect" --> Home((Navigate to Home))
    
    Entry -- "Klik Sign Up" --> RegisterFlow((Ke Signup))
    Entry -- "Klik Forgot Password" --> ForgotFlow((Ke Forgot Password))
```

---

## 2. Signup (Register) Screen
Menangani pendaftaran akun pengguna baru.
* **Input**: Nama Lengkap, Email, Password.
* **Syarat**: Centang Syarat & Ketentuan (Terms & Conditions).
* **Validasi**: Tombol aktif jika data lengkap & T&C dicentang.

### Alur Signup (Data Flow)
```mermaid
graph TD
    Entry((Layar Register)) --> Form[Input Nama, Email, Password]
    Form --> TC[Centang Syarat & Ketentuan]
    TC -- "Submit Intent" --> VM[RegisterViewModel]
    VM -- "Invoke" --> UC[RegisterUseCase]
    UC -- "Result" --> VM
    VM -- "Navigate Effect" --> Login((Ke Layar Login))
```

## Pengiriman Data & Keamanan
*   Semua data otentikasi dikirim melalui `Result<Unit>` untuk penanganan error yang aman.
*   Password tidak pernah disimpan secara plain text di layer data.
