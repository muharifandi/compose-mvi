package com.muh.arifandi.dicoding.features.detail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/muh/arifandi/dicoding/features/detail/DetailViewModel;", "Lcom/muh/arifandi/dicoding/core/common/mvi/BaseViewModel;", "Lcom/muh/arifandi/dicoding/features/detail/state/DetailState;", "Lcom/muh/arifandi/dicoding/features/detail/state/DetailIntent;", "Lcom/muh/arifandi/dicoding/features/detail/state/DetailEffect;", "repository", "Lcom/muh/arifandi/dicoding/domain/news/repository/NewsRepository;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/muh/arifandi/dicoding/domain/news/repository/NewsRepository;Landroidx/lifecycle/SavedStateHandle;)V", "isProcessingFavorite", "", "loadJob", "Lkotlinx/coroutines/Job;", "loadArticle", "", "url", "", "processIntent", "intent", "toggleFavorite", "detail_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DetailViewModel extends com.muh.arifandi.dicoding.core.common.mvi.BaseViewModel<com.muh.arifandi.dicoding.features.detail.state.DetailState, com.muh.arifandi.dicoding.features.detail.state.DetailIntent, com.muh.arifandi.dicoding.features.detail.state.DetailEffect> {
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.domain.news.repository.NewsRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.SavedStateHandle savedStateHandle = null;
    private boolean isProcessingFavorite = false;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job loadJob;
    
    @javax.inject.Inject()
    public DetailViewModel(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.domain.news.repository.NewsRepository repository, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super(null);
    }
    
    @java.lang.Override()
    public void processIntent(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.detail.state.DetailIntent intent) {
    }
    
    private final void loadArticle(java.lang.String url) {
    }
    
    private final void toggleFavorite() {
    }
}