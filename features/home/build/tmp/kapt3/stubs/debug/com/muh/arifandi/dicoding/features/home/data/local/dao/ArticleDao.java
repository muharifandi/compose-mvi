package com.muh.arifandi.dicoding.features.home.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b2\u0006\u0010\n\u001a\u00020\u0005H\'J\u001e\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\'J\u001c\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\fH\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/data/local/dao/ArticleDao;", "", "deleteArticlesByCategory", "", "category", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getArticleByUrl", "Lkotlinx/coroutines/flow/Flow;", "Lcom/muh/arifandi/dicoding/features/home/data/local/entity/ArticleEntity;", "url", "getArticlesByCategory", "", "insertArticles", "articles", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "home_debug"})
@androidx.room.Dao()
public abstract interface ArticleDao {
    
    @androidx.room.Query(value = "SELECT * FROM articles WHERE category = :category")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.muh.arifandi.dicoding.features.home.data.local.entity.ArticleEntity>> getArticlesByCategory(@org.jetbrains.annotations.Nullable()
    java.lang.String category);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertArticles(@org.jetbrains.annotations.NotNull()
    java.util.List<com.muh.arifandi.dicoding.features.home.data.local.entity.ArticleEntity> articles, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM articles WHERE category = :category")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteArticlesByCategory(@org.jetbrains.annotations.Nullable()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM articles WHERE url = :url")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.muh.arifandi.dicoding.features.home.data.local.entity.ArticleEntity> getArticleByUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url);
}