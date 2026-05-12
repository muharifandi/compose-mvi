package com.muh.arifandi.dicoding.features.news.data.repository;

import com.muh.arifandi.dicoding.core.network.util.SafeApiCall;
import com.muh.arifandi.dicoding.features.news.data.database.dao.ArticleDao;
import com.muh.arifandi.dicoding.features.news.data.network.api.NewsApiService;
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
public final class NewsRepositoryImpl_Factory implements Factory<NewsRepositoryImpl> {
  private final Provider<NewsApiService> apiServiceProvider;

  private final Provider<ArticleDao> articleDaoProvider;

  private final Provider<SafeApiCall> safeApiCallProvider;

  public NewsRepositoryImpl_Factory(Provider<NewsApiService> apiServiceProvider,
      Provider<ArticleDao> articleDaoProvider, Provider<SafeApiCall> safeApiCallProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.articleDaoProvider = articleDaoProvider;
    this.safeApiCallProvider = safeApiCallProvider;
  }

  @Override
  public NewsRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), articleDaoProvider.get(), safeApiCallProvider.get());
  }

  public static NewsRepositoryImpl_Factory create(Provider<NewsApiService> apiServiceProvider,
      Provider<ArticleDao> articleDaoProvider, Provider<SafeApiCall> safeApiCallProvider) {
    return new NewsRepositoryImpl_Factory(apiServiceProvider, articleDaoProvider, safeApiCallProvider);
  }

  public static NewsRepositoryImpl newInstance(NewsApiService apiService, ArticleDao articleDao,
      SafeApiCall safeApiCall) {
    return new NewsRepositoryImpl(apiService, articleDao, safeApiCall);
  }
}
