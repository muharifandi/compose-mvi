package com.muh.arifandi.dicoding.core.network.di;

import com.muh.arifandi.dicoding.core.network.NetworkInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
public final class CoreNetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<HttpLoggingInterceptor> loggingInterceptorProvider;

  private final Provider<NetworkInterceptor> networkInterceptorProvider;

  public CoreNetworkModule_ProvideOkHttpClientFactory(
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<NetworkInterceptor> networkInterceptorProvider) {
    this.loggingInterceptorProvider = loggingInterceptorProvider;
    this.networkInterceptorProvider = networkInterceptorProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(loggingInterceptorProvider.get(), networkInterceptorProvider.get());
  }

  public static CoreNetworkModule_ProvideOkHttpClientFactory create(
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<NetworkInterceptor> networkInterceptorProvider) {
    return new CoreNetworkModule_ProvideOkHttpClientFactory(loggingInterceptorProvider, networkInterceptorProvider);
  }

  public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
      NetworkInterceptor networkInterceptor) {
    return Preconditions.checkNotNullFromProvides(CoreNetworkModule.INSTANCE.provideOkHttpClient(loggingInterceptor, networkInterceptor));
  }
}
