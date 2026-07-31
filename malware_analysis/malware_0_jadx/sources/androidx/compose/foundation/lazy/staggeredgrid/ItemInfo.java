package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimationSpecsNode;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGridItemPlacementAnimator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bR0\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b2\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/ItemInfo;", "", "lane", "", "span", "crossAxisOffset", "(III)V", "<set-?>", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "animations", "getAnimations", "()[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "getCrossAxisOffset", "()I", "setCrossAxisOffset", "(I)V", "getLane", "setLane", "getSpan", "setSpan", "updateAnimation", "", "positionedItem", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ItemInfo {
    private LazyLayoutAnimation[] animations = LazyStaggeredGridItemPlacementAnimatorKt.EmptyArray;
    private int crossAxisOffset;
    private int lane;
    private int span;

    public ItemInfo(int lane, int span, int crossAxisOffset) {
        this.lane = lane;
        this.span = span;
        this.crossAxisOffset = crossAxisOffset;
    }

    public final int getLane() {
        return this.lane;
    }

    public final void setLane(int i) {
        this.lane = i;
    }

    public final int getSpan() {
        return this.span;
    }

    public final void setSpan(int i) {
        this.span = i;
    }

    public final int getCrossAxisOffset() {
        return this.crossAxisOffset;
    }

    public final void setCrossAxisOffset(int i) {
        this.crossAxisOffset = i;
    }

    public final LazyLayoutAnimation[] getAnimations() {
        return this.animations;
    }

    public final void updateAnimation(LazyStaggeredGridMeasuredItem positionedItem, CoroutineScope coroutineScope) {
        int length = this.animations.length;
        for (int i = positionedItem.getPlaceablesCount(); i < length; i++) {
            LazyLayoutAnimation lazyLayoutAnimation = this.animations[i];
            if (lazyLayoutAnimation != null) {
                lazyLayoutAnimation.stopAnimations();
            }
        }
        if (this.animations.length != positionedItem.getPlaceablesCount()) {
            Object[] objArrCopyOf = Arrays.copyOf(this.animations, positionedItem.getPlaceablesCount());
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
            this.animations = (LazyLayoutAnimation[]) objArrCopyOf;
        }
        int placeablesCount = positionedItem.getPlaceablesCount();
        for (int i2 = 0; i2 < placeablesCount; i2++) {
            int index = i2;
            LazyLayoutAnimationSpecsNode specs = LazyStaggeredGridItemPlacementAnimatorKt.getSpecs(positionedItem.getParentData(index));
            if (specs == null) {
                LazyLayoutAnimation lazyLayoutAnimation2 = this.animations[index];
                if (lazyLayoutAnimation2 != null) {
                    lazyLayoutAnimation2.stopAnimations();
                }
                this.animations[index] = null;
            } else {
                LazyLayoutAnimation it = this.animations[index];
                if (it == null) {
                    it = new LazyLayoutAnimation(coroutineScope);
                    this.animations[index] = it;
                }
                it.setAppearanceSpec(specs.getAppearanceSpec());
                it.setPlacementSpec(specs.getPlacementSpec());
            }
        }
    }
}
