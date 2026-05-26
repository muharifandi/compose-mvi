# Dokumentasi Fitur - Forgot Password & OTP Flow

Alur pemulihan akun yang mencakup verifikasi nomor telepon melalui kode OTP menggunakan standar arsitektur Clean.

## Arsitektur Fitur
*   **UseCase**: `ForgotpasswordUseCase` (Metode: `sendOtp`, `verifyOtp`).
*   **Repository**: `ForgotpasswordRepository`.
*   **ViewModel**: `ForgotpasswordViewModel` (MVI).

## 1. Forgot Password (Phone Input)
Pengguna memasukkan nomor telepon untuk menerima kode verifikasi.

### Alur Data
```mermaid
graph TD
    Start((Forgot Password)) --> Phone[Input Nomor Telepon]
    Phone -- "Submit Intent" --> VM[ForgotpasswordViewModel]
    VM -- "Invoke sendOtp" --> UC[ForgotpasswordUseCase]
    UC -- "Result" --> VM
    VM -- "State: isCodeSent=true" --> OTP_Stage[Tampilan Input OTP]
```

## 2. OTP Verification & Resend Timer
Verifikasi identitas pengguna menggunakan kode yang dikirim.

### Alur Interaksi OTP
```mermaid
graph TD
    OTP_Stage[Layar OTP] --> Input[Input Kode OTP]
    Input -- "Submit Intent" --> VM
    VM -- "Invoke verifyOtp" --> UC
    UC -- "Success" --> Effect[Send Effect: NavigateToVerify]
    
    subgraph Timer_Logic [Logika Timer]
        VM --> Timer{resendTimer > 0?}
        Timer -- Ya --> ResendDisabled[Tombol Disabled]
        Timer -- Tidak --> ResendEnabled[Tombol Enabled]
    end
```

## 3. Change Password
Menetapkan kata sandi baru setelah verifikasi sukses.

### Alur Validasi Password
```mermaid
graph TD
    Entry((Layar Ganti Password)) --> Input[Input Password Baru & Konfirmasi]
    Input -- "Submit Intent" --> VM
    VM -- "Match Check" --> Valid{Valid?}
    Valid -- Ya --> API[Invoke Update Password]
    API -- Sukses --> SuccessView((Success Screen))
```

## Troubleshooting
*   **OTP Tidak Terkirim**: Pastikan `ForgotpasswordRepositoryImpl` telah mensimulasikan kegagalan jika diperlukan untuk testing.
*   **Timer Macet**: Timer dikelola oleh `viewModelScope` menggunakan coroutine `delay(1000)`. Pastikan ViewModel tidak hancur saat transisi state.
