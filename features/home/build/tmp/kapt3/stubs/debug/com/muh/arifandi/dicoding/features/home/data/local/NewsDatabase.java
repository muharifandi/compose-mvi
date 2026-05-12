package com.muh.arifandi.dicoding.features.home.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/data/local/NewsDatabase;", "Landroidx/room/RoomDatabase;", "()V", "articleDao", "Lcom/muh/arifandi/dicoding/features/home/data/local/dao/ArticleDao;", "favoriteDao", "Lcom/muh/arifandi/dicoding/features/home/data/local/dao/FavoriteDao;", "home_debug"})
@androidx.room.Database(entities = {com.muh.arifandi.dicoding.features.home.data.local.entity.ArticleEntity.class, com.muh.arifandi.dicoding.features.home.data.local.entity.FavoriteEntity.class}, version = 2, exportSchema = false)
public abstract class NewsDatabase extends androidx.room.RoomDatabase {
    
    public NewsDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao articleDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.muh.arifandi.dicoding.features.home.data.local.dao.FavoriteDao favoriteDao();
}