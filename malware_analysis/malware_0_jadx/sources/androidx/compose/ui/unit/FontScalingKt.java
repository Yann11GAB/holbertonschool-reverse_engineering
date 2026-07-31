package androidx.compose.ui.unit;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: compiled from: FontScaling.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\"1\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0000\u001a\u00020\u00018G@GX\u0087\u008e\u0002¢\u0006\u0018\n\u0004\b\t\u0010\n\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"<set-?>", "", "DisableNonLinearFontScalingInCompose", "getDisableNonLinearFontScalingInCompose$annotations", "()V", "getDisableNonLinearFontScalingInCompose", "()Z", "setDisableNonLinearFontScalingInCompose", "(Z)V", "DisableNonLinearFontScalingInCompose$delegate", "Landroidx/compose/runtime/MutableState;", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontScalingKt {
    private static final MutableState DisableNonLinearFontScalingInCompose$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    public static /* synthetic */ void getDisableNonLinearFontScalingInCompose$annotations() {
    }

    public static final boolean getDisableNonLinearFontScalingInCompose() {
        State $this$getValue$iv = DisableNonLinearFontScalingInCompose$delegate;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public static final void setDisableNonLinearFontScalingInCompose(boolean z) {
        MutableState $this$setValue$iv = DisableNonLinearFontScalingInCompose$delegate;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }
}
