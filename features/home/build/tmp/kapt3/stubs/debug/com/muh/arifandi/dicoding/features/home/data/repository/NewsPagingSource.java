package com.muh.arifandi.dicoding.features.home.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ#\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\rH\u0016\u00a2\u0006\u0002\u0010\u000eJ(\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/data/repository/NewsPagingSource;", "Landroidx/paging/PagingSource;", "", "Lcom/muh/arifandi/dicoding/domain/news/model/Article;", "apiService", "Lcom/muh/arifandi/dicoding/features/home/data/remote/api/NewsApiService;", "articleDao", "Lcom/muh/arifandi/dicoding/features/home/data/local/dao/ArticleDao;", "category", "", "(Lcom/muh/arifandi/dicoding/features/home/data/remote/api/NewsApiService;Lcom/muh/arifandi/dicoding/features/home/data/local/dao/ArticleDao;Ljava/lang/String;)V", "getRefreshKey", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/PagingState;)Ljava/lang/Integer;", "load", "Landroidx/paging/PagingSource$LoadResult;", "params", "Landroidx/paging/PagingSource$LoadParams;", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "home_debug"})
public final class NewsPagingSource extends androidx.paging.PagingSource<java.lang.Integer, com.muh.arifandi.dicoding.domain.news.model.Article> {
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.features.home.data.remote.api.NewsApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao articleDao = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String category = null;
    
    public NewsPagingSource(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.home.data.remote.api.NewsApiService apiService, @org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao articleDao, @org.jetbrains.annotations.Nullable()
    java.lang.String category) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Integer getRefreshKey(@org.jetbrains.annotations.NotNull()
    androidx.paging.PagingState<java.lang.Integer, com.muh.arifandi.dicoding.domain.news.model.Article> state) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object load(@org.jetbrains.annotations.NotNull()
    androidx.paging.PagingSource.LoadParams<java.lang.Integer> params, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.paging.PagingSource.LoadResult<java.lang.Integer, com.muh.arifandi.dicoding.domain.news.model.Article>> $completion) {
        return null;
    }
}