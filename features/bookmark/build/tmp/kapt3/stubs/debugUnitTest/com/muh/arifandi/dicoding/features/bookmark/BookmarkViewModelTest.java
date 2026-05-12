package com.muh.arifandi.dicoding.features.bookmark;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\nH\u0007J\f\u0010\f\u001a\u00060\nj\u0002`\rH\u0007J\f\u0010\u000e\u001a\u00060\nj\u0002`\rH\u0007J\f\u0010\u000f\u001a\u00060\nj\u0002`\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/muh/arifandi/dicoding/features/bookmark/BookmarkViewModelTest;", "", "()V", "repository", "Lcom/muh/arifandi/dicoding/domain/news/repository/NewsRepository;", "testDispatcher", "Lkotlinx/coroutines/test/TestDispatcher;", "viewModel", "Lcom/muh/arifandi/dicoding/features/bookmark/BookmarkViewModel;", "setUp", "", "tearDown", "when ClickArticle intent is processed, should send NavigateToDetail effect", "Lkotlinx/coroutines/test/TestResult;", "when DeleteFavorite intent is processed, should call repository delete", "when initialized, should load favorites automatically", "bookmark_debugUnitTest"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class BookmarkViewModelTest {
    private com.muh.arifandi.dicoding.features.bookmark.BookmarkViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.domain.news.repository.NewsRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.test.TestDispatcher testDispatcher = null;
    
    public BookmarkViewModelTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setUp() {
    }
    
    @org.junit.After()
    public final void tearDown() {
    }
}