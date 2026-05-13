package com.muh.arifandi.dicoding.features.news.ui.home;

import androidx.lifecycle.SavedStateHandle;
import com.muh.arifandi.dicoding.core.common.navigation.Navigator;
import com.muh.arifandi.dicoding.features.news.data.repository.NewsPagingRepository;
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
  private final Provider<SearchNewsUseCase> searchNewsUseCaseProvider;

  private final Provider<NewsPagingRepository> pagingRepositoryProvider;

  private final Provider<Navigator> navigatorProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public HomeViewModel_Factory(Provider<SearchNewsUseCase> searchNewsUseCaseProvider,
      Provider<NewsPagingRepository> pagingRepositoryProvider,
      Provider<Navigator> navigatorProvider, Provider<SavedStateHandle> savedStateHandleProvider) {
    this.searchNewsUseCaseProvider = searchNewsUseCaseProvider;
    this.pagingRepositoryProvider = pagingRepositoryProvider;
    this.navigatorProvider = navigatorProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(searchNewsUseCaseProvider.get(), pagingRepositoryProvider.get(), navigatorProvider.get(), savedStateHandleProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<SearchNewsUseCase> searchNewsUseCaseProvider,
      Provider<NewsPagingRepository> pagingRepositoryProvider,
      Provider<Navigator> navigatorProvider, Provider<SavedStateHandle> savedStateHandleProvider) {
    return new HomeViewModel_Factory(searchNewsUseCaseProvider, pagingRepositoryProvider, navigatorProvider, savedStateHandleProvider);
  }

  public static HomeViewModel newInstance(SearchNewsUseCase searchNewsUseCase,
      NewsPagingRepository pagingRepository, Navigator navigator,
      SavedStateHandle savedStateHandle) {
    return new HomeViewModel(searchNewsUseCase, pagingRepository, navigator, savedStateHandle);
  }
}
