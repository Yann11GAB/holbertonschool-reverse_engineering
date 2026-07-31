package androidx.compose.material3.carousel;

import androidx.collection.FloatList;
import androidx.collection.FloatListKt;
import androidx.collection.MutableFloatList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Strategy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014BK\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ'\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/material3/carousel/Strategy;", "", "defaultKeylines", "Landroidx/compose/material3/carousel/KeylineList;", "startKeylineSteps", "", "endKeylineSteps", "startShiftDistance", "", "endShiftDistance", "startShiftPoints", "Landroidx/collection/FloatList;", "endShiftPoints", "(Landroidx/compose/material3/carousel/KeylineList;Ljava/util/List;Ljava/util/List;FFLandroidx/collection/FloatList;Landroidx/collection/FloatList;)V", "getKeylineListForScrollOffset", "scrollOffset", "maxScrollOffset", "roundToNearestStep", "", "getKeylineListForScrollOffset$material3_release", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Strategy {
    private final KeylineList defaultKeylines;
    private final List<KeylineList> endKeylineSteps;
    private final float endShiftDistance;
    private final FloatList endShiftPoints;
    private final List<KeylineList> startKeylineSteps;
    private final float startShiftDistance;
    private final FloatList startShiftPoints;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ Strategy(KeylineList keylineList, List list, List list2, float f, float f2, FloatList floatList, FloatList floatList2, DefaultConstructorMarker defaultConstructorMarker) {
        this(keylineList, list, list2, f, f2, floatList, floatList2);
    }

    private Strategy(KeylineList defaultKeylines, List<KeylineList> list, List<KeylineList> list2, float startShiftDistance, float endShiftDistance, FloatList startShiftPoints, FloatList endShiftPoints) {
        this.defaultKeylines = defaultKeylines;
        this.startKeylineSteps = list;
        this.endKeylineSteps = list2;
        this.startShiftDistance = startShiftDistance;
        this.endShiftDistance = endShiftDistance;
        this.startShiftPoints = startShiftPoints;
        this.endShiftPoints = endShiftPoints;
    }

    public static /* synthetic */ KeylineList getKeylineListForScrollOffset$material3_release$default(Strategy strategy, float f, float f2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return strategy.getKeylineListForScrollOffset$material3_release(f, f2, z);
    }

    public final KeylineList getKeylineListForScrollOffset$material3_release(float scrollOffset, float maxScrollOffset, boolean roundToNearestStep) {
        int roundedStepIndex;
        float startShiftOffset = this.startShiftDistance;
        float endShiftOffset = maxScrollOffset - this.endShiftDistance;
        boolean z = false;
        if (startShiftOffset <= scrollOffset && scrollOffset <= endShiftOffset) {
            z = true;
        }
        if (!z) {
            float interpolation = StrategyKt.lerp(1.0f, 0.0f, 0.0f, startShiftOffset, scrollOffset);
            FloatList shiftPoints = this.startShiftPoints;
            List<KeylineList> list = this.startKeylineSteps;
            if (scrollOffset > endShiftOffset) {
                interpolation = StrategyKt.lerp(0.0f, 1.0f, endShiftOffset, maxScrollOffset, scrollOffset);
                shiftPoints = this.endShiftPoints;
                list = this.endKeylineSteps;
            }
            Companion.ShiftPointRange shiftPointRange = INSTANCE.getShiftPointRange(list.size(), shiftPoints, interpolation);
            if (roundToNearestStep) {
                if (MathKt.roundToInt(shiftPointRange.getSteppedInterpolation()) == 0) {
                    roundedStepIndex = shiftPointRange.getFromStepIndex();
                } else {
                    roundedStepIndex = shiftPointRange.getToStepIndex();
                }
                return list.get(roundedStepIndex);
            }
            int roundedStepIndex2 = shiftPointRange.getFromStepIndex();
            return StrategyKt.lerp(list.get(roundedStepIndex2), list.get(shiftPointRange.getToStepIndex()), shiftPointRange.getSteppedInterpolation());
        }
        return this.defaultKeylines;
    }

    /* JADX INFO: compiled from: Strategy.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001!B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J&\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J(\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J(\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0010H\u0002¨\u0006\""}, d2 = {"Landroidx/compose/material3/carousel/Strategy$Companion;", "", "()V", "create", "Landroidx/compose/material3/carousel/Strategy;", "carouselMainAxisSize", "", "keylineList", "Landroidx/compose/material3/carousel/KeylineList;", "create$material3_release", "getEndKeylineSteps", "", "defaultKeylines", "getShiftPointRange", "Landroidx/compose/material3/carousel/Strategy$Companion$ShiftPointRange;", "stepsCount", "", "shiftPoint", "Landroidx/collection/FloatList;", "interpolation", "getStartKeylineSteps", "getStepInterpolationPoints", "totalShiftDistance", "steps", "isShiftingLeft", "", "moveKeylineAndCreateShiftedKeylineList", "from", "srcIndex", "dstIndex", "move", "", "Landroidx/compose/material3/carousel/Keyline;", "ShiftPointRange", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Strategy create$material3_release(float carouselMainAxisSize, KeylineList keylineList) {
            List<KeylineList> startKeylineSteps = getStartKeylineSteps(keylineList, carouselMainAxisSize);
            List<KeylineList> endKeylineSteps = getEndKeylineSteps(keylineList, carouselMainAxisSize);
            float startShiftDistance = ((Keyline) CollectionsKt.first((List) CollectionsKt.last((List) startKeylineSteps))).getUnadjustedOffset() - ((Keyline) CollectionsKt.first((List) keylineList)).getUnadjustedOffset();
            float endShiftDistance = ((Keyline) CollectionsKt.last((List) keylineList)).getUnadjustedOffset() - ((Keyline) CollectionsKt.last((List) CollectionsKt.last((List) endKeylineSteps))).getUnadjustedOffset();
            return new Strategy(keylineList, startKeylineSteps, endKeylineSteps, startShiftDistance, endShiftDistance, getStepInterpolationPoints(startShiftDistance, startKeylineSteps, true), getStepInterpolationPoints(endShiftDistance, endKeylineSteps, false), null);
        }

        private final List<KeylineList> getStartKeylineSteps(KeylineList defaultKeylines, float carouselMainAxisSize) {
            List steps = new ArrayList();
            steps.add(defaultKeylines);
            if (defaultKeylines.isFirstFocalItemAtStartOfContainer()) {
                return steps;
            }
            int startIndex = defaultKeylines.getFirstNonAnchorIndex();
            int endIndex = defaultKeylines.getFirstFocalIndex();
            int numberOfSteps = endIndex - startIndex;
            if (numberOfSteps <= 0 && defaultKeylines.getFirstFocal().getCutoff() > 0.0f) {
                steps.add(moveKeylineAndCreateShiftedKeylineList(defaultKeylines, 0, 0, carouselMainAxisSize));
                return steps;
            }
            for (int i = 0; i < numberOfSteps; i++) {
                KeylineList prevStep = (KeylineList) CollectionsKt.last(steps);
                int originalItemIndex = startIndex + i;
                int dstIndex = CollectionsKt.getLastIndex(defaultKeylines);
                if (originalItemIndex > 0) {
                    float originalNeighborBeforeSize = defaultKeylines.get(originalItemIndex - 1).getSize();
                    dstIndex = prevStep.firstIndexAfterFocalRangeWithSize(originalNeighborBeforeSize) - 1;
                }
                steps.add(moveKeylineAndCreateShiftedKeylineList(prevStep, defaultKeylines.getFirstNonAnchorIndex(), dstIndex, carouselMainAxisSize));
            }
            return steps;
        }

        private final List<KeylineList> getEndKeylineSteps(KeylineList defaultKeylines, float carouselMainAxisSize) {
            List steps = new ArrayList();
            steps.add(defaultKeylines);
            if (defaultKeylines.isLastFocalItemAtEndOfContainer(carouselMainAxisSize)) {
                return steps;
            }
            int startIndex = defaultKeylines.getLastFocalIndex();
            int endIndex = defaultKeylines.getLastNonAnchorIndex();
            int numberOfSteps = endIndex - startIndex;
            if (numberOfSteps <= 0 && defaultKeylines.getLastFocal().getCutoff() > 0.0f) {
                steps.add(moveKeylineAndCreateShiftedKeylineList(defaultKeylines, 0, 0, carouselMainAxisSize));
                return steps;
            }
            for (int i = 0; i < numberOfSteps; i++) {
                KeylineList prevStep = (KeylineList) CollectionsKt.last(steps);
                int originalItemIndex = endIndex - i;
                int dstIndex = 0;
                if (originalItemIndex < CollectionsKt.getLastIndex(defaultKeylines)) {
                    float originalNeighborAfterSize = defaultKeylines.get(originalItemIndex + 1).getSize();
                    dstIndex = prevStep.lastIndexBeforeFocalRangeWithSize(originalNeighborAfterSize) + 1;
                }
                KeylineList keylines = moveKeylineAndCreateShiftedKeylineList(prevStep, defaultKeylines.getLastNonAnchorIndex(), dstIndex, carouselMainAxisSize);
                steps.add(keylines);
            }
            return steps;
        }

        private final KeylineList moveKeylineAndCreateShiftedKeylineList(final KeylineList from, final int srcIndex, final int dstIndex, float carouselMainAxisSize) {
            int pivotDir = srcIndex > dstIndex ? 1 : -1;
            float pivotDelta = from.get(srcIndex).getSize() * pivotDir;
            int newPivotIndex = from.getPivotIndex() + pivotDir;
            float newPivotOffset = from.getPivot().getOffset() + pivotDelta;
            return KeylineKt.keylineListOf(carouselMainAxisSize, newPivotIndex, newPivotOffset, new Function1<KeylineListScope, Unit>() { // from class: androidx.compose.material3.carousel.Strategy$Companion$moveKeylineAndCreateShiftedKeylineList$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(KeylineListScope keylineListScope) {
                    invoke2(keylineListScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(KeylineListScope $this$keylineListOf) {
                    List $this$fastForEach$iv = Strategy.INSTANCE.move(CollectionsKt.toMutableList((Collection) from), srcIndex, dstIndex);
                    int size = $this$fastForEach$iv.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = $this$fastForEach$iv.get(index$iv);
                        Keyline k = (Keyline) item$iv;
                        $this$keylineListOf.add(k.getSize(), k.isAnchor());
                    }
                }
            });
        }

        private final FloatList getStepInterpolationPoints(float totalShiftDistance, List<KeylineList> steps, boolean isShiftingLeft) {
            float distanceShifted;
            MutableFloatList points = FloatListKt.mutableFloatListOf(0.0f);
            if (totalShiftDistance == 0.0f) {
                return points;
            }
            Iterable $this$map$iv = RangesKt.until(1, steps.size());
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            Iterator<Integer> it = $this$map$iv.iterator();
            while (it.hasNext()) {
                int item$iv$iv = ((IntIterator) it).nextInt();
                KeylineList prevKeylines = steps.get(item$iv$iv - 1);
                KeylineList currKeylines = steps.get(item$iv$iv);
                if (isShiftingLeft) {
                    distanceShifted = ((Keyline) CollectionsKt.first((List) currKeylines)).getUnadjustedOffset() - ((Keyline) CollectionsKt.first((List) prevKeylines)).getUnadjustedOffset();
                } else {
                    distanceShifted = ((Keyline) CollectionsKt.last((List) prevKeylines)).getUnadjustedOffset() - ((Keyline) CollectionsKt.last((List) currKeylines)).getUnadjustedOffset();
                }
                float stepPercentage = distanceShifted / totalShiftDistance;
                float point = item$iv$iv == CollectionsKt.getLastIndex(steps) ? 1.0f : points.get(item$iv$iv - 1) + stepPercentage;
                destination$iv$iv.add(Boolean.valueOf(points.add(point)));
            }
            return points;
        }

        /* JADX INFO: compiled from: Strategy.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/carousel/Strategy$Companion$ShiftPointRange;", "", "fromStepIndex", "", "toStepIndex", "steppedInterpolation", "", "(IIF)V", "getFromStepIndex", "()I", "getSteppedInterpolation", "()F", "getToStepIndex", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        private static final /* data */ class ShiftPointRange {
            private final int fromStepIndex;
            private final float steppedInterpolation;
            private final int toStepIndex;

            public static /* synthetic */ ShiftPointRange copy$default(ShiftPointRange shiftPointRange, int i, int i2, float f, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i = shiftPointRange.fromStepIndex;
                }
                if ((i3 & 2) != 0) {
                    i2 = shiftPointRange.toStepIndex;
                }
                if ((i3 & 4) != 0) {
                    f = shiftPointRange.steppedInterpolation;
                }
                return shiftPointRange.copy(i, i2, f);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getFromStepIndex() {
                return this.fromStepIndex;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final int getToStepIndex() {
                return this.toStepIndex;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final float getSteppedInterpolation() {
                return this.steppedInterpolation;
            }

            public final ShiftPointRange copy(int fromStepIndex, int toStepIndex, float steppedInterpolation) {
                return new ShiftPointRange(fromStepIndex, toStepIndex, steppedInterpolation);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ShiftPointRange)) {
                    return false;
                }
                ShiftPointRange shiftPointRange = (ShiftPointRange) other;
                return this.fromStepIndex == shiftPointRange.fromStepIndex && this.toStepIndex == shiftPointRange.toStepIndex && Float.compare(this.steppedInterpolation, shiftPointRange.steppedInterpolation) == 0;
            }

            public int hashCode() {
                return (((Integer.hashCode(this.fromStepIndex) * 31) + Integer.hashCode(this.toStepIndex)) * 31) + Float.hashCode(this.steppedInterpolation);
            }

            public String toString() {
                return "ShiftPointRange(fromStepIndex=" + this.fromStepIndex + ", toStepIndex=" + this.toStepIndex + ", steppedInterpolation=" + this.steppedInterpolation + ')';
            }

            public ShiftPointRange(int fromStepIndex, int toStepIndex, float steppedInterpolation) {
                this.fromStepIndex = fromStepIndex;
                this.toStepIndex = toStepIndex;
                this.steppedInterpolation = steppedInterpolation;
            }

            public final int getFromStepIndex() {
                return this.fromStepIndex;
            }

            public final int getToStepIndex() {
                return this.toStepIndex;
            }

            public final float getSteppedInterpolation() {
                return this.steppedInterpolation;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ShiftPointRange getShiftPointRange(int stepsCount, FloatList shiftPoint, float interpolation) {
            float lowerBounds = shiftPoint.get(0);
            Iterable $this$forEach$iv = RangesKt.until(1, stepsCount);
            Iterator<Integer> it = $this$forEach$iv.iterator();
            while (it.hasNext()) {
                int element$iv = ((IntIterator) it).nextInt();
                float upperBounds = shiftPoint.get(element$iv);
                if (interpolation <= upperBounds) {
                    return new ShiftPointRange(element$iv - 1, element$iv, StrategyKt.lerp(0.0f, 1.0f, lowerBounds, upperBounds, interpolation));
                }
                lowerBounds = upperBounds;
            }
            return new ShiftPointRange(0, 0, 0.0f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<Keyline> move(List<Keyline> list, int srcIndex, int dstIndex) {
            Keyline keyline = list.get(srcIndex);
            list.remove(srcIndex);
            list.add(dstIndex, keyline);
            return list;
        }
    }
}
