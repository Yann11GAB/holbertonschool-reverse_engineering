package androidx.compose.ui.unit;

import android.content.Context;
import androidx.compose.ui.unit.fontscaling.FontScaleConverterFactory;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidDensity.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"Density", "Landroidx/compose/ui/unit/Density;", "context", "Landroid/content/Context;", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidDensity_androidKt {
    public static final Density Density(Context context) {
        LinearFontScaleConverter converter;
        float fontScale = context.getResources().getConfiguration().fontScale;
        if (FontScalingKt.getDisableNonLinearFontScalingInCompose() || (converter = FontScaleConverterFactory.INSTANCE.forScale(fontScale)) == null) {
            converter = new LinearFontScaleConverter(fontScale);
        }
        return new DensityWithConverter(context.getResources().getDisplayMetrics().density, fontScale, converter);
    }
}
