package androidx.compose.ui.tooling;

import androidx.compose.ui.layout.LayoutInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: ShadowViewInfo.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"stitchTrees", "", "Landroidx/compose/ui/tooling/ViewInfo;", "allViewInfoRoots", "ui-tooling_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShadowViewInfo_androidKt {
    public static final List<ViewInfo> stitchTrees(List<ViewInfo> list) {
        Object answer$iv$iv$iv;
        if (list.size() < 2) {
            return list;
        }
        List<ViewInfo> $this$map$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            ViewInfo it = (ViewInfo) item$iv$iv;
            destination$iv$iv.add(new ShadowViewInfo(it));
        }
        Iterable shadowTreeRoots = (List) destination$iv$iv;
        Iterable<ShadowViewInfo> $this$flatMap$iv = shadowTreeRoots;
        Collection destination$iv$iv2 = new ArrayList();
        for (ShadowViewInfo it2 : $this$flatMap$iv) {
            CollectionsKt.addAll(destination$iv$iv2, it2.getAllNodes());
        }
        Iterable $this$map$iv2 = (List) destination$iv$iv2;
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            ShadowViewInfo it3 = (ShadowViewInfo) item$iv$iv2;
            destination$iv$iv3.add(TuplesKt.to(it3.getLayoutInfo(), it3));
        }
        Iterable $this$filter$iv = (List) destination$iv$iv3;
        Collection destination$iv$iv4 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Pair it4 = (Pair) element$iv$iv;
            if (it4.getFirst() != null) {
                destination$iv$iv4.add(element$iv$iv);
            }
        }
        Iterable $this$groupBy$iv = (List) destination$iv$iv4;
        final Map destination$iv$iv5 = new LinkedHashMap();
        for (Object element$iv$iv2 : $this$groupBy$iv) {
            Pair it5 = (Pair) element$iv$iv2;
            LayoutInfo layoutInfo = (LayoutInfo) it5.getFirst();
            Object value$iv$iv$iv = destination$iv$iv5.get(layoutInfo);
            if (value$iv$iv$iv == null) {
                answer$iv$iv$iv = new ArrayList();
                destination$iv$iv5.put(layoutInfo, answer$iv$iv$iv);
            } else {
                answer$iv$iv$iv = value$iv$iv$iv;
            }
            List list$iv$iv = (List) answer$iv$iv$iv;
            list$iv$iv.add(element$iv$iv2);
        }
        LinkedHashSet currentRoots = new LinkedHashSet((Collection) shadowTreeRoots);
        Iterable $this$forEach$iv = shadowTreeRoots;
        for (Object element$iv : $this$forEach$iv) {
            final ShadowViewInfo rootToAttach = (ShadowViewInfo) element$iv;
            ShadowViewInfo nodeToAttachTo = (ShadowViewInfo) SequencesKt.firstOrNull(SequencesKt.map(SequencesKt.filter(SequencesKt.flatMapIterable(rootToAttach.getAllNodes(), new Function1<ShadowViewInfo, List<? extends Pair<? extends LayoutInfo, ? extends ShadowViewInfo>>>() { // from class: androidx.compose.ui.tooling.ShadowViewInfo_androidKt$stitchTrees$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final List<Pair<LayoutInfo, ShadowViewInfo>> invoke(ShadowViewInfo candidate) {
                    Map<LayoutInfo, List<Pair<LayoutInfo, ShadowViewInfo>>> map = destination$iv$iv5;
                    LayoutInfo layoutInfo2 = candidate.getLayoutInfo();
                    List<Pair<LayoutInfo, ShadowViewInfo>> list2 = map.get(layoutInfo2 != null ? layoutInfo2.getParentInfo() : null);
                    return list2 == null ? CollectionsKt.emptyList() : list2;
                }
            }), new Function1<Pair<? extends LayoutInfo, ? extends ShadowViewInfo>, Boolean>() { // from class: androidx.compose.ui.tooling.ShadowViewInfo_androidKt$stitchTrees$1$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Pair<? extends LayoutInfo, ? extends ShadowViewInfo> pair) {
                    return invoke2((Pair<? extends LayoutInfo, ShadowViewInfo>) pair);
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final Boolean invoke2(Pair<? extends LayoutInfo, ShadowViewInfo> pair) {
                    return Boolean.valueOf(!Intrinsics.areEqual(pair.getSecond().findRoot(), rootToAttach));
                }
            }), new Function1<Pair<? extends LayoutInfo, ? extends ShadowViewInfo>, ShadowViewInfo>() { // from class: androidx.compose.ui.tooling.ShadowViewInfo_androidKt$stitchTrees$1$3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final ShadowViewInfo invoke2(Pair<? extends LayoutInfo, ShadowViewInfo> pair) {
                    ShadowViewInfo candidateNode = pair.component2();
                    return candidateNode;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ ShadowViewInfo invoke(Pair<? extends LayoutInfo, ? extends ShadowViewInfo> pair) {
                    return invoke2((Pair<? extends LayoutInfo, ShadowViewInfo>) pair);
                }
            }));
            if (nodeToAttachTo != null) {
                rootToAttach.setNewParent(nodeToAttachTo);
                currentRoots.remove(rootToAttach);
            }
        }
        LinkedHashSet $this$map$iv3 = currentRoots;
        Collection destination$iv$iv6 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
        for (Object item$iv$iv3 : $this$map$iv3) {
            ShadowViewInfo it6 = (ShadowViewInfo) item$iv$iv3;
            destination$iv$iv6.add(it6.toViewInfo());
        }
        List newTree = (List) destination$iv$iv6;
        return newTree;
    }
}
