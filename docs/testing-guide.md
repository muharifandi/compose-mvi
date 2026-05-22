# Strategi & Panduan Testing

Dokumen ini menjelaskan arsitektur dan standar pengujian yang digunakan untuk menjaga stabilitas aplikasi dari unit terkecil hingga alur pengguna utuh.

## 1. Piramida Testing
Kami mengikuti strategi **Test Pyramid** untuk menyeimbangkan kecepatan dan akurasi:
- **Unit Tests (70%)**: Menguji logika bisnis di UseCase, ViewModel, dan Mapper. Cepat dan dijalankan di JVM.
- **Integration Tests (20%)**: Menguji interaksi antar komponen, seperti Repository dengan Database.
- **UI & E2E Tests (10%)**: Menguji UI Compose dan alur pengguna ujung-ke-ujung (End-to-End).

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

## 3. Compose UI & E2E Testing
E2E testing menguji aplikasi sebagai satu kesatuan, idealnya menggunakan *real network* atau *staging environment*.

### Robot Pattern (Best Practice)
Kami menggunakan **Robot Pattern** untuk memisahkan logika pengujian dari detail implementasi UI agar test lebih mudah dibaca dan dipelihara.

- **Robot**: Berisi fungsi interaksi teknis seperti `clickSearch()`, `verifyArticleIsShown()`.
- **Test**: Berisi skenario bisnis tingkat tinggi seperti `testSearchArticleSuccess()`.

```kotlin
class NewsRobot(private val composeTestRule: ComposeTestRule) {
    fun checkArticleIsVisible(title: String) {
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }
    
    fun clickArticle(title: String) {
        composeTestRule.onNodeWithText(title).performClick()
    }
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

## 5. Automation Workflow
1. **Trigger:** Setiap kali ada merge ke branch `develop`.
2. **Execution:** Jalankan `./gradlew connectedDebugAndroidTest` via CI/CD.
3. **Reporting:** Report diunggah ke Firebase Test Lab atau platform QA lainnya.

---

## 6. Testing Checklist
- [ ] Unit Test untuk UseCase (Bisnis Logika).
- [ ] Unit Test untuk ViewModel (State & Effect).
- [ ] UI Test untuk komponen kritis di `:core:ui`.
- [ ] Edge case (Error network, list kosong) sudah dites.
- [ ] Coroutine menggunakan `TestDispatcher`.
- [ ] Menggunakan data dummy dari `:core:testing`.
- [ ] Robot sudah didefinisikan untuk setiap layar utama.
