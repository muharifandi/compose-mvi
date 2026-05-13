package com.muh.arifandi.dicoding.features.splash;

import com.muh.arifandi.dicoding.core.common.navigation.Navigator;
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
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<Navigator> navigatorProvider;

  public SplashViewModel_Factory(Provider<Navigator> navigatorProvider) {
    this.navigatorProvider = navigatorProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(navigatorProvider.get());
  }

  public static SplashViewModel_Factory create(Provider<Navigator> navigatorProvider) {
    return new SplashViewModel_Factory(navigatorProvider);
  }

  public static SplashViewModel newInstance(Navigator navigator) {
    return new SplashViewModel(navigator);
  }
}
