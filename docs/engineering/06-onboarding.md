# Panduan Onboarding Developer

Selamat datang di tim! Ikuti langkah-langkah ini untuk mulai berkontribusi.

## 1. Persiapan Lingkungan
- Pastikan Anda menggunakan **Android Studio Ladybug** atau versi yang lebih baru.
- Gunakan **JDK 17** (disetel di Settings -> Build, Execution, Deployment -> Build Tools -> Gradle).
- Tambahkan `NEWS_API_KEY` dan `BASE_URL` di file `local.properties` atau `gradle.properties`.

## 2. Menjalankan Proyek
1. Lakukan **Sync Project with Gradle Files**.
2. Jalankan perintah `./gradlew assembleDebug` melalui terminal untuk memastikan build awal sukses.
3. Jalankan aplikasi ke Emulator atau Device fisik melalui modul `:app`.

## 3. Memahami Kode
- **Mulai dari `:domain`**: Pahami model data dan aturan bisnis utama.
- **Buka `:navigation`**: Lihat bagaimana layar-layar dalam aplikasi saling terhubung.
- **Lihat `:core:ui`**: Pahami komponen Design System yang tersedia agar tidak membuat komponen duplikat.
- **Pelajari `:features:home`**: Ini adalah fitur paling lengkap yang bisa dijadikan referensi untuk membuat fitur baru.

## 4. Alur Kerja Git
- Gunakan branch baru untuk setiap fitur/bug: `feature/nama-fitur` atau `bugfix/nama-bug`.
- Pastikan `detekt` atau `lint` lulus sebelum melakukan commit.
- Setiap Pull Request harus menyertakan unit test untuk logika bisnis baru.

## 5. Standar Penamaan
- **Class**: PascalCase (misal: `NewsRepository`).
- **Function**: camelCase (misal: `getTopHeadlines`).
- **Variable**: camelCase (misal: `articleUrl`).
- **Resource**: snake_case (misal: `ic_back_arrow`).
