package com.muh.arifandi.dicoding.features.news.data.di;

import android.content.Context;
import com.muh.arifandi.dicoding.features.news.data.database.NewsDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class NewsDatabaseModule_ProvideNewsDatabaseFactory implements Factory<NewsDatabase> {
  private final Provider<Context> contextProvider;

  public NewsDatabaseModule_ProvideNewsDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NewsDatabase get() {
    return provideNewsDatabase(contextProvider.get());
  }

  public static NewsDatabaseModule_ProvideNewsDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new NewsDatabaseModule_ProvideNewsDatabaseFactory(contextProvider);
  }

  public static NewsDatabase provideNewsDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(NewsDatabaseModule.INSTANCE.provideNewsDatabase(context));
  }
}
