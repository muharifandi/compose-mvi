# My Application - Android Starter Foundation

Proyek ini adalah foundation Android modular tingkat enterprise yang dibangun dengan prinsip Clean Architecture, MVI, dan Jetpack Compose.

## 🚀 Fitur Arsitektur
- **Fully Modular**: Modularisasi berbasis fitur untuk performa build dan isolasi kode.
- **Clean Architecture**: Pemisahan tanggung jawab yang ketat antara Data, Domain, dan Presentation.
- **MVI Architecture**: Aliran data satu arah (UDF) untuk state management yang predictable.
- **Design System**: Komponen UI yang reusable dan konsisten.
- **Dependency Injection**: Menggunakan Hilt untuk manajemen dependensi.
- **Unit & UI Testing**: Siap dengan MockK, Turbine, dan Robot Pattern.

## 📚 Dokumentasi Teknis
Kami telah menyediakan dokumentasi lengkap untuk membantu Anda memahami dan mengembangkan proyek ini:

1.  [**Project Overview**](docs/engineering/01-overview.md) - Tujuan dan filosofi proyek.
2.  [**Architecture & Structure**](docs/engineering/02-architecture.md) - Penjelasan modul dan Clean Architecture.
3.  [**MVI & Data Flow**](docs/engineering/03-mvi-flow.md) - Bagaimana data mengalir di aplikasi.
4.  [**Feature Development Guide**](docs/engineering/04-development-guide.md) - Panduan langkah demi langkah membuat fitur baru.
5.  [**Deep Dive Dependencies**](docs/engineering/05-dependencies-deep-dive.md) - Penjelasan mendalam library & plugin (dengan analogi).
6.  [**Onboarding Guide**](docs/engineering/06-onboarding.md) - Panduan setup untuk developer baru.
7.  [**Testing Strategy**](docs/engineering/07-testing.md) - Bagaimana kami menjamin kualitas kode.

## 🛠️ Setup Awal
1. Clone repositori ini.
2. Buka `local.properties` dan tambahkan API Key Anda:
   ```properties
   NEWS_API_KEY=your_api_key_here
   BASE_URL=https://newsapi.org/v2/
   ```
3. Sync Project dan jalankan aplikasi.

---
**Created by Muh. Arifandi**
Email: [arif76440@gmail.com](mailto:arif76440@gmail.com)
