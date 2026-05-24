# Dokumentasi Fitur - Authentication (Login & Signup)

Modul ini menangani proses otentikasi pengguna untuk masuk atau mendaftar akun baru.

## 1. Login Screen
Menangani akses pengguna lama ke dalam aplikasi.
* **Input**: Email & Password.
* **Validasi**: Tombol aktif jika kedua field terisi.
* **Navigasi**: Pindah ke Home jika sukses, atau ke Signup/Forgot Password.

### Alur Login
```mermaid
graph TD
    Entry((Layar Login)) --> Input[Input Email & Password]
    Input -- "Klik Sign In" --> Valid{Valid?}
    Valid -- Ya --> Loading[Proses API]
    Valid -- Tidak --> Error[Show Error]
    Loading -- Sukses --> Home((Ke Home))
    Loading -- Gagal --> Error
    
    Entry -- "Klik Sign Up" --> RegisterFlow((Ke Signup))
    Entry -- "Klik Forgot Password" --> ForgotFlow((Ke Forgot Password))
```

---

## 2. Signup (Register) Screen
Menangani pendaftaran akun pengguna baru.
* **Input**: Nama Lengkap, Email, Password.
* **Syarat**: Centang Syarat & Ketentuan (Terms & Conditions).
* **Validasi**: Tombol aktif jika data lengkap & T&C dicentang.

### Alur Signup
```mermaid
graph TD
    Entry((Layar Register)) --> Form[Input Nama, Email, Password]
    Form --> TC[Centang Syarat & Ketentuan]
    TC -- "Klik Sign Up" --> Check{Valid & Agreed?}
    Check -- Ya --> API[Proses Pendaftaran]
    Check -- Tidak --> Wait[Tombol Disabled]
    API -- Sukses --> Login((Ke Login))
```
