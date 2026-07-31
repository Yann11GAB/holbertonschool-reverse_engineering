package androidx.compose.ui.autofill;

import android.util.Log;
import android.util.SparseArray;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import androidx.compose.ui.geometry.Rect;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AndroidAutofill.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0001\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0001¨\u0006\t"}, d2 = {"performAutofill", "", "Landroidx/compose/ui/autofill/AndroidAutofill;", "values", "Landroid/util/SparseArray;", "Landroid/view/autofill/AutofillValue;", "populateViewStructure", "root", "Landroid/view/ViewStructure;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidAutofill_androidKt {
    public static final void populateViewStructure(AndroidAutofill $this$populateViewStructure, ViewStructure root) {
        Iterator<Map.Entry<Integer, AutofillNode>> it;
        ViewStructure viewStructure = root;
        int index = AutofillApi23Helper.INSTANCE.addChildCount(viewStructure, $this$populateViewStructure.getAutofillTree().getChildren().size());
        Iterator<Map.Entry<Integer, AutofillNode>> it2 = $this$populateViewStructure.getAutofillTree().getChildren().entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<Integer, AutofillNode> next = it2.next();
            int id = next.getKey().intValue();
            AutofillNode autofillNode = next.getValue();
            ViewStructure child = AutofillApi23Helper.INSTANCE.newChild(viewStructure, index);
            if (child == null) {
                it = it2;
            } else {
                AutofillApi26Helper autofillApi26Helper = AutofillApi26Helper.INSTANCE;
                AutofillId autofillId = AutofillApi26Helper.INSTANCE.getAutofillId(viewStructure);
                Intrinsics.checkNotNull(autofillId);
                autofillApi26Helper.setAutofillId(child, autofillId, id);
                AutofillApi23Helper.INSTANCE.setId(child, id, $this$populateViewStructure.getView().getContext().getPackageName(), null, null);
                AutofillApi26Helper.INSTANCE.setAutofillType(child, 1);
                AutofillApi26Helper autofillApi26Helper2 = AutofillApi26Helper.INSTANCE;
                List<AutofillType> autofillTypes = autofillNode.getAutofillTypes();
                Collection target$iv = new ArrayList(autofillTypes.size());
                int index$iv$iv = 0;
                int size = autofillTypes.size();
                while (index$iv$iv < size) {
                    Object item$iv$iv = autofillTypes.get(index$iv$iv);
                    AutofillType it3 = (AutofillType) item$iv$iv;
                    target$iv.add(AndroidAutofillType_androidKt.getAndroidType(it3));
                    index$iv$iv++;
                    it2 = it2;
                }
                it = it2;
                Collection $this$toTypedArray$iv = (List) target$iv;
                autofillApi26Helper2.setAutofillHints(child, (String[]) $this$toTypedArray$iv.toArray(new String[0]));
                Rect boundingBox = autofillNode.getBoundingBox();
                if (boundingBox == null) {
                    Log.w("Autofill Warning", "Bounding box not set.\n                        Did you call perform autofillTree before the component was positioned? ");
                } else {
                    int left = MathKt.roundToInt(boundingBox.getLeft());
                    int top = MathKt.roundToInt(boundingBox.getTop());
                    int right = MathKt.roundToInt(boundingBox.getRight());
                    int bottom = MathKt.roundToInt(boundingBox.getBottom());
                    int width = right - left;
                    int height = bottom - top;
                    AutofillApi23Helper.INSTANCE.setDimens(child, left, top, 0, 0, width, height);
                }
            }
            index++;
            viewStructure = root;
            it2 = it;
        }
    }

    public static final void performAutofill(AndroidAutofill $this$performAutofill, SparseArray<AutofillValue> sparseArray) {
        int size = sparseArray.size();
        for (int index = 0; index < size; index++) {
            int itemId = sparseArray.keyAt(index);
            AutofillValue value = sparseArray.get(itemId);
            if (AutofillApi26Helper.INSTANCE.isText(value)) {
                $this$performAutofill.getAutofillTree().performAutofill(itemId, AutofillApi26Helper.INSTANCE.textValue(value).toString());
            } else {
                if (AutofillApi26Helper.INSTANCE.isDate(value)) {
                    throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for date");
                }
                if (AutofillApi26Helper.INSTANCE.isList(value)) {
                    throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for list");
                }
                if (AutofillApi26Helper.INSTANCE.isToggle(value)) {
                    throw new NotImplementedError("An operation is not implemented: b/138604541:  Add onFill() callback for toggle");
                }
            }
        }
    }
}
