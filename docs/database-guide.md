# Panduan Database & Persistence

Dokumen ini menjelaskan strategi penyimpanan data lokal (offline-first) dan manajemen pagination.

## 1. Stack Teknologi Persistence

| Library | Purpose | Why Chosen | When To Use | When NOT To Use | Alternative | Scalability Impact |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Room** | SQLite Abstraction | Compile-time validation, Flow. | Caching data kompleks. | Key-value simple. | Realm, SQLDelight | Tinggi (Safe Migrations) |
| **Paging 3** | Pagination | Mengurangi beban RAM. | List data sangat besar. | List data statis (< 50). | Manual Pagination | Medium |
| **DataStore** | Key-Value Storage | Async, Coroutines support. | User Preference, Token. | Data Relasional. | SharedPreferences | Tinggi (Atomic update) |

---

## 2. Room Database
- **Konsep:** Lapisan abstraksi di atas SQLite yang mempermudah query data.
- **Tujuan:** Menjaga integritas data lokal dan memudahkan integrasi dengan UI.
- **Kenapa dipilih:** Google merekomendasikan Room sebagai standar industri. Validasi query saat compile time mencegah *runtime crash* akibat typo SQL.
- **Integration Flow:**
  - `Entity` (Table) -> `DAO` (Query) -> `Database` (Holder) -> `Repository`.
- **Performance:** Room melakukan operasi I/O di background thread. Penggunaan `Flow` memungkinkan UI terupdate otomatis saat data di DB berubah.

---

## 3. Paging 3 (Jetpack Paging)
- **Masalah yang diselesaikan:** Memuat ribuan data sekaligus menyebabkan aplikasi lambat (OOM - Out of Memory).
- **Cara Kerja:** Memuat data dalam "halaman" (chunks) secara otomatis saat user scroll.
- **Integration dengan Room:** Room mendukung `PagingSource` secara native, memudahkan implementasi *RemoteMediator* (Offline + Online sync).

---

## 4. Cara Kerja Room (Best Practice)
```kotlin
@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)
}
```
**Common Mistake:** Melakukan query berat di dalam `init` blok ViewModel atau di Main Thread.

---

## 5. APK Size & Performance
- **APK Size:** Room menambah sedikit ukuran karena code generation.
- **Performance Impact:** Sangat tergantung pada efisiensi Query SQL. Gunakan **Indices** (Indeks) pada kolom yang sering dicari.
- **Maintainability:** Selalu definisikan `Migration` saat mengubah skema database agar data user tidak hilang saat update aplikasi.

---

## 6. Tradeoff & Alternatives
- **Realm:** Lebih cepat untuk data sangat kompleks, tapi tidak native (menambah ukuran APK secara signifikan).
- **SQLDelight:** Multiplatform friendly, tapi konfigurasinya lebih rumit dibanding Room untuk project Android-only.

---

## 7. Kesimpulan
Kombinasi Room dan Paging 3 memberikan pengalaman pengguna yang sangat responsif, mendukung penggunaan offline, dan menjaga konsumsi memori tetap rendah bahkan dengan jutaan baris data.
