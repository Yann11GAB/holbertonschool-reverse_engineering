package androidx.compose.foundation.pager;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PagerState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.pager.PagerState$animateScrollToPage$3$1", f = "PagerState.kt", i = {}, l = {552}, m = "invokeSuspend", n = {}, s = {})
final class PagerState$animateScrollToPage$3$1 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AnimationSpec<Float> $animationSpec;
    final /* synthetic */ int $targetPage;
    final /* synthetic */ int $targetPageOffsetToSnappedPosition;
    final /* synthetic */ LazyLayoutAnimateScrollScope $this_with;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PagerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PagerState$animateScrollToPage$3$1(PagerState pagerState, int i, LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i2, AnimationSpec<Float> animationSpec, Continuation<? super PagerState$animateScrollToPage$3$1> continuation) {
        super(2, continuation);
        this.this$0 = pagerState;
        this.$targetPage = i;
        this.$this_with = lazyLayoutAnimateScrollScope;
        this.$targetPageOffsetToSnappedPosition = i2;
        this.$animationSpec = animationSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PagerState$animateScrollToPage$3$1 pagerState$animateScrollToPage$3$1 = new PagerState$animateScrollToPage$3$1(this.this$0, this.$targetPage, this.$this_with, this.$targetPageOffsetToSnappedPosition, this.$animationSpec, continuation);
        pagerState$animateScrollToPage$3$1.L$0 = obj;
        return pagerState$animateScrollToPage$3$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
        return ((PagerState$animateScrollToPage$3$1) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int preJumpPosition;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final ScrollScope $this$scroll = (ScrollScope) this.L$0;
                this.this$0.updateTargetPage($this$scroll, this.$targetPage);
                boolean forward = this.$targetPage > this.$this_with.getFirstVisibleItemIndex();
                int visiblePages = (this.$this_with.getLastVisibleItemIndex() - this.$this_with.getFirstVisibleItemIndex()) + 1;
                if (((forward && this.$targetPage > this.$this_with.getLastVisibleItemIndex()) || (!forward && this.$targetPage < this.$this_with.getFirstVisibleItemIndex())) && Math.abs(this.$targetPage - this.$this_with.getFirstVisibleItemIndex()) >= 3) {
                    if (forward) {
                        preJumpPosition = RangesKt.coerceAtLeast(this.$targetPage - visiblePages, this.$this_with.getFirstVisibleItemIndex());
                    } else {
                        preJumpPosition = RangesKt.coerceAtMost(this.$targetPage + visiblePages, this.$this_with.getFirstVisibleItemIndex());
                    }
                    this.$this_with.snapToItem($this$scroll, preJumpPosition, 0);
                }
                int pageAvailableSpace = this.$this_with.getVisibleItemsAverageSize();
                int currentPosition = this.this$0.getCurrentPage();
                int targetOffset = this.$targetPage * pageAvailableSpace;
                int currentOffset = currentPosition * pageAvailableSpace;
                float currentOffsetFraction = pageAvailableSpace * this.this$0.getCurrentPageOffsetFraction();
                float displacement = ((targetOffset - currentOffset) + this.$targetPageOffsetToSnappedPosition) - currentOffsetFraction;
                final Ref.FloatRef previousValue = new Ref.FloatRef();
                this.label = 1;
                if (SuspendAnimationKt.animate$default(0.0f, displacement, 0.0f, this.$animationSpec, new Function2<Float, Float, Unit>() { // from class: androidx.compose.foundation.pager.PagerState$animateScrollToPage$3$1.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Float f, Float f2) {
                        invoke(f.floatValue(), f2.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float currentValue, float f) {
                        float delta = currentValue - previousValue.element;
                        float consumed = $this$scroll.scrollBy(delta);
                        previousValue.element += consumed;
                    }
                }, this, 4, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
