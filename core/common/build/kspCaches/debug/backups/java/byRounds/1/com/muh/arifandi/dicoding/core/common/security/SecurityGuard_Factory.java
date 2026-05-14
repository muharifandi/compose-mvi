package com.muh.arifandi.dicoding.core.common.security;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class SecurityGuard_Factory implements Factory<SecurityGuard> {
  private final Provider<SecurityProvider> securityProvider;

  public SecurityGuard_Factory(Provider<SecurityProvider> securityProvider) {
    this.securityProvider = securityProvider;
  }

  @Override
  public SecurityGuard get() {
    return newInstance(securityProvider.get());
  }

  public static SecurityGuard_Factory create(Provider<SecurityProvider> securityProvider) {
    return new SecurityGuard_Factory(securityProvider);
  }

  public static SecurityGuard newInstance(SecurityProvider securityProvider) {
    return new SecurityGuard(securityProvider);
  }
}
