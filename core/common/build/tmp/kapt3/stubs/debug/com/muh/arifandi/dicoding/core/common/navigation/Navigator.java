package com.muh.arifandi.dicoding.core.common.navigation;

/**
 * Created by Foundation Team
 * Navigation Bridge Pattern: Interface to decouple feature modules from NavController and specific routes.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H&\u00a8\u0006\n"}, d2 = {"Lcom/muh/arifandi/dicoding/core/common/navigation/Navigator;", "", "navigateAndPopUpTo", "", "route", "popUpTo", "inclusive", "", "navigateBack", "navigateTo", "common_debug"})
public abstract interface Navigator {
    
    public abstract void navigateTo(@org.jetbrains.annotations.NotNull()
    java.lang.Object route);
    
    public abstract void navigateBack();
    
    public abstract void navigateAndPopUpTo(@org.jetbrains.annotations.NotNull()
    java.lang.Object route, @org.jetbrains.annotations.NotNull()
    java.lang.Object popUpTo, boolean inclusive);
    
    /**
     * Created by Foundation Team
     * Navigation Bridge Pattern: Interface to decouple feature modules from NavController and specific routes.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}