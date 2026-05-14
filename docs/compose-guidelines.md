# Compose Architecture & Guidelines

Dokumen ini berisi aturan teknis dan praktik terbaik dalam menulis kode Jetpack Compose yang performan dan maintainable.

## 1. Arsitektur Komponen

### A. State Hoisting
Pindahkan state ke tingkat yang lebih tinggi agar komponen tetap stateless dan mudah diuji.
```kotlin
@Composable
fun SearchBar(
    query: String, // State di-hoist
    onQueryChange: (String) -> Unit // Event dikirim ke atas
) {
    TextField(value = query, onValueChange = onQueryChange)
}
```

### B. Immutable State
Selalu gunakan data model yang immutable (`val`) untuk memicu rekomposisi yang efisien. Gunakan `@Immutable` atau `@Stable` jika diperlukan untuk membantu compiler Compose.

---

## 2. Optimasi Rekomposisi
Compose sangat cepat, tapi penggunaan yang salah bisa menyebabkan *jank*.
- **Gunakan `remember`:** Untuk menyimpan hasil komputasi berat.
- **Gunakan `derivedStateOf`:** Saat state tergantung pada state lain yang sering berubah (misal: scroll position).
- **Hindari Komputasi di Body:** Jangan lakukan parsing data atau format tanggal langsung di dalam fungsi Composable.

---

## 3. Anti-Pattern Compose
- **Passing ViewModel ke Sub-component:** Jangan kirim ViewModel ke komponen kecil. Kirim hanya State dan Callback.
- **Side Effect Tanpa Key:** Menggunakan `LaunchedEffect(true)` untuk sesuatu yang seharusnya bergantung pada state tertentu.
- **Large Composable:** Fungsi Composable lebih dari 200 baris. Pecah menjadi komponen-komponen kecil.

---

## 4. Performance Risk
- **Unstable Collections:** `List` standar dianggap tidak stabil oleh Compose. Pertimbangkan menggunakan `ImmutableList` atau bungkus list dalam kelas data yang ditandai `@Immutable`.
- **Backward Writes:** Mengubah state di dalam body Composable (menyebabkan loop rekomposisi tak terbatas).

---

## 5. Preview Strategy
Setiap komponen harus memiliki `@Preview` untuk mempercepat iterasi desain tanpa perlu menjalankan aplikasi.
```kotlin
@Preview(showBackground = true)
@Composable
fun PreviewMyComponent() {
    MyApplicationTheme {
        MyComponent()
    }
}
```

---

## 6. Kesimpulan
Kunci dari Compose yang baik adalah kesederhanaan. Komponen yang kecil, stateless, dan memiliki tanggung jawab tunggal adalah kunci skalabilitas UI.
