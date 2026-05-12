package com.muh.arifandi.dicoding.features.bookmark;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001aP\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001a.\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007\u00a8\u0006\u0013"}, d2 = {"BookmarkContent", "", "state", "Lcom/muh/arifandi/dicoding/features/bookmark/state/BookmarkState;", "onBackClick", "Lkotlin/Function0;", "onArticleClick", "Lkotlin/Function1;", "Lcom/muh/arifandi/dicoding/domain/news/model/Article;", "onDeleteClick", "", "modifier", "Landroidx/compose/ui/Modifier;", "BookmarkScreen", "navController", "Landroidx/navigation/NavController;", "onNavigateToDetail", "viewModel", "Lcom/muh/arifandi/dicoding/features/bookmark/BookmarkViewModel;", "bookmark_debug"})
public final class BookmarkScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void BookmarkScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNavigateToDetail, @org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.bookmark.BookmarkViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void BookmarkContent(@org.jetbrains.annotations.NotNull()
    com.muh.arifandi.dicoding.features.bookmark.state.BookmarkState state, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.muh.arifandi.dicoding.domain.news.model.Article, kotlin.Unit> onArticleClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDeleteClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}