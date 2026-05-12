package com.muh.arifandi.dicoding.core.common.repository;

/**
 * Created by Foundation Team
 * Generic BaseRepository to standardize error handling and flow emission across features.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J=\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\u0004\"\u0004\b\u0000\u0010\u00062\u001c\u0010\u0007\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0004\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/muh/arifandi/dicoding/core/common/repository/BaseRepository;", "", "()V", "safeNetworkCall", "Lkotlinx/coroutines/flow/Flow;", "Lcom/muh/arifandi/dicoding/core/common/ResultState;", "T", "call", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "common_debug"})
public abstract class BaseRepository {
    
    public BaseRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final <T extends java.lang.Object>kotlinx.coroutines.flow.Flow<com.muh.arifandi.dicoding.core.common.ResultState<T>> safeNetworkCall(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> call) {
        return null;
    }
}