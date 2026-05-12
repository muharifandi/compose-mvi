package com.muh.arifandi.dicoding.features.home.di;

import com.muh.arifandi.dicoding.features.home.data.local.NewsDatabase;
import com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideArticleDaoFactory implements Factory<ArticleDao> {
  private final Provider<NewsDatabase> databaseProvider;

  public DatabaseModule_ProvideArticleDaoFactory(Provider<NewsDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ArticleDao get() {
    return provideArticleDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideArticleDaoFactory create(
      Provider<NewsDatabase> databaseProvider) {
    return new DatabaseModule_ProvideArticleDaoFactory(databaseProvider);
  }

  public static ArticleDao provideArticleDao(NewsDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideArticleDao(database));
  }
}
