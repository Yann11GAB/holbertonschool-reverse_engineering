package androidx.compose.material3.carousel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Keyline.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016JL\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012H\u0002J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aJ\u001e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006J\b\u0010\u001c\u001a\u00020\u0004H\u0002J\u0018\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0002J \u0010\u001f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/compose/material3/carousel/KeylineListScopeImpl;", "Landroidx/compose/material3/carousel/KeylineListScope;", "()V", "firstFocalIndex", "", "focalItemSize", "", "pivotIndex", "pivotOffset", "tmpKeylines", "", "Landroidx/compose/material3/carousel/KeylineListScopeImpl$TmpKeyline;", "add", "", "size", "isAnchor", "", "createKeylinesWithPivot", "", "Landroidx/compose/material3/carousel/Keyline;", "lastFocalIndex", "itemMainAxisSize", "carouselMainAxisSize", "createWithAlignment", "Landroidx/compose/material3/carousel/KeylineList;", "carouselAlignment", "Landroidx/compose/material3/carousel/CarouselAlignment;", "createWithPivot", "findLastFocalIndex", "isCutoffLeft", "offset", "isCutoffRight", "TmpKeyline", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class KeylineListScopeImpl implements KeylineListScope {
    private float focalItemSize;
    private float pivotOffset;
    private int firstFocalIndex = -1;
    private int pivotIndex = -1;
    private final List<TmpKeyline> tmpKeylines = new ArrayList();

    /* JADX INFO: compiled from: Keyline.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CarouselAlignment.values().length];
            try {
                iArr[CarouselAlignment.Start.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CarouselAlignment.Center.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CarouselAlignment.End.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: compiled from: Keyline.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/carousel/KeylineListScopeImpl$TmpKeyline;", "", "size", "", "isAnchor", "", "(FZ)V", "()Z", "getSize", "()F", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final /* data */ class TmpKeyline {
        private final boolean isAnchor;
        private final float size;

        public static /* synthetic */ TmpKeyline copy$default(TmpKeyline tmpKeyline, float f, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                f = tmpKeyline.size;
            }
            if ((i & 2) != 0) {
                z = tmpKeyline.isAnchor;
            }
            return tmpKeyline.copy(f, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getSize() {
            return this.size;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsAnchor() {
            return this.isAnchor;
        }

        public final TmpKeyline copy(float size, boolean isAnchor) {
            return new TmpKeyline(size, isAnchor);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TmpKeyline)) {
                return false;
            }
            TmpKeyline tmpKeyline = (TmpKeyline) other;
            return Float.compare(this.size, tmpKeyline.size) == 0 && this.isAnchor == tmpKeyline.isAnchor;
        }

        public int hashCode() {
            return (Float.hashCode(this.size) * 31) + Boolean.hashCode(this.isAnchor);
        }

        public String toString() {
            return "TmpKeyline(size=" + this.size + ", isAnchor=" + this.isAnchor + ')';
        }

        public TmpKeyline(float size, boolean isAnchor) {
            this.size = size;
            this.isAnchor = isAnchor;
        }

        public final float getSize() {
            return this.size;
        }

        public final boolean isAnchor() {
            return this.isAnchor;
        }
    }

    @Override // androidx.compose.material3.carousel.KeylineListScope
    public void add(float size, boolean isAnchor) {
        this.tmpKeylines.add(new TmpKeyline(size, isAnchor));
        if (size > this.focalItemSize) {
            this.firstFocalIndex = CollectionsKt.getLastIndex(this.tmpKeylines);
            this.focalItemSize = size;
        }
    }

    public final KeylineList createWithPivot(float carouselMainAxisSize, int pivotIndex, float pivotOffset) {
        return new KeylineList(createKeylinesWithPivot(pivotIndex, pivotOffset, this.firstFocalIndex, findLastFocalIndex(), this.focalItemSize, carouselMainAxisSize, this.tmpKeylines));
    }

    public final KeylineList createWithAlignment(float carouselMainAxisSize, CarouselAlignment carouselAlignment) {
        float f;
        int lastFocalIndex = findLastFocalIndex();
        int focalItemCount = lastFocalIndex - this.firstFocalIndex;
        this.pivotIndex = this.firstFocalIndex;
        switch (WhenMappings.$EnumSwitchMapping$0[carouselAlignment.ordinal()]) {
            case 1:
                f = this.focalItemSize / 2;
                break;
            case 2:
                float f2 = 2;
                f = (carouselMainAxisSize / f2) - ((this.focalItemSize / f2) * focalItemCount);
                break;
            case 3:
                f = carouselMainAxisSize - (this.focalItemSize / 2);
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        this.pivotOffset = f;
        return new KeylineList(createKeylinesWithPivot(this.pivotIndex, this.pivotOffset, this.firstFocalIndex, lastFocalIndex, this.focalItemSize, carouselMainAxisSize, this.tmpKeylines));
    }

    private final int findLastFocalIndex() {
        int lastFocalIndex = this.firstFocalIndex;
        while (lastFocalIndex < CollectionsKt.getLastIndex(this.tmpKeylines)) {
            if (!(this.tmpKeylines.get(lastFocalIndex + 1).getSize() == this.focalItemSize)) {
                break;
            }
            lastFocalIndex++;
        }
        return lastFocalIndex;
    }

    private final List<Keyline> createKeylinesWithPivot(int pivotIndex, float pivotOffset, int firstFocalIndex, int lastFocalIndex, float itemMainAxisSize, float carouselMainAxisSize, List<TmpKeyline> tmpKeylines) {
        float pivotCutoff;
        float cutoff;
        TmpKeyline pivot = tmpKeylines.get(pivotIndex);
        List keylines = new ArrayList();
        if (isCutoffLeft(pivot.getSize(), pivotOffset)) {
            pivotCutoff = pivotOffset - (pivot.getSize() / 2);
        } else if (isCutoffRight(pivot.getSize(), pivotOffset, carouselMainAxisSize)) {
            pivotCutoff = ((pivot.getSize() / 2) + pivotOffset) - carouselMainAxisSize;
        } else {
            pivotCutoff = 0.0f;
        }
        keylines.add(new Keyline(pivot.getSize(), pivotOffset, pivotOffset, firstFocalIndex <= pivotIndex && pivotIndex <= lastFocalIndex, pivot.isAnchor(), true, pivotCutoff));
        float f = 2;
        float offset = pivotOffset - (itemMainAxisSize / f);
        float unadjustedOffset = pivotOffset - (itemMainAxisSize / f);
        Iterable iterable = null;
        Iterable $this$forEach$iv = RangesKt.downTo(pivotIndex - 1, 0);
        int $i$f$forEach = 0;
        Iterator<Integer> it = $this$forEach$iv.iterator();
        while (it.hasNext()) {
            int element$iv = ((IntIterator) it).nextInt();
            TmpKeyline tmp = tmpKeylines.get(element$iv);
            Iterable $this$forEach$iv2 = $this$forEach$iv;
            float tmpOffset = offset - (tmp.getSize() / f);
            float tmpUnadjustedOffset = unadjustedOffset - (itemMainAxisSize / f);
            int $i$f$forEach2 = $i$f$forEach;
            float cutoff2 = isCutoffLeft(tmp.getSize(), tmpOffset) ? tmpOffset - (tmp.getSize() / f) : 0.0f;
            keylines.add(0, new Keyline(tmp.getSize(), tmpOffset, tmpUnadjustedOffset, firstFocalIndex <= element$iv && element$iv <= lastFocalIndex, tmp.isAnchor(), false, cutoff2));
            offset -= tmp.getSize();
            unadjustedOffset -= itemMainAxisSize;
            iterable = null;
            $this$forEach$iv = $this$forEach$iv2;
            $i$f$forEach = $i$f$forEach2;
        }
        float offset2 = pivotOffset + (itemMainAxisSize / f);
        float unadjustedOffset2 = pivotOffset + (itemMainAxisSize / f);
        Iterable $this$forEach$iv3 = RangesKt.until(pivotIndex + 1, tmpKeylines.size());
        Iterator<Integer> it2 = $this$forEach$iv3.iterator();
        while (it2.hasNext()) {
            int element$iv2 = ((IntIterator) it2).nextInt();
            TmpKeyline tmp2 = tmpKeylines.get(element$iv2);
            float tmpOffset2 = offset2 + (tmp2.getSize() / f);
            float tmpUnadjustedOffset2 = unadjustedOffset2 + (itemMainAxisSize / f);
            Iterable $this$forEach$iv4 = $this$forEach$iv3;
            if (isCutoffRight(tmp2.getSize(), tmpOffset2, carouselMainAxisSize)) {
                cutoff = ((tmp2.getSize() / f) + tmpOffset2) - carouselMainAxisSize;
            } else {
                cutoff = 0.0f;
            }
            keylines.add(new Keyline(tmp2.getSize(), tmpOffset2, tmpUnadjustedOffset2, firstFocalIndex <= element$iv2 && element$iv2 <= lastFocalIndex, tmp2.isAnchor(), false, cutoff));
            offset2 += tmp2.getSize();
            unadjustedOffset2 += itemMainAxisSize;
            $this$forEach$iv3 = $this$forEach$iv4;
        }
        return keylines;
    }

    private final boolean isCutoffLeft(float size, float offset) {
        float f = 2;
        return offset - (size / f) < 0.0f && (size / f) + offset > 0.0f;
    }

    private final boolean isCutoffRight(float size, float offset, float carouselMainAxisSize) {
        float f = 2;
        return offset - (size / f) < carouselMainAxisSize && (size / f) + offset > carouselMainAxisSize;
    }
}
