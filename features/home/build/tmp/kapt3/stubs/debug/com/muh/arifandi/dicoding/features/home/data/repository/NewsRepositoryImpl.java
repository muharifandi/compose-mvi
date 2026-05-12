package com.muh.arifandi.dicoding.features.home.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0016J\u0018\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001e\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00140\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\fH\u0016J,\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u00170\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u001eJ*\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u00170\u000f2\u0006\u0010 \u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/data/repository/NewsRepositoryImpl;", "Lcom/muh/arifandi/dicoding/domain/news/repository/NewsRepository;", "apiService", "Lcom/muh/arifandi/dicoding/features/home/data/remote/api/NewsApiService;", "articleDao", "Lcom/muh/arifandi/dicoding/features/home/data/local/dao/ArticleDao;", "favoriteDao", "Lcom/muh/arifandi/dicoding/features/home/data/local/dao/FavoriteDao;", "(Lcom/muh/arifandi/dicoding/features/home/data/remote/api/NewsApiService;Lcom/muh/arifandi/dicoding/features/home/data/local/dao/ArticleDao;Lcom/muh/arifandi/dicoding/features/home/data/local/dao/FavoriteDao;)V", "deleteFavorite", "", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFavorites", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/muh/arifandi/dicoding/domain/news/model/Article;", "getArticleByUrl", "getPagedTopHeadlines", "Landroidx/paging/PagingData;", "category", "getTopHeadlines", "Lcom/muh/arifandi/dicoding/core/common/ResultState;", "page", "", "isFavorite", "", "saveFavorite", "article", "(Lcom/muh/arifandi/dicoding/domain/news/model/Article;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchNews", "query", "home_debug"})
public final class NewsRepositoryImpl implements com.muh.arifandi.dicoding.domain.news.repository.NewsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.features.home.data.remote.api.NewsApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao articleDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.features.home.data.local.dao.FavoriteDao favoriteDao = null;
    
    @javax.inject.Inject()
    public NewsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.home.data.remote.api.NewsApiService apiService, @org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao articleDao, @org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.home.data.local.dao.FavoriteDao favoriteDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.muh.arifandi.dicoding.core.common.ResultState<java.util.List<com.muh.arifandi.dicoding.domain.news.model.Article>>> getTopHeadlines(@org.jetbrains.annotations.Nullable()
    java.lang.String category, int page) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.muh.arifandi.dicoding.domain.news.model.Article>> getPagedTopHeadlines(@org.jetbrains.annotations.Nullable()
    java.lang.String category) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.muh.arifandi.dicoding.core.common.ResultState<java.util.List<com.muh.arifandi.dicoding.domain.news.model.Article>>> searchNews(@org.jetbrains.annotations.NotNull()
    java.lang.String query, int page) {
        return null;
    }
    
    @java.lang.Override()
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.muh.arifandi.dicoding.domain.news.model.Article> getArticleByUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.muh.arifandi.dicoding.domain.news.model.Article>> getAllFavorites() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> isFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveFavorite(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.domain.news.model.Article article, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}