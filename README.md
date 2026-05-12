# My Application - Android Starter Foundation

Proyek ini adalah foundation Android modular tingkat enterprise yang dibangun dengan prinsip Clean Architecture, MVI, dan Jetpack Compose. Dirancang untuk skalabilitas tim besar dan pemeliharaan jangka panjang, mengikuti standar resmi **Google "Now in Android" (NiA)**.

---

## 🛠️ Setup Awal

Kami menggunakan standar `.env` untuk manajemen rahasia aplikasi agar proses *onboarding* tim lebih mudah dan terstandarisasi.

1.  **Clone** repositori ini.
2.  Buat file baru bernama **`.env`** di root direktori proyek.
3.  Tambahkan konfigurasi berikut ke dalam file `.env`:
    ```env
    NEWS_API_KEY=isi_dengan_api_key_anda
    BASE_URL=https://newsapi.org/v2/
    ```
4.  **Sync Project** dengan Gradle Files dan jalankan aplikasi.

---

## 🏗️ Visualisasi Arsitektur (Google NiA Standard)

Proyek ini menggunakan **Feature-Oriented Modular Architecture**. Setiap fitur diisolasi untuk memastikan performa build yang cepat dan mencegah "Spaghetti Code".

### 1. Graf Dependensi Modul
Berikut adalah struktur bagaimana modul-modul saling terhubung:

```mermaid
graph TD
    subgraph App_Layer
        APP[":app (The Glue)"]
    end

    subgraph Feature_Layer
        HOME[":features:home"]
        DETAIL[":features:detail"]
        BOOKMARK[":features:bookmark"]
        NEWS_UI[":features:news:ui (Shared UI)"]
    end

    subgraph Core_Layer
        CORE_MODEL[":core:model (Pure Kotlin)"]
        CORE_DOMAIN[":core:domain (UseCases)"]
        CORE_DATA[":core:data (Repositories)"]
        CORE_NET[":core:network"]
        CORE_DB[":core:database"]
        CORE_UI[":core:ui (Design System)"]
        CORE_COM[":core:common"]
    end

    APP --> HOME
    APP --> DETAIL
    APP --> BOOKMARK
    
    HOME --> CORE_DOMAIN
    HOME --> CORE_DATA
    BOOKMARK --> CORE_DOMAIN
    BOOKMARK --> CORE_DATA
    DETAIL --> CORE_DOMAIN
    DETAIL --> CORE_DATA

    HOME --> NEWS_UI
    BOOKMARK --> NEWS_UI
    DETAIL --> NEWS_UI

    CORE_DOMAIN --> CORE_MODEL
    CORE_DATA --> CORE_DOMAIN
    CORE_DATA --> CORE_NET
    CORE_DATA --> CORE_DB
    
    NEWS_UI --> CORE_UI
    NEWS_UI --> CORE_MODEL
```

#### Penjelasan Layer:
*   **App Layer (`:app`)**: Modul tertinggi yang merakit semua fitur.
*   **Feature Layer (`:features:*`)**: Berisi logika UI dan ViewModel yang terisolasi.
*   **Core Model (`:core:model`)**: Berisi entitas data murni (Pure Kotlin) yang digunakan seluruh aplikasi.
*   **Core Domain (`:core:domain`)**: Berisi UseCases untuk abstraksi logika bisnis.
*   **Core Data (`:core:data`)**: Implementasi Repository yang menghubungkan Network dan Database.
*   **Core Network/DB**: Infrastruktur teknis untuk API dan penyimpanan lokal.

---

### 2. Alur Data MVI (Unidirectional Data Flow)
Kami menjamin konsistensi UI melalui aliran data satu arah:

```mermaid
sequenceDiagram
    participant User
    box "UI Layer"
        participant UI as Compose UI
        participant VM as ViewModel (State/Effect)
    end
    participant UC as UseCase (Core Domain)
    participant Repo as Repository (Core Data)

    User->>UI: Interaksi (Klik/Input)
    UI->>VM: Kirim Intent (Action)
    VM->>UC: Panggil Logika Bisnis
    UC->>Repo: Request Data
    Repo-->>UC: Return Result (DTO/Entity)
    UC-->>VM: Return Domain Model
    VM->>VM: Update State (setState)
    VM-->>UI: Recompositon (New State)
    UI-->>User: Tampilan Terupdate
```

---

## 📚 Dokumentasi Engineering

1.  [**Project Overview**](docs/engineering/01-overview.md) - Filosofi dan tujuan skalabilitas.
2.  [**Architecture & Structure**](docs/engineering/02-architecture.md) - Detail modul dan arah dependensi.
3.  [**MVI & Data Flow**](docs/engineering/03-mvi-flow.md) - Panduan manajemen state.
4.  [**Feature Development Guide**](docs/engineering/04-development-guide.md) - Cara menambah fitur baru.
5.  [**Deep Dive Dependencies**](docs/engineering/05-dependencies-deep-dive.md) - Penjelasan library & plugin dengan analogi.
6.  [**Onboarding Guide**](docs/engineering/06-onboarding.md) - Standar penamaan dan workflow Git.
7.  [**Testing Strategy**](docs/engineering/07-testing.md) - Unit Test, UI Test, dan Robot Pattern.

---
**Created by Muh. Arifandi**
Email: [arif76440@gmail.com](mailto:arif76440@gmail.com)
