package com.muh.arifandi.dicoding.core.common.mvi;

/**
 * Created by Muh. Arifandi on 06/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: BaseViewModel
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0004*\b\b\u0002\u0010\u0005*\u00020\u00062\u00020\u0007B\r\u0012\u0006\u0010\b\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\tJ\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00028\u0001H&\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00020\u001cH\u0004J!\u0010\u001d\u001a\u00020\u00172\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\u001f\u00a2\u0006\u0002\b H\u0004R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006!"}, d2 = {"Lcom/muh/arifandi/dicoding/core/common/mvi/BaseViewModel;", "S", "Lcom/muh/arifandi/dicoding/core/common/mvi/UiState;", "I", "Lcom/muh/arifandi/dicoding/core/common/mvi/UiIntent;", "E", "Lcom/muh/arifandi/dicoding/core/common/mvi/UiEffect;", "Landroidx/lifecycle/ViewModel;", "initialState", "(Lcom/muh/arifandi/dicoding/core/common/mvi/UiState;)V", "_effect", "Lkotlinx/coroutines/channels/Channel;", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "effect", "Lkotlinx/coroutines/flow/Flow;", "getEffect", "()Lkotlinx/coroutines/flow/Flow;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "processIntent", "", "intent", "(Lcom/muh/arifandi/dicoding/core/common/mvi/UiIntent;)V", "sendEffect", "builder", "Lkotlin/Function0;", "setState", "reducer", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "common_debug"})
public abstract class BaseViewModel<S extends com.muh.arifandi.dicoding.core.common.mvi.UiState, I extends com.muh.arifandi.dicoding.core.common.mvi.UiIntent, E extends com.muh.arifandi.dicoding.core.common.mvi.UiEffect> extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<S> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<S> state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<E> _effect = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<E> effect = null;
    
    public BaseViewModel(@org.jetbrains.annotations.NotNull()
    S initialState) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<S> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<E> getEffect() {
        return null;
    }
    
    protected final void setState(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super S, ? extends S> reducer) {
    }
    
    protected final void sendEffect(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends E> builder) {
    }
    
    public abstract void processIntent(@org.jetbrains.annotations.NotNull()
    I intent);
}