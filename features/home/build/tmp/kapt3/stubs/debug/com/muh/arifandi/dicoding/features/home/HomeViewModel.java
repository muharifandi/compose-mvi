package com.muh.arifandi.dicoding.features.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001c2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001:\u0001\u001cB\u001f\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0003H\u0016J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0017H\u0002R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/HomeViewModel;", "Lcom/muh/arifandi/dicoding/core/common/mvi/BaseViewModel;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeState;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeEffect;", "getTopHeadlinesUseCase", "Lcom/muh/arifandi/dicoding/domain/news/usecase/GetTopHeadlinesUseCase;", "searchNewsUseCase", "Lcom/muh/arifandi/dicoding/domain/news/usecase/SearchNewsUseCase;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/muh/arifandi/dicoding/domain/news/usecase/GetTopHeadlinesUseCase;Lcom/muh/arifandi/dicoding/domain/news/usecase/SearchNewsUseCase;Landroidx/lifecycle/SavedStateHandle;)V", "_pagedArticles", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/paging/PagingData;", "Lcom/muh/arifandi/dicoding/domain/news/model/Article;", "pagedArticles", "Lkotlinx/coroutines/flow/StateFlow;", "getPagedArticles", "()Lkotlinx/coroutines/flow/StateFlow;", "loadPagedArticles", "", "category", "", "processIntent", "intent", "searchArticles", "query", "Companion", "home_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HomeViewModel extends com.muh.arifandi.dicoding.core.common.mvi.BaseViewModel<com.muh.arifandi.dicoding.features.home.state.HomeState, com.muh.arifandi.dicoding.features.home.state.HomeIntent, com.muh.arifandi.dicoding.features.home.state.HomeEffect> {
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.domain.news.usecase.GetTopHeadlinesUseCase getTopHeadlinesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.domain.news.usecase.SearchNewsUseCase searchNewsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.SavedStateHandle savedStateHandle = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<androidx.paging.PagingData<com.muh.arifandi.dicoding.domain.news.model.Article>> _pagedArticles = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<androidx.paging.PagingData<com.muh.arifandi.dicoding.domain.news.model.Article>> pagedArticles = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_QUERY = "query";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CATEGORY = "category";
    @org.jetbrains.annotations.NotNull()
    public static final com.muh.arifandi.dicoding.features.home.HomeViewModel.Companion Companion = null;
    
    @javax.inject.Inject()
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.domain.news.usecase.GetTopHeadlinesUseCase getTopHeadlinesUseCase, @org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.domain.news.usecase.SearchNewsUseCase searchNewsUseCase, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<androidx.paging.PagingData<com.muh.arifandi.dicoding.domain.news.model.Article>> getPagedArticles() {
        return null;
    }
    
    @java.lang.Override()
    public void processIntent(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.home.state.HomeIntent intent) {
    }
    
    private final void loadPagedArticles(java.lang.String category) {
    }
    
    private final void searchArticles(java.lang.String query) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/HomeViewModel$Companion;", "", "()V", "KEY_CATEGORY", "", "KEY_QUERY", "home_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}