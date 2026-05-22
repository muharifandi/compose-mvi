# SakaAndroid - Scalable Enterprise Android Foundation

Selamat datang di repository **SakaAndroid**. Proyek ini merupakan **fondasi arsitektur Android tingkat enterprise** yang dibangun dengan standar industri tinggi. Dirancang sebagai *boilerplate* modular yang skalabel, proyek ini menggunakan **Clean Architecture**, **pola MVI (Model-View-Intent)**, dan **Jetpack Compose** untuk mendukung pengembangan berbagai jenis aplikasi Android modern dengan efisiensi tinggi.

## 📝 Ringkasan Fondasi
Fondasi ini dirancang untuk menyelesaikan tantangan umum dalam pengembangan aplikasi skala besar, seperti manajemen state yang kompleks, modularisasi yang sulit dipelihara, dan performa UI. Secara teknis, proyek ini menyediakan implementasi standar untuk **Offline-First**, **Modularisasi tingkat lanjut (API/Impl split)**, serta sistem navigasi yang terdesentralisasi.

### Key Capabilities:
- **Saka Design System:** Sistem UI terpusat dengan komponen yang Figma-compliant, mendukung *Custom Shadow*, *Pill-shape Buttons*, dan transisi animasi halus.
- **Modularization Engine:** Struktur modul `:api` (kontrak) dan `:impl` (detail) yang mengoptimalkan kecepatan build dan enkapsulasi fitur.
- **Enterprise Security:** Implementasi `SecurityGuard` terintegrasi untuk deteksi Root, Emulator, dan Debug secara *out-of-the-box*.
- **High Performance Foundation:** Integrasi *Baseline Profile* dan *Paging 3* yang siap digunakan untuk pengalaman UI tanpa hambatan.
- **Predictable Robustness:** Arsitektur MVI yang menjamin konsistensi state UI dan kemudahan pengujian otomatis (Unit/UI Testing).

---

## 📊 Alur Arsitektur MVI (Technical Flow)
Diagram ini menjelaskan siklus *Unidirectional Data Flow* (UDF) yang menjadi standar manajemen state dalam fondasi ini.

```mermaid
graph TD
    subgraph View [View - Jetpack Compose]
        UI[UI Components]
    end

    subgraph ViewModel [ViewModel - State Holder]
        Process[Process Intent]
        Reducer[Update State]
    end

    subgraph Model [Model - State & Side Effects]
        State[Immutable State]
        Effect[One-time Effects]
    end

    UI -- "User Action (Intent)" --> Process
    Process -- "Repository/UseCase" --> Data((Data Layer))
    Data -- "Response" --> Reducer
    Reducer -- "Emit New State" --> State
    State -- "Reactive Binding" --> UI
    Process -- "Fire Effect" --> Effect
    Effect -- "Navigation/Toast/Snackbar" --> UI
```

---

## 🛠 Contoh Implementasi Alur Bisnis (Example Flow)
Diagram fungsional yang menjelaskan bagaimana fondasi ini menangani perjalanan pengguna dalam sebuah aplikasi secara modular.

```mermaid
graph LR
    Splash[Splash Screen] --> Home[List View]
    Home -- "Action/Filter" --> Home
    Home -- "Select Item" --> Detail[Detail View]
    Detail -- "Primary Action" --> LocalDB[(Local DB)]
    Home -- "Secondary View" --> Auxiliary[Auxiliary Screen]
    Auxiliary -- "Action" --> Detail
    Home -- "About/Settings" --> About[About/Profile Screen]
```

---

## 📦 Struktur Modul & Dependency Graph (Module Visualization)
SakaAndroid menggunakan struktur **Multi-module** tingkat lanjut dengan pemisahan `:api` dan `:impl` untuk mendukung skalabilitas build dan enkapsulasi kode.

