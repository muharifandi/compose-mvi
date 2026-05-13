package com.muh.arifandi.dicoding.features.news.ui.bookmark;

import com.muh.arifandi.dicoding.core.common.navigation.Navigator;
import com.muh.arifandi.dicoding.features.news.domain.repository.NewsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class BookmarkViewModel_Factory implements Factory<BookmarkViewModel> {
  private final Provider<NewsRepository> repositoryProvider;

  private final Provider<Navigator> navigatorProvider;

  public BookmarkViewModel_Factory(Provider<NewsRepository> repositoryProvider,
      Provider<Navigator> navigatorProvider) {
    this.repositoryProvider = repositoryProvider;
    this.navigatorProvider = navigatorProvider;
  }

  @Override
  public BookmarkViewModel get() {
    return newInstance(repositoryProvider.get(), navigatorProvider.get());
  }

  public static BookmarkViewModel_Factory create(Provider<NewsRepository> repositoryProvider,
      Provider<Navigator> navigatorProvider) {
    return new BookmarkViewModel_Factory(repositoryProvider, navigatorProvider);
  }

  public static BookmarkViewModel newInstance(NewsRepository repository, Navigator navigator) {
    return new BookmarkViewModel(repository, navigator);
  }
}
