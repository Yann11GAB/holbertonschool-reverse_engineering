package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: SnapshotLongState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"androidx/compose/runtime/SnapshotLongStateKt__SnapshotLongStateKt"}, k = 4, mv = {1, 8, 0}, xi = 48)
public final class SnapshotLongStateKt {
    public static final long getValue(LongState $this$getValue, Object thisObj, KProperty<?> kProperty) {
        return SnapshotLongStateKt__SnapshotLongStateKt.getValue($this$getValue, thisObj, kProperty);
    }

    public static final MutableLongState mutableLongStateOf(long value) {
        return SnapshotLongStateKt__SnapshotLongStateKt.mutableLongStateOf(value);
    }

    public static final void setValue(MutableLongState $this$setValue, Object thisObj, KProperty<?> kProperty, long value) {
        SnapshotLongStateKt__SnapshotLongStateKt.setValue($this$setValue, thisObj, kProperty, value);
    }
}
