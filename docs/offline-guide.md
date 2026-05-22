# Panduan Pengembangan Offline (Offline Development Guide) 🚀

Dokumen ini menjelaskan cara mengkonfigurasi dan menjalankan proyek SakaAndroid sepenuhnya tanpa koneksi internet.

## 📝 Konsep Dasar
Proyek ini menggunakan sistem caching Gradle yang agresif. Agar dapat bekerja offline, semua dependensi (library, plugin, SDK) harus sudah ada di folder `.gradle` lokal di komputer Anda.

## 🛠 Langkah Persiapan (Saat Online)

Sebelum Anda memutuskan koneksi internet, Anda wajib melakukan proses "pemanasan" (*warm-up*) untuk mengunduh semua artefak yang diperlukan.

### 1. Jalankan Task Persiapan
Eksekusi perintah berikut untuk memaksa Gradle mengunduh dependensi dari semua 29 modul:
```bash
./gradlew prepareOfflineBuild --no-configuration-cache
```
*Gunakan flag `--no-configuration-cache` agar skrip dapat mengakses seluruh modul tanpa hambatan cache.*

### 2. Lakukan Build Utama
Pastikan build dasar berhasil setidaknya satu kali:
```bash
./gradlew assembleDebug
```

---

## ✈️ Pengembangan Saat Offline (Tanpa Internet)

Setelah persiapan di atas selesai, Anda dapat menjalankan alur kerja berikut tanpa koneksi internet:

### 1. Membuat Fitur Baru
Skrip generator sudah mendukung offline karena menggunakan *Convention Plugins* lokal:
```bash
./generate_feature.sh <nama_fitur>
```

### 2. Menjalankan Build
Selalu tambahkan flag `--offline` agar Gradle tidak mencoba menghubungi server:
```bash
./gradlew assembleDebug --offline
```

### 3. Mengaktifkan Mode Offline di Android Studio
Agar IDE tidak melambat karena mencoba mencari update:
1.  Buka panel **Gradle** di sisi kanan.
2.  Klik ikon **Toggle Offline Mode** (ikon koneksi terputus).

---

## ⚠️ Batasan & Pantangan
1.  **Library Baru**: Jika Anda menambahkan dependensi baru di `libs.versions.toml`, Anda **WAJIB** online sejenak untuk mengunduh library tersebut.
2.  **Update Gradle/AGP**: Perubahan versi Gradle atau Android Gradle Plugin memerlukan koneksi internet untuk mengunduh compiler yang sesuai.

## 🛠 Troubleshooting
Jika muncul error "No cached version available for offline mode":
1.  Sambungkan kembali ke internet.
2.  Jalankan `./gradlew prepareOfflineBuild --no-configuration-cache`.
3.  Ulangi proses offline.

---
**Status Proyek**: 100% Offline-Ready
