# Panduan QA & Validasi (QA Guide)

Dokumen ini menjelaskan strategi penjaminan kualitas untuk memastikan aplikasi bebas dari bug kritis sebelum sampai ke tangan pengguna.

## 1. QA Workflow
1. **Feature Validation:** QA menguji fitur baru berdasarkan kriteria penerimaan (Acceptance Criteria).
2. **Regression Testing:** Pengujian ulang fitur lama untuk memastikan tidak ada kerusakan akibat kode baru.
3. **Exploratory Testing:** Mencari bug di luar skenario yang direncanakan.
4. **Bug Reporting:** Melaporkan bug melalui sistem tracking (Jira/GitHub Issues).

---

## 2. Regression Testing Strategy
Setiap kali ada Pull Request besar atau rilis versi baru, tim QA wajib menjalankan **Regression Suite** yang mencakup:
- Alur Login/Authentication.
- Alur Utama Fitur (Core Business Flow).
- Integrasi pihak ketiga (Payment, Analytics).

---

## 3. Stability & Lifecycle Testing
- **Configuration Change:** Memutar layar (Landscape/Portrait) dan memastikan state tidak hilang.
- **Process Death:** Mensimulasikan sistem membunuh aplikasi saat di background dan memastikan data tetap ada saat dibuka kembali.
- **Network Fluctuation:** Menguji perilaku aplikasi saat berpindah dari 4G ke Wi-Fi atau saat Offline.

---

## 4. Release Checklist (QA)
- [ ] Lolos Regression Test.
- [ ] Performa aplikasi stabil (Tidak ada memory leak).
- [ ] Build variant `release` sudah diproteksi ProGuard/R8.
- [ ] Analitik sudah terkirim dengan benar.
- [ ] Versi aplikasi (Version Code & Name) sudah diperbarui.

---

## 5. Kesimpulan
QA bukan hanya mencari bug, tapi memastikan *User Experience* tetap terjaga dan aplikasi memiliki standar stabilitas tinggi sebelum didistribusikan.
