package com.muh.arifandi.dicoding.features.home.data.remote.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ,\u0010\u000b\u001a\u00020\u00032\b\b\u0001\u0010\f\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/data/remote/api/NewsApiService;", "", "getTopHeadlines", "Lcom/muh/arifandi/dicoding/features/home/data/remote/dto/NewsResponse;", "country", "", "category", "page", "", "pageSize", "(Ljava/lang/String;Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchNews", "query", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "home_debug"})
public abstract interface NewsApiService {
    
    @retrofit2.http.GET(value = "top-headlines")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTopHeadlines(@retrofit2.http.Query(value = "country")
    @org.jetbrains.annotations.NotNull()
    java.lang.String country, @retrofit2.http.Query(value = "category")
    @org.jetbrains.annotations.Nullable()
    java.lang.String category, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "pageSize")
    int pageSize, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.muh.arifandi.dicoding.features.home.data.remote.dto.NewsResponse> $completion);
    
    @retrofit2.http.GET(value = "everything")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchNews(@retrofit2.http.Query(value = "q")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "pageSize")
    int pageSize, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.muh.arifandi.dicoding.features.home.data.remote.dto.NewsResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}