package com.muh.arifandi.dicoding.core.common.di;

import com.muh.arifandi.dicoding.core.common.navigation.Navigator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class CommonModule_Companion_ProvideNavigatorFactory implements Factory<Navigator> {
  @Override
  public Navigator get() {
    return provideNavigator();
  }

  public static CommonModule_Companion_ProvideNavigatorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Navigator provideNavigator() {
    return Preconditions.checkNotNullFromProvides(CommonModule.Companion.provideNavigator());
  }

  private static final class InstanceHolder {
    private static final CommonModule_Companion_ProvideNavigatorFactory INSTANCE = new CommonModule_Companion_ProvideNavigatorFactory();
  }
}
