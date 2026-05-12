# Panduan Onboarding Developer

Selamat datang di tim! Ikuti langkah-langkah ini untuk mulai berkontribusi.

## 1. Persiapan Lingkungan
- Pastikan Anda menggunakan **Android Studio Ladybug** atau versi yang lebih baru.
- Gunakan **JDK 17** (disetel di Settings -> Build, Execution, Deployment -> Build Tools -> Gradle).
- Pastikan file **`config.env`** ada di root direktori proyek. Jika belum ada, buat baru dan isi dengan API Key Anda sesuai petunjuk di `README.md`.

## 2. Menjalankan Proyek
1. Lakukan **Sync Project with Gradle Files**.
2. Jalankan perintah `./gradlew assembleDebug` melalui terminal untuk memastikan build awal sukses.
3. Jalankan aplikasi ke Emulator atau Device fisik melalui modul `:app`.

## 3. Memahami Kode (Recommended Path)
1.  **Pelajari `:core:common`**: Pahami `BaseViewModel` dan `ResultState` karena ini adalah fondasi MVI kita.
2.  **Pelajari `:core:network`**: Lihat bagaimana `SafeApiCall` bekerja.
3.  **Pelajari `:navigation`**: Lihat bagaimana layar-layar dalam aplikasi saling terhubung secara Type-Safe.
4.  **Bedah `:features:news`**: Ini adalah fitur "Reference" yang menerapkan Clean Architecture (Data, Domain, UI) secara lengkap. Jadikan ini acuan saat membuat fitur baru.

## 4. Alur Kerja Git
- Gunakan branch baru untuk setiap fitur/bug: `feature/nama-fitur` atau `bugfix/nama-bug`.
- Setiap Pull Request (PR) diharapkan menyertakan Unit Test untuk logika bisnis baru di lapisan Domain.
- Pastikan build sukses secara lokal sebelum melakukan push.

## 5. Standar Penamaan & Style
- **Class**: PascalCase (misal: `NewsRepository`).
- **Function**: camelCase (misal: `getTopHeadlines`).
- **Composable**: PascalCase (misal: `ArticleItem`).
- **Variable**: camelCase (misal: `articleUrl`).
- **Resource**: snake_case (misal: `ic_back_arrow`).
- **MVI Classes**: `[Feature]State`, `[Feature]Intent`, `[Feature]Effect`.
