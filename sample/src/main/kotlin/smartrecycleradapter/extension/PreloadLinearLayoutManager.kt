package smartrecycleradapter.extension

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max


class PreloadLinearLayoutManager : LinearLayoutManager {
    private var mOrientationHelper: OrientationHelper? = null

    /**
     * As [LinearLayoutManager.collectAdjacentPrefetchPositions] will prefetch one view for us,
     * we only need to prefetch additional ones.
     */
    private var mAdditionalAdjacentPrefetchItemCount = 0

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    ) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        mOrientationHelper = OrientationHelper.createOrientationHelper(this, orientation)
    }

    fun setPreloadItemCount(preloadItemCount: Int) {
        require(preloadItemCount >= 1) { "adjacentPrefetchItemCount must not smaller than 1!" }
        mAdditionalAdjacentPrefetchItemCount = preloadItemCount - 1
    }

    override fun collectAdjacentPrefetchPositions(
        dx: Int,
        dy: Int,
        state: RecyclerView.State,
        layoutPrefetchRegistry: LayoutPrefetchRegistry
    ) {
        super.collectAdjacentPrefetchPositions(dx, dy, state, layoutPrefetchRegistry)
        /* We make the simple assumption that the list scrolls down to load more data,
         * so here we ignore the `mShouldReverseLayout` param.
         * Additionally, as we can not access mLayoutState, we have to get related info by ourselves.
         * See LinearLayoutManager#updateLayoutState
         */
        val delta = if (orientation == HORIZONTAL) dx else dy
        if (childCount == 0 || delta == 0) {
            // can't support this scroll, so don't bother prefetching
            return
        }
        val layoutDirection = if (delta > 0) 1 else -1
        val child = getChildClosest(layoutDirection)
        val currentPosition = getPosition(child!!) + layoutDirection
        val scrollingOffset: Int
        /* Our aim is to pre-load, so we just handle layoutDirection=1 situation.
         * If we handle layoutDirection=-1 situation, each scroll with slightly toggle directions
         * will cause huge numbers of bindings.
         */if (layoutDirection == 1) {
            scrollingOffset = (mOrientationHelper!!.getDecoratedEnd(child)
                    - mOrientationHelper!!.endAfterPadding)
            for (i in currentPosition + 1 until currentPosition + mAdditionalAdjacentPrefetchItemCount + 1) {
                if (i >= 0 && i < state.itemCount) {
                    layoutPrefetchRegistry.addPosition(i, max(0, scrollingOffset))
                }
            }
        }
    }

    private fun getChildClosest(layoutDirection: Int): View? {
        return getChildAt(if (layoutDirection == -1) 0 else childCount - 1)
    }

    companion object {
        fun getInstance(
            activity: Activity,
            orientation: Int = VERTICAL,
            reverseLayout: Boolean = false,
            preloadItemCount: Int = 10
        ) = PreloadLinearLayoutManager(activity, orientation, reverseLayout).apply {
            setPreloadItemCount(preloadItemCount)
        }
    }
}