```mermaid
graph TD
    subgraph App_Layer [App Layer]
        APP[":app"]
    end

    subgraph Navigation_Layer [Navigation Layer]
        NAV[":navigation"]
    end

    subgraph Feature_Layer [Feature Layer]
        subgraph Feature_X [Feature X]
            FX_API[":features:X:api"]
            FX_IMPL[":features:X:impl"]
        end
    end

    subgraph Core_Layer [Core Layer]
        CORE_ARC[":core:architecture"]
        CORE_UI[":core:ui"]
        CORE_NET[":core:network"]
        CORE_MOD[":core:model"]
        CORE_COM[":core:common"]
    end

    subgraph Build_Logic [Build Logic]
        CONV[":build-logic:convention"]
    end

    %% Dependencies
    APP --> FX_IMPL
    APP --> NAV

    FX_IMPL --> FX_API
    FX_IMPL --> CORE_ARC
    FX_IMPL --> CORE_UI
    FX_IMPL --> CORE_NET
    FX_IMPL --> CORE_MOD

    NAV --> FX_API
    NAV --> CORE_ARC

    %% Build logic applied (Simplified)
    CONV -.-> APP
    CONV -.-> Feature_Layer
    CONV -.-> Core_Layer
```

---

## 🚀 Quick Start
1. **Clone:** `git clone https://github.com/muharifandi/compose-mvi.git`
2. **Setup:** Gunakan Android Studio Ladybug (2024.2.1) atau lebih baru.
3. **Build:** Jalankan `./gradlew assembleDebug`.
4. **Docs:** Baca [Onboarding Guide](docs/onboarding-guide.md) untuk memulai pengembangan fitur baru.

---

## 🏗 Arsitektur Utama
Proyek ini mengadopsi standar **Modern Android Development (MAD)** dengan pilar utama:
- **Modularization:** API/Impl split strategy untuk enkapsulasi dan build time yang optimal.
- **Clean Architecture:** Pemisahan tanggung jawab yang jelas antara layer Presentation, Domain, dan Data.
- **MVI Pattern:** Pola arsitektur yang menjamin *Unidirectional Data Flow* (UDF) dan state yang konsisten.

---

## 📚 Referensi & Standar Pengembangan
Pengembangan proyek ini merujuk pada standar industri dan dokumentasi resmi berikut:
- **[Guide to App Architecture](https://developer.android.com/topic/architecture):** Panduan resmi Google untuk arsitektur aplikasi Android yang skalabel.
- **[Now in Android (NiA)](https://github.com/android/nowinandroid):** Project referensi resmi Google untuk implementasi modularisasi dan tech stack terbaru.
- **[Kotlin Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html):** Standar manajemen asinkron dan reaktif di Kotlin.

---

## 📖 Dokumentasi Lengkap
Kami menyediakan dokumentasi teknis mendalam untuk setiap aspek:
| Dokumen | Deskripsi |
| :--- | :--- |
| [Playbook](docs/engineering-playbook.md) | Pusat navigasi seluruh standar engineering. |
| [Architecture](docs/architecture.md) | Detail Clean Architecture, Layering, & Data Flow. |
| [Design System](docs/ui-component-guide.md) | Katalog komponen UI Saka (Button, Card, dll). |
| [Modularization](docs/modularization.md) | Struktur modul & aturan dependency. |
| [MVI Guide](docs/mvi-architecture.md) | Panduan State, Intent, & Effect. |
| [Standards](docs/engineering-standards.md) | Aturan coding, arsitektur, & workflow. |
| [Feature Guide](docs/feature-development.md) | Panduan langkah demi langkah membuat fitur baru. |
| [Testing Guide](docs/testing-guide.md) | Strategi pengujian & Robot Pattern. |

---

## 🛠 Tech Stack
- **UI:** Jetpack Compose, Material 3, **Saka Design System**
- **Network:** Retrofit, OkHttp, Kotlin Serialization
- **Database:** Room, Paging 3
- **DI:** Hilt (Dagger)
- **Image:** Coil
- **Quality:** Detekt, JUnit 5, MockK, Turbine

---
**Created by:** Muh. Arifandi  
**Email:** arif76440@gmail.com
