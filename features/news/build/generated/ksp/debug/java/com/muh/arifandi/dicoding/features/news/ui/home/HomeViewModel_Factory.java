package com.muh.arifandi.dicoding.features.news.ui.home;

import androidx.lifecycle.SavedStateHandle;
import com.muh.arifandi.dicoding.features.news.domain.usecase.GetTopHeadlinesUseCase;
import com.muh.arifandi.dicoding.features.news.domain.usecase.SearchNewsUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetTopHeadlinesUseCase> getTopHeadlinesUseCaseProvider;

  private final Provider<SearchNewsUseCase> searchNewsUseCaseProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public HomeViewModel_Factory(Provider<GetTopHeadlinesUseCase> getTopHeadlinesUseCaseProvider,
      Provider<SearchNewsUseCase> searchNewsUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.getTopHeadlinesUseCaseProvider = getTopHeadlinesUseCaseProvider;
    this.searchNewsUseCaseProvider = searchNewsUseCaseProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getTopHeadlinesUseCaseProvider.get(), searchNewsUseCaseProvider.get(), savedStateHandleProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetTopHeadlinesUseCase> getTopHeadlinesUseCaseProvider,
      Provider<SearchNewsUseCase> searchNewsUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new HomeViewModel_Factory(getTopHeadlinesUseCaseProvider, searchNewsUseCaseProvider, savedStateHandleProvider);
  }

  public static HomeViewModel newInstance(GetTopHeadlinesUseCase getTopHeadlinesUseCase,
      SearchNewsUseCase searchNewsUseCase, SavedStateHandle savedStateHandle) {
    return new HomeViewModel(getTopHeadlinesUseCase, searchNewsUseCase, savedStateHandle);
  }
}
