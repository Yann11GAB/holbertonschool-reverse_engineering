package androidx.compose.material3.carousel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Keyline.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0000\u001a9\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0000¨\u0006\u000e"}, d2 = {"keylineListOf", "Landroidx/compose/material3/carousel/KeylineList;", "carouselMainAxisSize", "", "carouselAlignment", "Landroidx/compose/material3/carousel/CarouselAlignment;", "keylines", "Lkotlin/Function1;", "Landroidx/compose/material3/carousel/KeylineListScope;", "", "Lkotlin/ExtensionFunctionType;", "pivotIndex", "", "pivotOffset", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeylineKt {
    public static final KeylineList keylineListOf(float carouselMainAxisSize, CarouselAlignment carouselAlignment, Function1<? super KeylineListScope, Unit> function1) {
        KeylineListScopeImpl keylineListScope = new KeylineListScopeImpl();
        function1.invoke(keylineListScope);
        return keylineListScope.createWithAlignment(carouselMainAxisSize, carouselAlignment);
    }

    public static final KeylineList keylineListOf(float carouselMainAxisSize, int pivotIndex, float pivotOffset, Function1<? super KeylineListScope, Unit> function1) {
        KeylineListScopeImpl keylineListScope = new KeylineListScopeImpl();
        function1.invoke(keylineListScope);
        return keylineListScope.createWithPivot(carouselMainAxisSize, pivotIndex, pivotOffset);
    }
}
