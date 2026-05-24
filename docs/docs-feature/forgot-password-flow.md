# Dokumentasi Fitur - Forgot Password & OTP Flow

Alur pemulihan akun yang mencakup verifikasi nomor telepon melalui kode OTP.

## 1. Forgot Password (Phone Input)
Pengguna memasukkan nomor telepon untuk menerima kode verifikasi.
* **Diagram Alur**:
```mermaid
graph TD
    Start((Forgot Password)) --> Phone[Input Nomor Telepon]
    Phone -- "Klik Send" --> API[Kirim Kode OTP]
    API -- Sukses --> OTP_Stage[Pindah ke Tampilan Input OTP]
```

## 2. OTP Verification & Resend Timer
Verifikasi identitas pengguna menggunakan kode yang dikirim.
* **Fitur Utama**:
    * **Resend Timer**: Hitung mundur 30 detik sebelum tombol resend aktif kembali.
    * **Auto Focus**: (Opsional) Langsung ke field input.

### Alur Interaksi OTP
```mermaid
graph TD
    OTP_Stage[Layar OTP] --> Input[Input 4-6 Digit Kode]
    OTP_Stage --> Timer{Timer > 0?}
    Timer -- Ya --> ResendDisabled[Tombol Resend Mati & Show s]
    Timer -- Tidak --> ResendEnabled[Tombol Resend Aktif]
    ResendEnabled -- Klik --> API[Kirim Ulang OTP & Reset Timer]
    
    Input -- "Klik Change Password" --> ChangePass((Ke Ganti Password))
```

## 3. Change Password
Menetapkan kata sandi baru setelah verifikasi sukses.
* **Validasi**: Pengecekan kecocokan (Matching) antara Password Baru dan Konfirmasi Password.

### Alur Validasi Password
```mermaid
graph TD
    Entry((Layar Ganti Password)) --> Input[Input Password Baru & Konfirmasi]
    Input --> Match{Password == Konfirmasi?}
    Match -- Tidak --> Error[Tampilkan Pesan: Password Tidak Cocok]
    Match -- Ya --> Submit[Tombol Aktif & Submit]
    Submit -- Sukses --> SuccessView((Success Screen))
```
