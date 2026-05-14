# Panduan Testing Android (Testing Guide)

Dokumen ini menjelaskan arsitektur dan standar pengujian yang digunakan untuk menjaga stabilitas aplikasi.

## 1. Piramida Testing
Kami mengikuti strategi **Test Pyramid** untuk menyeimbangkan kecepatan dan akurasi:
- **Unit Tests (70%)**: Menguji logika bisnis di UseCase, ViewModel, dan Mapper. Cepat dan dijalankan di JVM.
- **Integration Tests (20%)**: Menguji interaksi antar komponen, seperti Repository dengan Database.
- **UI & E2E Tests (10%)**: Menguji UI Compose dan alur pengguna ujung-ke-ujung.

---

## 2. Strategi Unit Testing

### A. ViewModel Testing (MVI)
Fokus pada pengujian apakah **Intent** menghasilkan **State** atau **Effect** yang benar.
```kotlin
@Test
fun `loadArticles should emit Success state when repository is successful`() = runTest {
    // Given
    val articles = listOf(Article(title = "Test"))
    coEvery { useCase() } returns flowOf(ResultState.Success(articles))

    // When
    viewModel.onIntent(HomeIntent.Refresh)

    // Then
    viewModel.state.test {
        val state = awaitItem()
        assert(state.articles == articles)
        assert(!state.isLoading)
    }
}
```

### B. Coroutine & Flow Testing
Gunakan `runTest` dari library `kotlinx-coroutines-test` dan `test()` dari library **Turbine** untuk menguji `StateFlow`.

---

## 3. Compose UI Testing
Kami menggunakan **Semantics** untuk menemukan elemen UI dan melakukan aksi.
```kotlin
@Test
fun myTest() {
    composeTestRule.setContent {
        MyScreen(state = HomeState(articles = listOf(...)))
    }

    composeTestRule.onNodeWithText("Judul Berita").assertIsDisplayed()
    composeTestRule.onNodeWithTag("btn_back").performClick()
}
```

---

## 4. Fake Data Strategy
Gunakan **Fake Repository** daripada **Mock** untuk integration test agar lebih mendekati perilaku nyata.
```kotlin
class FakeNewsRepository : NewsRepository {
    private val articles = mutableListOf<Article>()
    override suspend fun getNews() = flow { emit(ResultState.Success(articles)) }
}
```

---

## 5. Testing Checklist
- [ ] Unit Test untuk UseCase (Bisnis Logika).
- [ ] Unit Test untuk ViewModel (State & Effect).
- [ ] UI Test untuk komponen kritis di `:core:ui`.
- [ ] Edge case (Error network, list kosong) sudah dites.
- [ ] Coroutine menggunakan `TestDispatcher`.
