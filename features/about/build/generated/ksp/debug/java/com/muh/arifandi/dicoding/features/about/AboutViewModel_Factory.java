package com.muh.arifandi.dicoding.features.about;

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
public final class AboutViewModel_Factory implements Factory<AboutViewModel> {
  private final Provider<Navigator> navigatorProvider;

  public AboutViewModel_Factory(Provider<Navigator> navigatorProvider) {
    this.navigatorProvider = navigatorProvider;
  }

  @Override
  public AboutViewModel get() {
    return newInstance(navigatorProvider.get());
  }

  public static AboutViewModel_Factory create(Provider<Navigator> navigatorProvider) {
    return new AboutViewModel_Factory(navigatorProvider);
  }

  public static AboutViewModel newInstance(Navigator navigator) {
    return new AboutViewModel(navigator);
  }
}
