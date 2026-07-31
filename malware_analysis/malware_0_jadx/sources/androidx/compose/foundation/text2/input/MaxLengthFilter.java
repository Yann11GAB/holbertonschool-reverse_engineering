package androidx.compose.foundation.text2.input;

import kotlin.Metadata;

/* JADX INFO: compiled from: MaxLengthTransformation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÂ\u0003J\t\u0010\b\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/text2/input/MaxLengthFilter;", "Landroidx/compose/foundation/text2/input/InputTransformation;", "maxLength", "", "inCodepoints", "", "(IZ)V", "component1", "component2", "copy", "equals", "other", "", "hashCode", "toString", "", "transformInput", "", "originalValue", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "valueWithChanges", "Landroidx/compose/foundation/text2/input/TextFieldBuffer;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class MaxLengthFilter implements InputTransformation {
    private final boolean inCodepoints;
    private final int maxLength;

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final int getMaxLength() {
        return this.maxLength;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final boolean getInCodepoints() {
        return this.inCodepoints;
    }

    public static /* synthetic */ MaxLengthFilter copy$default(MaxLengthFilter maxLengthFilter, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = maxLengthFilter.maxLength;
        }
        if ((i2 & 2) != 0) {
            z = maxLengthFilter.inCodepoints;
        }
        return maxLengthFilter.copy(i, z);
    }

    public final MaxLengthFilter copy(int maxLength, boolean inCodepoints) {
        return new MaxLengthFilter(maxLength, inCodepoints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MaxLengthFilter)) {
            return false;
        }
        MaxLengthFilter maxLengthFilter = (MaxLengthFilter) other;
        return this.maxLength == maxLengthFilter.maxLength && this.inCodepoints == maxLengthFilter.inCodepoints;
    }

    public int hashCode() {
        return (Integer.hashCode(this.maxLength) * 31) + Boolean.hashCode(this.inCodepoints);
    }

    public MaxLengthFilter(int maxLength, boolean inCodepoints) {
        this.maxLength = maxLength;
        this.inCodepoints = inCodepoints;
        if (this.maxLength >= 0) {
        } else {
            throw new IllegalArgumentException(("maxLength must be at least zero, was " + this.maxLength).toString());
        }
    }

    @Override // androidx.compose.foundation.text2.input.InputTransformation
    public void transformInput(TextFieldCharSequence originalValue, TextFieldBuffer valueWithChanges) {
        int newLength = this.inCodepoints ? valueWithChanges.getCodepointLength() : valueWithChanges.getLength();
        if (newLength > this.maxLength) {
            valueWithChanges.revertAllChanges();
        }
    }

    public String toString() {
        String name = this.inCodepoints ? "maxLengthInCodepoints" : "maxLengthInChars";
        return "InputTransformation." + name + "(maxLength=" + this.maxLength + ')';
    }
}
