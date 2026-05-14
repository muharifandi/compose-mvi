# System Flow & Data Lifecycle

Dokumen ini menjelaskan aliran data dari jaringan hingga sampai ke layar pengguna, serta bagaimana lifecycle komponen dikelola.

## 1. End-to-End Data Flow

```mermaid
sequenceDiagram
    participant UI as Compose Screen
    participant VM as ViewModel
    participant UC as UseCase
    participant REP as Repository
    participant API as Remote API
    participant DB as Local DB

    UI->>VM: Kirim Intent (Refresh)
    VM->>VM: Update State (Loading = true)
    VM->>UC: Execute UseCase
    UC->>REP: Fetch Data
    REP->>API: Network Request
    API-->>REP: Return DTO
    REP->>REP: Map DTO to Entity/Model
    REP->>DB: Save to Cache
    REP-->>UC: Return Flow<Model>
    UC-->>VM: Emit Result
    VM->>VM: Update State (Loading = false, Data = result)
    VM-->>UI: Observe State Change (Recomposition)
```

---

## 2. Navigasi Antar Fitur
Kami menggunakan **Navigation Handler** yang terpusat untuk menjaga fitur tetap terisolasi.
1. Fitur A ingin pindah ke Fitur B.
2. Fitur A mengirim perintah navigasi melalui `NavigationManager`.
3. `MainActivity` atau `AppNavHost` menangkap perintah tersebut.
4. `NavigationHandler` mengeksekusi navigasi menggunakan `NavController`.

---

## 3. Dependency Injection Flow (Hilt)
- **Singleton Component:** Berada di `:core` (Network, Database).
- **ViewModel Component:** Berada di masing-masing modul `:impl`.
- **Activity Component:** Berada di modul `:app`.

---

## 4. Error Handling Strategy
- Kami menggunakan kelas pembungkus `ResultState<T>` untuk mengirimkan status Sukses atau Error dari Repository ke UI.
- Error di tangkap di lapisan Data/Repository, dipetakan ke pesan yang ramah pengguna, dan dikirim ke UI melalui State atau Effect.

---

## 5. Kesimpulan
Aliran data yang konsisten dan terprediksi mempermudah debugging dan memastikan performa aplikasi tetap terjaga meskipun data yang dikelola sangat besar.
