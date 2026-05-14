package com.muh.arifandi.dicoding.core.common.security;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class SecurityProvider_Factory implements Factory<SecurityProvider> {
  private final Provider<Context> contextProvider;

  public SecurityProvider_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SecurityProvider get() {
    return newInstance(contextProvider.get());
  }

  public static SecurityProvider_Factory create(Provider<Context> contextProvider) {
    return new SecurityProvider_Factory(contextProvider);
  }

  public static SecurityProvider newInstance(Context context) {
    return new SecurityProvider(context);
  }
}
