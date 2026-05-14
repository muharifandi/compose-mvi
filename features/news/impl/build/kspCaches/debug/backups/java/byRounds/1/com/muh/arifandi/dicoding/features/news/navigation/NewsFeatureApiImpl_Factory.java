package com.muh.arifandi.dicoding.features.news.navigation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class NewsFeatureApiImpl_Factory implements Factory<NewsFeatureApiImpl> {
  @Override
  public NewsFeatureApiImpl get() {
    return newInstance();
  }

  public static NewsFeatureApiImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static NewsFeatureApiImpl newInstance() {
    return new NewsFeatureApiImpl();
  }

  private static final class InstanceHolder {
    private static final NewsFeatureApiImpl_Factory INSTANCE = new NewsFeatureApiImpl_Factory();
  }
}
