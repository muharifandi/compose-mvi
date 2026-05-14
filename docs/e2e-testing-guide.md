# Panduan End-to-End Testing (E2E Guide)

Dokumen ini menjelaskan pengujian otomatis yang mensimulasikan alur pengguna secara utuh dari UI hingga ke Network/Database.

## 1. Konsep E2E Testing
E2E testing menguji aplikasi sebagai satu kesatuan. Berbeda dengan unit test yang menggunakan *fake*, E2E idealnya menggunakan *staging environment* atau *real network*.

## 2. Robot Pattern (Best Practice)
Kami menggunakan **Robot Pattern** untuk memisahkan logika pengujian dari detail implementasi UI.
- **Tujuan:** Membuat test lebih mudah dibaca dan dipelihara.

```kotlin
class NewsRobot(private val composeTestRule: ComposeTestRule) {
    fun checkArticleIsVisible(title: String) {
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }
    
    fun clickArticle(title: String) {
        composeTestRule.onNodeWithText(title).performClick()
    }
}

// Penggunaan di Test
@Test
fun newsFlowTest() {
    newsRobot {
        checkArticleIsVisible("Berita Terkini")
        clickArticle("Berita Terkini")
    }
}
```

---

## 3. Automation Testing Workflow
1. **Trigger:** Setiap kali ada merge ke branch `develop`.
2. **Environment:** CI/CD (GitHub Actions/Bitrise) menjalankan emulator.
3. **Execution:** Jalankan `./gradlew connectedDebugAndroidTest`.
4. **Reporting:** Report diunggah ke Firebase Test Lab atau platform QA lainnya.

---

## 4. Kapan Menggunakan E2E Test?
- **YA:** Untuk alur kritis (Happy Path) seperti Onboarding, Checkout, atau Sinkronisasi Data.
- **TIDAK:** Untuk menguji logika kecil atau variasi error (Gunakan Unit Test).

---

## 5. Automation Checklist
- [ ] Robot sudah didefinisikan untuk setiap layar utama.
- [ ] Test tidak bersifat "flaky" (selalu memberikan hasil yang sama).
- [ ] Delay/Idle resources ditangani dengan benar (IdlingResource).
- [ ] Test membersihkan datanya sendiri setelah dijalankan.
