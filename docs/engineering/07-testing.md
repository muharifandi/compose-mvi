# Strategi Testing

Kualitas kode dijamin melalui tiga lapisan pengujian.

## 1. Unit Testing
- **Fokus**: Logika bisnis di UseCase, ViewModel, dan Repository.
- **Library**: JUnit 4, MockK, Turbine (untuk mengetes Flow).
- **Lokasi**: Folder `src/test`.
- **Standar**: Setiap logika percabangan (if/else) wajib diuji.

## 2. UI Testing (Instrumented)
- **Fokus**: Interaksi pengguna dan alur navigasi.
- **Library**: Compose Test Rule, Espresso (jika diperlukan).
- **Pola**: Menggunakan **Robot Pattern** untuk membuat test case yang mudah dibaca.
- **Lokasi**: Folder `src/androidTest`.

## 3. Robot Pattern
Untuk menjaga maintainability UI test, kami memisahkan logic interaksi (apa yang dilakukan) dari skenario test (apa yang diuji).
- **Robot**: Berisi fungsi seperti `clickSearch()`, `verifyArticleIsShown()`.
- **Test**: Berisi skenario seperti `testSearchArticleSuccess()`.

## 4. Test Utilities
Modul `:core:testing` menyediakan data palsu (fakes) dan utilities untuk mempercepat penulisan test lintas modul. Selalu gunakan modul ini untuk data artikel testing agar konsisten.
