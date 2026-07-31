package androidx.compose.ui.semantics;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: SemanticsOwner.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a,\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\b*\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000¨\u0006\u000b"}, d2 = {"getAllSemanticsNodes", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "Landroidx/compose/ui/semantics/SemanticsOwner;", "mergingEnabled", "", "skipDeactivatedNodes", "getAllSemanticsNodesToMap", "", "", "useUnmergedTree", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SemanticsOwnerKt {
    public static /* synthetic */ List getAllSemanticsNodes$default(SemanticsOwner semanticsOwner, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        return getAllSemanticsNodes(semanticsOwner, z, z2);
    }

    public static final List<SemanticsNode> getAllSemanticsNodes(SemanticsOwner $this$getAllSemanticsNodes, boolean mergingEnabled, boolean skipDeactivatedNodes) {
        return CollectionsKt.toList(getAllSemanticsNodesToMap($this$getAllSemanticsNodes, !mergingEnabled, skipDeactivatedNodes).values());
    }

    public static /* synthetic */ Map getAllSemanticsNodesToMap$default(SemanticsOwner semanticsOwner, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return getAllSemanticsNodesToMap(semanticsOwner, z, z2);
    }

    public static final Map<Integer, SemanticsNode> getAllSemanticsNodesToMap(SemanticsOwner $this$getAllSemanticsNodesToMap, boolean useUnmergedTree, boolean skipDeactivatedNodes) {
        Map nodes = new LinkedHashMap();
        SemanticsNode root = useUnmergedTree ? $this$getAllSemanticsNodesToMap.getUnmergedRootSemanticsNode() : $this$getAllSemanticsNodesToMap.getRootSemanticsNode();
        getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(skipDeactivatedNodes, nodes, root);
        return nodes;
    }

    private static final void getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(boolean $skipDeactivatedNodes, Map<Integer, SemanticsNode> map, SemanticsNode currentNode) {
        if (!$skipDeactivatedNodes || !currentNode.getLayoutInfo().getIsDeactivated()) {
            map.put(Integer.valueOf(currentNode.getId()), currentNode);
            List<SemanticsNode> children = currentNode.getChildren();
            int size = children.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = children.get(index$iv);
                SemanticsNode child = (SemanticsNode) item$iv;
                getAllSemanticsNodesToMap$findAllSemanticNodesRecursive($skipDeactivatedNodes, map, child);
            }
        }
    }
}
