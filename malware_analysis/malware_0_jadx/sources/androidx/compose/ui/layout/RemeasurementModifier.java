package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RemeasurementModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/RemeasurementModifier;", "Landroidx/compose/ui/Modifier$Element;", "onRemeasurementAvailable", "", "remeasurement", "Landroidx/compose/ui/layout/Remeasurement;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface RemeasurementModifier extends Modifier.Element {
    void onRemeasurementAvailable(Remeasurement remeasurement);

    /* JADX INFO: compiled from: RemeasurementModifier.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean all(RemeasurementModifier $this, Function1<? super Modifier.Element, Boolean> function1) {
            return RemeasurementModifier.super.all(function1);
        }

        @Deprecated
        public static boolean any(RemeasurementModifier $this, Function1<? super Modifier.Element, Boolean> function1) {
            return RemeasurementModifier.super.any(function1);
        }

        @Deprecated
        public static <R> R foldIn(RemeasurementModifier remeasurementModifier, R r, Function2<? super R, ? super Modifier.Element, ? extends R> function2) {
            return (R) RemeasurementModifier.super.foldIn(r, function2);
        }

        @Deprecated
        public static <R> R foldOut(RemeasurementModifier remeasurementModifier, R r, Function2<? super Modifier.Element, ? super R, ? extends R> function2) {
            return (R) RemeasurementModifier.super.foldOut(r, function2);
        }

        @Deprecated
        public static Modifier then(RemeasurementModifier $this, Modifier other) {
            return RemeasurementModifier.super.then(other);
        }
    }
}
