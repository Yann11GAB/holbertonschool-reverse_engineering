package androidx.compose.ui.text.android;

import android.text.Layout;
import kotlin.Metadata;

/* JADX INFO: compiled from: LayoutCompat.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"getLineForOffset", "", "Landroid/text/Layout;", "offset", "upstream", "", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LayoutCompatKt {
    public static final int getLineForOffset(Layout $this$getLineForOffset, int offset, boolean upstream) {
        if (offset <= 0) {
            return 0;
        }
        if (offset >= $this$getLineForOffset.getText().length()) {
            return $this$getLineForOffset.getLineCount() - 1;
        }
        int downstreamLineNo = $this$getLineForOffset.getLineForOffset(offset);
        int lineStart = $this$getLineForOffset.getLineStart(downstreamLineNo);
        int lineEnd = $this$getLineForOffset.getLineEnd(downstreamLineNo);
        if (lineStart != offset && lineEnd != offset) {
            return downstreamLineNo;
        }
        if (lineStart == offset) {
            if (upstream) {
                return downstreamLineNo - 1;
            }
        } else if (!upstream) {
            return downstreamLineNo + 1;
        }
        return downstreamLineNo;
    }
}
