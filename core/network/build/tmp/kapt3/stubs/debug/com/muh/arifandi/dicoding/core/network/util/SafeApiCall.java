package com.muh.arifandi.dicoding.core.network.util;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J;\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\u0004\"\u0004\b\u0000\u0010\u00062\u001c\u0010\u0007\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002\u00a8\u0006\u0010"}, d2 = {"Lcom/muh/arifandi/dicoding/core/network/util/SafeApiCall;", "", "()V", "flow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/muh/arifandi/dicoding/core/common/ResultState;", "T", "apiCall", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "parseBadRequest", "", "code", "defaultMessage", "parseRateLimit", "network_debug"})
public final class SafeApiCall {
    
    @javax.inject.Inject()
    public SafeApiCall() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final <T extends java.lang.Object>kotlinx.coroutines.flow.Flow<com.muh.arifandi.dicoding.core.common.ResultState<T>> flow(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> apiCall) {
        return null;
    }
    
    private final java.lang.String parseBadRequest(java.lang.String code, java.lang.String defaultMessage) {
        return null;
    }
    
    private final java.lang.String parseRateLimit(java.lang.String code) {
        return null;
    }
}