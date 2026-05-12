package com.muh.arifandi.dicoding.features.home.state;

/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: HomeIntent
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t\u00a8\u0006\n"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent;", "Lcom/muh/arifandi/dicoding/core/common/mvi/UiIntent;", "ClickArticle", "FilterCategory", "Refresh", "SearchArticle", "Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent$ClickArticle;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent$FilterCategory;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent$Refresh;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent$SearchArticle;", "home_debug"})
public abstract interface HomeIntent extends com.muh.arifandi.dicoding.core.common.mvi.UiIntent {
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent$ClickArticle;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent;", "article", "Lcom/muh/arifandi/dicoding/domain/news/model/Article;", "(Lcom/muh/arifandi/dicoding/domain/news/model/Article;)V", "getArticle", "()Lcom/muh/arifandi/dicoding/domain/news/model/Article;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "home_debug"})
    public static final class ClickArticle implements com.muh.arifandi.dicoding.features.home.state.HomeIntent {
        @org.jetbrains.annotations.NotNull()
        private final com.muh.arifandi.dicoding.domain.news.model.Article article = null;
        
        public ClickArticle(@org.jetbrains.annotations.NotNull()
        com.muh.arifandi.dicoding.domain.news.model.Article article) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.muh.arifandi.dicoding.domain.news.model.Article getArticle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.muh.arifandi.dicoding.domain.news.model.Article component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.muh.arifandi.dicoding.features.home.state.HomeIntent.ClickArticle copy(@org.jetbrains.annotations.NotNull()
        com.muh.arifandi.dicoding.domain.news.model.Article article) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent$FilterCategory;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent;", "category", "", "(Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "home_debug"})
    public static final class FilterCategory implements com.muh.arifandi.dicoding.features.home.state.HomeIntent {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String category = null;
        
        public FilterCategory(@org.jetbrains.annotations.Nullable()
        java.lang.String category) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getCategory() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.muh.arifandi.dicoding.features.home.state.HomeIntent.FilterCategory copy(@org.jetbrains.annotations.Nullable()
        java.lang.String category) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent$Refresh;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "home_debug"})
    public static final class Refresh implements com.muh.arifandi.dicoding.features.home.state.HomeIntent {
        @org.jetbrains.annotations.NotNull()
        public static final com.muh.arifandi.dicoding.features.home.state.HomeIntent.Refresh INSTANCE = null;
        
        private Refresh() {
            super();
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent$SearchArticle;", "Lcom/muh/arifandi/dicoding/features/home/state/HomeIntent;", "query", "", "(Ljava/lang/String;)V", "getQuery", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "home_debug"})
    public static final class SearchArticle implements com.muh.arifandi.dicoding.features.home.state.HomeIntent {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String query = null;
        
        public SearchArticle(@org.jetbrains.annotations.NotNull()
        java.lang.String query) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getQuery() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.muh.arifandi.dicoding.features.home.state.HomeIntent.SearchArticle copy(@org.jetbrains.annotations.NotNull()
        java.lang.String query) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}