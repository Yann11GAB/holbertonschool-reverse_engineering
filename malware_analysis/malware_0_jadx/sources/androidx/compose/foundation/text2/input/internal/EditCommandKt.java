package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text.StringHelpers_androidKt;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: EditCommand.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\u001c\u0010\b\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\f\u0010\r\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\u001c\u0010\u000e\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0000\u001a\u001c\u0010\u0011\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0000\u001a\f\u0010\u0012\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\u0014\u0010\u0013\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\fH\u0000\u001a\u001c\u0010\u0015\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fH\u0000\u001a\u001c\u0010\u0018\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000¨\u0006\u0019"}, d2 = {"isSurrogatePair", "", "high", "", "low", "backspace", "", "Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "commitText", "text", "", "newCursorPosition", "", "deleteAll", "deleteSurroundingText", "lengthBeforeCursor", "lengthAfterCursor", "deleteSurroundingTextInCodePoints", "finishComposingText", "moveCursor", "amount", "setComposingRegion", "start", "end", "setComposingText", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditCommandKt {
    public static final void commitText(EditingBuffer $this$commitText, String text, int newCursorPosition) {
        int newCursorInBuffer;
        if ($this$commitText.hasComposition()) {
            $this$commitText.replace($this$commitText.getCompositionStart(), $this$commitText.getCompositionEnd(), text);
        } else {
            $this$commitText.replace($this$commitText.getSelectionStart(), $this$commitText.getSelectionEnd(), text);
        }
        int newCursor = $this$commitText.getCursor();
        if (newCursorPosition > 0) {
            newCursorInBuffer = (newCursor + newCursorPosition) - 1;
        } else {
            newCursorInBuffer = (newCursor + newCursorPosition) - text.length();
        }
        $this$commitText.setCursor(RangesKt.coerceIn(newCursorInBuffer, 0, $this$commitText.getLength()));
    }

    public static final void setComposingRegion(EditingBuffer $this$setComposingRegion, int start, int end) {
        if ($this$setComposingRegion.hasComposition()) {
            $this$setComposingRegion.commitComposition();
        }
        int clampedStart = RangesKt.coerceIn(start, 0, $this$setComposingRegion.getLength());
        int clampedEnd = RangesKt.coerceIn(end, 0, $this$setComposingRegion.getLength());
        if (clampedStart != clampedEnd) {
            if (clampedStart < clampedEnd) {
                $this$setComposingRegion.setComposition(clampedStart, clampedEnd);
            } else {
                $this$setComposingRegion.setComposition(clampedEnd, clampedStart);
            }
        }
    }

    public static final void setComposingText(EditingBuffer $this$setComposingText, String text, int newCursorPosition) {
        int length;
        if ($this$setComposingText.hasComposition()) {
            int compositionStart = $this$setComposingText.getCompositionStart();
            $this$setComposingText.replace(compositionStart, $this$setComposingText.getCompositionEnd(), text);
            if (text.length() > 0) {
                $this$setComposingText.setComposition(compositionStart, text.length() + compositionStart);
            }
        } else {
            int selectionStart = $this$setComposingText.getSelectionStart();
            $this$setComposingText.replace(selectionStart, $this$setComposingText.getSelectionEnd(), text);
            if (text.length() > 0) {
                $this$setComposingText.setComposition(selectionStart, text.length() + selectionStart);
            }
        }
        int newCursor = $this$setComposingText.getCursor();
        if (newCursorPosition > 0) {
            length = (newCursor + newCursorPosition) - 1;
        } else {
            length = (newCursor + newCursorPosition) - text.length();
        }
        int newCursorInBuffer = length;
        $this$setComposingText.setCursor(RangesKt.coerceIn(newCursorInBuffer, 0, $this$setComposingText.getLength()));
    }

    public static final void deleteSurroundingText(EditingBuffer $this$deleteSurroundingText, int lengthBeforeCursor, int lengthAfterCursor) {
        if (!(lengthBeforeCursor >= 0 && lengthAfterCursor >= 0)) {
            throw new IllegalArgumentException(("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + lengthBeforeCursor + " and " + lengthAfterCursor + " respectively.").toString());
        }
        int $this$addExactOrElse$iv = $this$deleteSurroundingText.getSelectionEnd();
        int result$iv = $this$addExactOrElse$iv + lengthAfterCursor;
        if ((($this$addExactOrElse$iv ^ result$iv) & (lengthAfterCursor ^ result$iv)) < 0) {
            result$iv = $this$deleteSurroundingText.getLength();
        }
        $this$deleteSurroundingText.delete($this$deleteSurroundingText.getSelectionEnd(), Math.min(result$iv, $this$deleteSurroundingText.getLength()));
        int $this$subtractExactOrElse$iv = $this$deleteSurroundingText.getSelectionStart();
        int result$iv2 = $this$subtractExactOrElse$iv - lengthBeforeCursor;
        if ((($this$subtractExactOrElse$iv ^ lengthBeforeCursor) & ($this$subtractExactOrElse$iv ^ result$iv2)) < 0) {
            result$iv2 = 0;
        }
        $this$deleteSurroundingText.delete(Math.max(0, result$iv2), $this$deleteSurroundingText.getSelectionStart());
    }

    public static final void deleteSurroundingTextInCodePoints(EditingBuffer $this$deleteSurroundingTextInCodePoints, int lengthBeforeCursor, int lengthAfterCursor) {
        if (!(lengthBeforeCursor >= 0 && lengthAfterCursor >= 0)) {
            throw new IllegalArgumentException(("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + lengthBeforeCursor + " and " + lengthAfterCursor + " respectively.").toString());
        }
        int beforeLenInChars = 0;
        for (int i = 0; i < lengthBeforeCursor; i++) {
            beforeLenInChars++;
            if ($this$deleteSurroundingTextInCodePoints.getSelectionStart() > beforeLenInChars) {
                char lead = $this$deleteSurroundingTextInCodePoints.get(($this$deleteSurroundingTextInCodePoints.getSelectionStart() - beforeLenInChars) - 1);
                char trail = $this$deleteSurroundingTextInCodePoints.get($this$deleteSurroundingTextInCodePoints.getSelectionStart() - beforeLenInChars);
                if (isSurrogatePair(lead, trail)) {
                    beforeLenInChars++;
                }
            }
            if (beforeLenInChars == $this$deleteSurroundingTextInCodePoints.getSelectionStart()) {
                break;
            }
        }
        int afterLenInChars = 0;
        for (int i2 = 0; i2 < lengthAfterCursor; i2++) {
            afterLenInChars++;
            if ($this$deleteSurroundingTextInCodePoints.getSelectionEnd() + afterLenInChars < $this$deleteSurroundingTextInCodePoints.getLength()) {
                char lead2 = $this$deleteSurroundingTextInCodePoints.get(($this$deleteSurroundingTextInCodePoints.getSelectionEnd() + afterLenInChars) - 1);
                char trail2 = $this$deleteSurroundingTextInCodePoints.get($this$deleteSurroundingTextInCodePoints.getSelectionEnd() + afterLenInChars);
                if (isSurrogatePair(lead2, trail2)) {
                    afterLenInChars++;
                }
            }
            if ($this$deleteSurroundingTextInCodePoints.getSelectionEnd() + afterLenInChars == $this$deleteSurroundingTextInCodePoints.getLength()) {
                break;
            }
        }
        $this$deleteSurroundingTextInCodePoints.delete($this$deleteSurroundingTextInCodePoints.getSelectionEnd(), $this$deleteSurroundingTextInCodePoints.getSelectionEnd() + afterLenInChars);
        $this$deleteSurroundingTextInCodePoints.delete($this$deleteSurroundingTextInCodePoints.getSelectionStart() - beforeLenInChars, $this$deleteSurroundingTextInCodePoints.getSelectionStart());
    }

    public static final void finishComposingText(EditingBuffer $this$finishComposingText) {
        $this$finishComposingText.commitComposition();
    }

    public static final void backspace(EditingBuffer $this$backspace) {
        if ($this$backspace.hasComposition()) {
            $this$backspace.delete($this$backspace.getCompositionStart(), $this$backspace.getCompositionEnd());
            return;
        }
        if ($this$backspace.getCursor() == -1) {
            int delStart = $this$backspace.getSelectionStart();
            int delEnd = $this$backspace.getSelectionEnd();
            $this$backspace.setCursor($this$backspace.getSelectionStart());
            $this$backspace.delete(delStart, delEnd);
            return;
        }
        if ($this$backspace.getCursor() != 0) {
            int prevCursorPos = StringHelpers_androidKt.findPrecedingBreak($this$backspace.toString(), $this$backspace.getCursor());
            $this$backspace.delete(prevCursorPos, $this$backspace.getCursor());
        }
    }

    public static final void moveCursor(EditingBuffer $this$moveCursor, int amount) {
        if ($this$moveCursor.getCursor() == -1) {
            $this$moveCursor.setCursor($this$moveCursor.getSelectionStart());
        }
        int newCursor = $this$moveCursor.getSelectionStart();
        String bufferText = $this$moveCursor.toString();
        if (amount > 0) {
            for (int i = 0; i < amount; i++) {
                int next = StringHelpers_androidKt.findFollowingBreak(bufferText, newCursor);
                if (next == -1) {
                    break;
                }
                newCursor = next;
            }
        } else {
            int i2 = -amount;
            for (int i3 = 0; i3 < i2; i3++) {
                int prev = StringHelpers_androidKt.findPrecedingBreak(bufferText, newCursor);
                if (prev == -1) {
                    break;
                }
                newCursor = prev;
            }
        }
        $this$moveCursor.setCursor(newCursor);
    }

    public static final void deleteAll(EditingBuffer $this$deleteAll) {
        $this$deleteAll.replace(0, $this$deleteAll.getLength(), "");
    }

    private static final boolean isSurrogatePair(char high, char low) {
        return Character.isHighSurrogate(high) && Character.isLowSurrogate(low);
    }
}
