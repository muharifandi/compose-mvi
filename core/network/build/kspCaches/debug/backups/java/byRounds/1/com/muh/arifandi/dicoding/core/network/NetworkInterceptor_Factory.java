package com.muh.arifandi.dicoding.core.network;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
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
public final class NetworkInterceptor_Factory implements Factory<NetworkInterceptor> {
  private final Provider<String> apiKeyProvider;

  public NetworkInterceptor_Factory(Provider<String> apiKeyProvider) {
    this.apiKeyProvider = apiKeyProvider;
  }

  @Override
  public NetworkInterceptor get() {
    return newInstance(apiKeyProvider.get());
  }

  public static NetworkInterceptor_Factory create(Provider<String> apiKeyProvider) {
    return new NetworkInterceptor_Factory(apiKeyProvider);
  }

  public static NetworkInterceptor newInstance(String apiKey) {
    return new NetworkInterceptor(apiKey);
  }
}
