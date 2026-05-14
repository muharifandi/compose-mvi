# Standar Engineering & Coding

Dokumen ini mendefinisikan standar penulisan kode untuk menjaga kualitas dan keterbacaan codebase.

## 1. Naming Convention
- **Class:** `PascalCase` (Contoh: `HomeViewModel`).
- **Function/Variable:** `camelCase` (Contoh: `onRefreshClick`).
- **XML Resource:** `snake_case` (Contoh: `ic_back_button`).
- **Compose Composable:** `PascalCase` dan harus kata benda (Contoh: `PrimaryButton`).

## 2. Package Convention
Gunakan penamaan paket berbasis fitur, bukan berbasis tipe kelas.
- **Benar:** `com.package.features.news.ui`
- **Salah:** `com.package.viewmodels.news`

## 3. Compose Best Practice
- **Stateless:** Buat composable se-stateless mungkin.
- **Modifiers:** Selalu jadikan `Modifier` sebagai parameter pertama dengan nilai default.
- **Preview:** Selalu sertakan `@Preview` untuk setiap komponen UI.

## 4. ViewModel & MVI
- **ViewModel** tidak boleh memiliki referensi ke `View` atau `Context`.
- **State** harus bersifat `Immutable` (gunakan `data class` dengan `val`).
- **Intent** harus dikirim melalui satu pintu masuk di ViewModel.

## 5. Testing Standard
- **Unit Test:** Wajib untuk UseCase dan ViewModel.
- **Naming Test:** `is_given_when_then` atau `should_when`.
  - Contoh: `getNews_should_emitSuccess_when_repositoryIsSuccessful`.

## 6. Documentation Convention
- Gunakan **KDoc** (`/** ... */`) untuk mendokumentasikan logika bisnis yang kompleks atau fungsi utilitas.
- Hindari komentar "apa" (karena kode harus *self-documenting*), fokuslah pada "mengapa".
