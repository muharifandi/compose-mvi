# Project Overview

## Tujuan Proyek
Proyek ini dirancang sebagai **Android Starter Project** tingkat enterprise yang siap digunakan untuk aplikasi skala menengah hingga besar. Fokus utama proyek ini adalah pada **scalability**, **maintainability**, dan **testability**.

## Filosofi Starter Project
Kami percaya bahwa fondasi yang kuat di awal akan mencegah "Technical Debt" yang mahal di masa depan. Proyek ini menggunakan standar industri modern (Modern Android Development - MAD) untuk memastikan setiap pengembang dalam tim dapat bekerja secara paralel tanpa saling mengganggu.

## Target Arsitektur
1.  **Modular**: Memecah kode menjadi unit-unit kecil (modul) untuk mempercepat build time dan isolasi fitur.
2.  **Clean Architecture**: Memisahkan logika bisnis dari detail framework (UI, Database, Network).
3.  **MVI (Model-View-Intent)**: Menjamin aliran data satu arah (Unidirectional Data Flow) yang dapat diprediksi.
4.  **Compose-first**: Menggunakan Jetpack Compose secara penuh untuk UI yang modern dan deklaratif.

## Goals Scalability
- **Build Scalability**: Memungkinkan ratusan modul tanpa memperlambat build time secara drastis melalui caching dan paralelisme.
- **Team Scalability**: Memungkinkan banyak tim (5-15+ developer) bekerja di modul fitur yang berbeda dengan konflik Git yang minimal.
- **Feature Scalability**: Menambah fitur baru semudah membuat modul fitur baru dan menghubungkannya ke sistem navigasi.
