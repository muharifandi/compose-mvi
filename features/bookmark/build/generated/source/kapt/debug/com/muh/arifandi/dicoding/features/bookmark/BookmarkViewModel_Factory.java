package com.muh.arifandi.dicoding.features.bookmark;

import com.muh.arifandi.dicoding.domain.news.repository.NewsRepository;
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

  public BookmarkViewModel_Factory(Provider<NewsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public BookmarkViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static BookmarkViewModel_Factory create(Provider<NewsRepository> repositoryProvider) {
    return new BookmarkViewModel_Factory(repositoryProvider);
  }

  public static BookmarkViewModel newInstance(NewsRepository repository) {
    return new BookmarkViewModel(repository);
  }
}
