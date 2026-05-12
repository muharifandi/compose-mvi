package com.muh.arifandi.dicoding.features.home.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u0018\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\b2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0011"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/data/local/dao/FavoriteDao;", "", "deleteFavoriteByUrl", "", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFavorites", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/muh/arifandi/dicoding/features/home/data/local/entity/FavoriteEntity;", "getFavoriteByUrl", "insertFavorite", "favorite", "(Lcom/muh/arifandi/dicoding/features/home/data/local/entity/FavoriteEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isFavorite", "", "home_debug"})
@androidx.room.Dao()
public abstract interface FavoriteDao {
    
    @androidx.room.Query(value = "SELECT * FROM favorites ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.muh.arifandi.dicoding.features.home.data.local.entity.FavoriteEntity>> getAllFavorites();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertFavorite(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.home.data.local.entity.FavoriteEntity favorite, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM favorites WHERE url = :url")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteFavoriteByUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM favorites WHERE url = :url")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> isFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String url);
    
    @androidx.room.Query(value = "SELECT * FROM favorites WHERE url = :url")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.muh.arifandi.dicoding.features.home.data.local.entity.FavoriteEntity> getFavoriteByUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url);
}