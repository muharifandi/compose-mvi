package com.muh.arifandi.dicoding.core.network.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class SafeApiCall_Factory implements Factory<SafeApiCall> {
  @Override
  public SafeApiCall get() {
    return newInstance();
  }

  public static SafeApiCall_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SafeApiCall newInstance() {
    return new SafeApiCall();
  }

  private static final class InstanceHolder {
    private static final SafeApiCall_Factory INSTANCE = new SafeApiCall_Factory();
  }
}
