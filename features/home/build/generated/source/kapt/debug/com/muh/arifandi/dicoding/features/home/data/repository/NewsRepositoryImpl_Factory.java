package com.muh.arifandi.dicoding.features.home.data.repository;

import com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao;
import com.muh.arifandi.dicoding.features.home.data.local.dao.FavoriteDao;
import com.muh.arifandi.dicoding.features.home.data.remote.api.NewsApiService;
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

  private final Provider<FavoriteDao> favoriteDaoProvider;

  public NewsRepositoryImpl_Factory(Provider<NewsApiService> apiServiceProvider,
      Provider<ArticleDao> articleDaoProvider, Provider<FavoriteDao> favoriteDaoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.articleDaoProvider = articleDaoProvider;
    this.favoriteDaoProvider = favoriteDaoProvider;
  }

  @Override
  public NewsRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), articleDaoProvider.get(), favoriteDaoProvider.get());
  }

  public static NewsRepositoryImpl_Factory create(Provider<NewsApiService> apiServiceProvider,
      Provider<ArticleDao> articleDaoProvider, Provider<FavoriteDao> favoriteDaoProvider) {
    return new NewsRepositoryImpl_Factory(apiServiceProvider, articleDaoProvider, favoriteDaoProvider);
  }

  public static NewsRepositoryImpl newInstance(NewsApiService apiService, ArticleDao articleDao,
      FavoriteDao favoriteDao) {
    return new NewsRepositoryImpl(apiService, articleDao, favoriteDao);
  }
}
