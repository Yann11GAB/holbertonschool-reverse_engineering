package androidx.compose.material3.carousel;

import androidx.compose.ui.util.MathHelpersKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: Strategy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a \u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a0\u0010\u0000\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002¨\u0006\u000e"}, d2 = {"lerp", "Landroidx/compose/material3/carousel/Keyline;", "start", "end", "fraction", "", "Landroidx/compose/material3/carousel/KeylineList;", "from", "to", "outputMin", "outputMax", "inputMin", "inputMax", "value", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StrategyKt {
    public static final Keyline lerp(Keyline start, Keyline end, float fraction) {
        return new Keyline(MathHelpersKt.lerp(start.getSize(), end.getSize(), fraction), MathHelpersKt.lerp(start.getOffset(), end.getOffset(), fraction), MathHelpersKt.lerp(start.getUnadjustedOffset(), end.getUnadjustedOffset(), fraction), fraction < 0.5f ? start.isFocal() : end.isFocal(), fraction < 0.5f ? start.isAnchor() : end.isAnchor(), fraction < 0.5f ? start.isPivot() : end.isPivot(), MathHelpersKt.lerp(start.getCutoff(), end.getCutoff(), fraction));
    }

    public static final KeylineList lerp(KeylineList from, KeylineList to, float fraction) {
        KeylineList $this$fastMapIndexed$iv = from;
        int $i$f$fastMapIndexed = 0;
        List target$iv = new ArrayList($this$fastMapIndexed$iv.size());
        int index$iv$iv = 0;
        int size = $this$fastMapIndexed$iv.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = $this$fastMapIndexed$iv.get(index$iv$iv);
            int index$iv = index$iv$iv;
            Keyline k = (Keyline) item$iv$iv;
            target$iv.add(lerp(k, to.get(index$iv), fraction));
            index$iv$iv++;
            $this$fastMapIndexed$iv = $this$fastMapIndexed$iv;
            $i$f$fastMapIndexed = $i$f$fastMapIndexed;
        }
        List interpolatedKeylines = target$iv;
        return new KeylineList(interpolatedKeylines);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float lerp(float outputMin, float outputMax, float inputMin, float inputMax, float value) {
        if (value <= inputMin) {
            return outputMin;
        }
        if (value >= inputMax) {
            return outputMax;
        }
        return MathHelpersKt.lerp(outputMin, outputMax, (value - inputMin) / (inputMax - inputMin));
    }
}
