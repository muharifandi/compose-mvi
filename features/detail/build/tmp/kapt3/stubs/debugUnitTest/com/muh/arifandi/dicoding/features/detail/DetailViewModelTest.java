package com.muh.arifandi.dicoding.features.detail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\fH\u0007J\f\u0010\u000e\u001a\u00060\fj\u0002`\u000fH\u0007J\f\u0010\u0010\u001a\u00060\fj\u0002`\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/muh/arifandi/dicoding/features/detail/DetailViewModelTest;", "", "()V", "dummyArticle", "Lcom/muh/arifandi/dicoding/domain/news/model/Article;", "repository", "Lcom/muh/arifandi/dicoding/domain/news/repository/NewsRepository;", "testDispatcher", "Lkotlinx/coroutines/test/TestDispatcher;", "viewModel", "Lcom/muh/arifandi/dicoding/features/detail/DetailViewModel;", "setUp", "", "tearDown", "when LoadArticle intent is processed, state should update correctly", "Lkotlinx/coroutines/test/TestResult;", "when ToggleFavorite is clicked and not favorite, it should call saveFavorite", "detail_debugUnitTest"})
public final class DetailViewModelTest {
    private com.muh.arifandi.dicoding.features.detail.DetailViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.domain.news.repository.NewsRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.test.TestDispatcher testDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.domain.news.model.Article dummyArticle = null;
    
    public DetailViewModelTest() {
        super();
    }
    
    @org.junit.Before()
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    public final void setUp() {
    }
    
    @org.junit.After()
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    public final void tearDown() {
    }
}