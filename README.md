# SakaAndroid - Scalable Enterprise Android Foundation (Starter Kit)

Selamat datang di repository **SakaAndroid Starter Kit**. Proyek ini merupakan **fondasi arsitektur Android tingkat enterprise** yang dibangun dengan standar industri tinggi, dirancang khusus sebagai titik awal (template) yang bersih untuk membangun aplikasi baru.

## 📝 Ringkasan Fondasi
Fondasi ini dirancang untuk menyelesaikan tantangan umum dalam pengembangan aplikasi skala besar, seperti manajemen state yang kompleks, modularisasi yang sulit dipelihara, dan performa UI.

### Key Capabilities:
- **Saka Design System:** Sistem UI terpusat dengan komponen yang Figma-compliant, mendukung *Custom Shadow*, *Pill-shape Buttons*, dan transisi animasi halus.
- **Modularization Engine:** Struktur modul `:api` (kontrak) dan `:impl` (detail) yang mengoptimalkan kecepatan build dan enkapsulasi fitur.
- **Enterprise Security:** Implementasi `SecurityGuard` terintegrasi untuk deteksi Root, Emulator, dan Debug secara *out-of-the-box*.
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
        subgraph Feature_Auth [Auth Feature]
            AUTH_API[":features:login:api"]
            AUTH_IMPL[":features:login:impl"]
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
    APP --> AUTH_IMPL
    APP --> NAV

    AUTH_IMPL --> AUTH_API
    AUTH_IMPL --> CORE_ARC
    AUTH_IMPL --> CORE_UI
    AUTH_IMPL --> CORE_NET
    AUTH_IMPL --> CORE_MOD

    NAV --> AUTH_API
    NAV --> CORE_ARC

    %% Build logic applied (Simplified)
    CONV -.-> APP
    CONV -.-> Feature_Layer
    CONV -.-> Core_Layer
```

---

## 🚀 Quick Start
1. **Clone:** `git clone -b starter-project https://github.com/muharifandi/compose-mvi.git`
2. **Setup:** Gunakan Android Studio Ladybug (2024.2.1) atau lebih baru.
3. **Build:** Jalankan `./gradlew assembleDebug`.
4. **Docs:** Baca [Architecture Guide](docs/architecture.md) untuk memahami standar pengembangan.

---

## 🏗 Arsitektur Utama
Proyek ini mengadopsi standar **Modern Android Development (MAD)** dengan pilar utama:
- **Modularization:** API/Impl split strategy untuk enkapsulasi dan build time yang optimal.
- **Clean Architecture:** Pemisahan tanggung jawab yang jelas antara layer Presentation, Domain, dan Data.
- **MVI Pattern:** Pola arsitektur yang menjamin *Unidirectional Data Flow* (UDF) dan state yang konsisten.

---

## 📖 Dokumentasi Lengkap (Starter Kit)
Berikut adalah daftar panduan yang tersedia di folder `docs/`:
| Dokumen | Deskripsi |
| :--- | :--- |
| [Architecture](docs/architecture.md) | Detail Clean Architecture, Layering, & Data Flow. |
| [Design System](docs/ui-component-guide.md) | Katalog komponen UI Saka (Button, Card, dll). |
| [Modularization](docs/modularization.md) | Struktur modul & aturan dependency. |
| [MVI Guide](docs/mvi-architecture.md) | Panduan State, Intent, & Effect. |
| [Standards](docs/engineering-standards.md) | Aturan coding, arsitektur, & workflow. |
| [Feature Guide](docs/feature-development.md) | Panduan langkah demi langkah membuat fitur baru. |
| [App Lifecycle](docs/app-lifecycle.md) | Diagram alur aplikasi (Splash -> Auth). |
| [Testing Guide](docs/testing-guide.md) | Strategi pengujian & Robot Pattern. |

---

## 🛠 Tech Stack
- **UI:** Jetpack Compose, Material 3, **Saka Design System**
- **Network:** Retrofit, OkHttp, Kotlin Serialization
- **DI:** Hilt (Dagger)
- **Image:** Coil
- **Quality:** JUnit 5, MockK, Turbine

---
**Created by:** Muh. Arifandi  
**Email:** arif76440@gmail.com
