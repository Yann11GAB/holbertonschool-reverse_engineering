package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: compiled from: ActualAndroid.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002H\u0001¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"defaultLocale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "(Landroidx/compose/runtime/Composer;I)Ljava/util/Locale;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ActualAndroid_androidKt {
    public static final Locale defaultLocale(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 661006346, "C(defaultLocale):ActualAndroid.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(661006346, $changed, -1, "androidx.compose.material3.defaultLocale (ActualAndroid.android.kt:32)");
        }
        $composer.startReplaceableGroup(-273864580);
        ComposerKt.sourceInformation($composer, "34@1191L15");
        Locale localeDefaultLocale = Locale24.INSTANCE.defaultLocale($composer, 6);
        $composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return localeDefaultLocale;
    }
}
