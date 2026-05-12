package com.muh.arifandi.dicoding.features.bookmark;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/muh/arifandi/dicoding/features/bookmark/BookmarkViewModel;", "Lcom/muh/arifandi/dicoding/core/common/mvi/BaseViewModel;", "Lcom/muh/arifandi/dicoding/features/bookmark/state/BookmarkState;", "Lcom/muh/arifandi/dicoding/features/bookmark/state/BookmarkIntent;", "Lcom/muh/arifandi/dicoding/features/bookmark/state/BookmarkEffect;", "repository", "Lcom/muh/arifandi/dicoding/domain/news/repository/NewsRepository;", "(Lcom/muh/arifandi/dicoding/domain/news/repository/NewsRepository;)V", "deleteFavorite", "", "url", "", "loadFavorites", "processIntent", "intent", "bookmark_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class BookmarkViewModel extends com.muh.arifandi.dicoding.core.common.mvi.BaseViewModel<com.muh.arifandi.dicoding.features.bookmark.state.BookmarkState, com.muh.arifandi.dicoding.features.bookmark.state.BookmarkIntent, com.muh.arifandi.dicoding.features.bookmark.state.BookmarkEffect> {
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.domain.news.repository.NewsRepository repository = null;
    
    @javax.inject.Inject()
    public BookmarkViewModel(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.domain.news.repository.NewsRepository repository) {
        super(null);
    }
    
    @java.lang.Override()
    public void processIntent(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.bookmark.state.BookmarkIntent intent) {
    }
    
    private final void deleteFavorite(java.lang.String url) {
    }
    
    private final void loadFavorites() {
    }
}