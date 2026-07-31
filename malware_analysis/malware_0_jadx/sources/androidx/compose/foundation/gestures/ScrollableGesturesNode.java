package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ \u0010-\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aRF\u0010\u001b\u001a8\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"\u0012\u0006\u0012\u0004\u0018\u00010$0\u001c¢\u0006\u0002\b%X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00070,X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableGesturesNode;", "Landroidx/compose/ui/node/DelegatingNode;", "scrollLogic", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "", "nestedScrollDispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/gestures/ScrollingLogic;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "draggableGesturesNode", "Landroidx/compose/foundation/gestures/DraggableNode;", "getDraggableGesturesNode", "()Landroidx/compose/foundation/gestures/DraggableNode;", "draggableState", "Landroidx/compose/foundation/gestures/ScrollDraggableState;", "getDraggableState", "()Landroidx/compose/foundation/gestures/ScrollDraggableState;", "getEnabled", "()Z", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getNestedScrollDispatcher", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "onDragStopped", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/compose/ui/unit/Velocity;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "velocity", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "getScrollLogic", "()Landroidx/compose/foundation/gestures/ScrollingLogic;", "startDragImmediately", "Lkotlin/Function0;", "update", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ScrollableGesturesNode extends DelegatingNode {
    private final DraggableNode draggableGesturesNode;
    private final ScrollDraggableState draggableState;
    private final boolean enabled;
    private final MutableInteractionSource interactionSource;
    private final NestedScrollDispatcher nestedScrollDispatcher;
    private final Function3<CoroutineScope, Velocity, Continuation<? super Unit>, Object> onDragStopped;
    private final Orientation orientation;
    private final ScrollingLogic scrollLogic;
    private final Function0<Boolean> startDragImmediately;

    public final ScrollingLogic getScrollLogic() {
        return this.scrollLogic;
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final NestedScrollDispatcher getNestedScrollDispatcher() {
        return this.nestedScrollDispatcher;
    }

    public final MutableInteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    public ScrollableGesturesNode(ScrollingLogic scrollLogic, Orientation orientation, boolean enabled, NestedScrollDispatcher nestedScrollDispatcher, MutableInteractionSource interactionSource) {
        this.scrollLogic = scrollLogic;
        this.orientation = orientation;
        this.enabled = enabled;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        this.interactionSource = interactionSource;
        delegate(new MouseWheelScrollNode(this.scrollLogic));
        this.draggableState = new ScrollDraggableState(this.scrollLogic);
        this.startDragImmediately = new Function0<Boolean>() { // from class: androidx.compose.foundation.gestures.ScrollableGesturesNode$startDragImmediately$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(this.this$0.getScrollLogic().shouldScrollImmediately());
            }
        };
        this.onDragStopped = new ScrollableGesturesNode$onDragStopped$1(this, null);
        ScrollDraggableState scrollDraggableState = this.draggableState;
        ScrollDraggableState scrollDraggableState2 = scrollDraggableState;
        this.draggableGesturesNode = (DraggableNode) delegate(new DraggableNode(scrollDraggableState2, ScrollableKt.CanDragCalculation, this.orientation, this.enabled, this.interactionSource, this.startDragImmediately, ScrollableKt.NoOpOnDragStarted, this.onDragStopped, false));
    }

    public final ScrollDraggableState getDraggableState() {
        return this.draggableState;
    }

    public final DraggableNode getDraggableGesturesNode() {
        return this.draggableGesturesNode;
    }

    public final void update(Orientation orientation, boolean enabled, MutableInteractionSource interactionSource) {
        DraggableNode draggableNode = this.draggableGesturesNode;
        ScrollDraggableState scrollDraggableState = this.draggableState;
        ScrollDraggableState scrollDraggableState2 = scrollDraggableState;
        draggableNode.update(scrollDraggableState2, ScrollableKt.CanDragCalculation, orientation, enabled, interactionSource, this.startDragImmediately, ScrollableKt.NoOpOnDragStarted, this.onDragStopped, false);
    }
}
