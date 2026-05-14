# Reusable Component Guide

Dokumen ini menjelaskan pembagian tanggung jawab antara komponen global di `:core:ui` dan komponen spesifik di modul fitur.

## 1. Generic vs Feature-Specific Component

| Karakteristik | Generic Component (`:core:ui`) | Feature Component (`:features:...`) |
| :--- | :--- | :--- |
| **Penggunaan** | Digunakan di >1 fitur. | Hanya digunakan di 1 fitur. |
| **Logika Bisnis** | 0% (Stateless). | Boleh tahu tentang Domain Model fitur. |
| **Ketergantungan** | Tidak boleh tergantung modul fitur. | Tergantung pada `:core:ui`. |
| **Contoh** | `AppButton`, `AppTextField`, `AppCard`. | `NewsArticleItem`, `AboutProfileHeader`. |

---

## 2. Kapan Membuat Component Reusable?
- **Aturan "Rule of Three":** Jika sebuah pola UI muncul di 3 tempat berbeda, pindahkan ke `:core:ui`.
- **Konsistensi:** Jika komponen tersebut harus memiliki tampilan yang sama persis di seluruh aplikasi (misal: Loading Indicator).

---

## 3. Komponen Ownership
- **Core UI Team:** Bertanggung jawab atas stabilitas dan performa komponen di `:core:ui`.
- **Feature Team:** Bebas berkreasi membuat komponen di dalam modul fitur mereka selama mengikuti Design System.

---

## 4. Cara Membuat Reusable Component yang Baik
1. **Gunakan Modifier:** Selalu sediakan parameter `modifier: Modifier = Modifier` di urutan pertama.
2. **Slots Pattern:** Gunakan parameter `content: @Composable () -> Unit` untuk fleksibilitas (seperti `Scaffold` atau `Card`).
3. **Defaults:** Sediakan nilai default untuk parameter opsional.

```kotlin
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Surface(modifier = modifier, shadowElevation = elevation) {
        content()
    }
}
```

---

## 5. Apa yang TIDAK BOLEH di `:core:ui`
- **API Call:** Komponen UI tidak boleh melakukan network request.
- **Navigator:** Komponen UI tidak boleh tahu tentang logika navigasi global.
- **Dagger/Hilt Injection:** Hindari menginjeksi dependency langsung ke reusable component.

---

## 6. Kesimpulan
Dengan memisahkan komponen generik dan spesifik, kita menjaga modul `:core:ui` tetap ringan dan meminimalkan kompilasi ulang saat ada perubahan kecil di tingkat fitur.
