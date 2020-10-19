package smartadapter.stickyheader

/*
 * Created by Manne Öhlund on 2020-10-11.
 * Copyright (c) All rights reserved.
 */

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.extension.SmartRecyclerAdapterBinder
import smartadapter.listener.OnAttachedToRecyclerViewListener
import smartadapter.listener.OnDetachedFromRecyclerViewListener
import kotlin.reflect.KClass

/**
 * Defining the basic sticky header for linear layout.
 *
 * Code and inspiration from https://gist.github.com/filipkowicz/1a769001fae407b8813ab4387c42fcbd
 */
@SuppressLint("ClickableViewAccessibility")
class StickyHeaderItemDecorationExtension(
    override val identifier: Any = StickyHeaderItemDecorationExtension::class,
    private val shouldFadeOutHeader: Boolean = false,
    private val headerItemType: KClass<*>,
    private var isHeader: ((itemPosition: Int) -> Boolean)? = null,
    private val headerBackground: Drawable? = null,
    private val onHeaderTouchListener: (motionEvent: MotionEvent, itemPosition: Int) -> Unit = { motionEvent, itemPosition -> }
) : RecyclerView.ItemDecoration(),
    SmartRecyclerAdapterBinder,
    OnAttachedToRecyclerViewListener,
    OnDetachedFromRecyclerViewListener {

    private var headerCount = fun (): Int = 0
    private var currentHeader: Pair<Int, RecyclerView.ViewHolder>? = null

    private val dataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            // clear saved header as it can be outdated now
            currentHeader = null
        }
    }

    private val itemTouchListener = object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(
            recyclerView: RecyclerView,
            motionEvent: MotionEvent
        ): Boolean {
            val isWithinHeaderBounds =
                motionEvent.y <= currentHeader?.second?.itemView?.bottom ?: 0
            return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                isWithinHeaderBounds
            } else false
        }
    }

    private val onLayoutChangeListener = View.OnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
        // clear saved layout as it may need layout update
        currentHeader = null
    }

    override fun bind(smartRecyclerAdapter: SmartRecyclerAdapter) {
        isHeader = isHeader ?: { position ->
            headerItemType.isInstance(smartRecyclerAdapter.getItem(position))
        }

        headerCount = fun (): Int {
            var count = 0
            for (position in (currentHeader?.first ?: 0) downTo 0) {
                if (isHeader?.invoke(position) == true) {
                    count++
                }
            }
            return count
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addItemDecoration(this)
        recyclerView.adapter?.registerAdapterDataObserver(dataObserver)
        recyclerView.addOnLayoutChangeListener(onLayoutChangeListener)
        // handle click on sticky header
        recyclerView.addOnItemTouchListener(itemTouchListener)
        recyclerView.setOnTouchListener { view, motionEvent ->
            val isWithinHeaderBounds =
                motionEvent.y <= currentHeader?.second?.itemView?.bottom ?: 0
            if (isWithinHeaderBounds) {
                currentHeader?.let {
                    onHeaderTouchListener.invoke(
                        motionEvent,
                        it.first - headerCount() + 1
                    )
                }
            }
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                isWithinHeaderBounds
            } else false
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        recyclerView.removeItemDecoration(this)
        recyclerView.adapter?.unregisterAdapterDataObserver(dataObserver)
        recyclerView.removeOnLayoutChangeListener(onLayoutChangeListener)
        // handle click on sticky header
        recyclerView.removeOnItemTouchListener(itemTouchListener)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //val topChild = parent.getChildAt(0) ?: return
        val topChild = parent.findChildViewUnder(
            parent.paddingLeft.toFloat(),
            parent.paddingTop.toFloat() /*+ (currentHeader?.second?.itemView?.height ?: 0 )*/
        ) ?: return

        val topChildPosition = parent.getChildAdapterPosition(topChild)
        if (topChildPosition == RecyclerView.NO_POSITION) {
            return
        }

        val headerView = getHeaderViewForItem(topChildPosition, parent) ?: return
        headerBackground?.let {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                headerView.background = it
            }
        }

        val contactPoint = headerView.bottom + parent.paddingTop
        val childInContact = getChildInContact(parent, contactPoint) ?: return

        if (isHeader?.invoke(parent.getChildAdapterPosition(childInContact)) == true) {
            moveHeader(c, headerView, childInContact, parent.paddingTop)
            return
        }

        drawHeader(c, headerView, parent.paddingTop)
    }

    private fun getHeaderViewForItem(itemPosition: Int, parent: RecyclerView): View? {
        if (parent.adapter == null) {
            return null
        }
        val headerPosition = getHeaderPositionForItem(itemPosition)
        if (headerPosition == RecyclerView.NO_POSITION) return null
        val headerType = parent.adapter?.getItemViewType(headerPosition) ?: return null
        // if match reuse viewHolder
        if (currentHeader?.first == headerPosition && currentHeader?.second?.itemViewType == headerType) {
            return currentHeader?.second?.itemView
        }

        val headerHolder = parent.adapter?.createViewHolder(parent, headerType)
        if (headerHolder != null) {
            parent.adapter?.onBindViewHolder(headerHolder, headerPosition)
            fixLayoutSize(parent, headerHolder.itemView)
            // save for next draw
            /*headerCount += when {
                currentHeader?.first ?: 0 == itemPosition -> 0
                currentHeader?.first ?: 0 < headerPosition -> 1
                else -> -1
            }*/
            currentHeader = headerPosition to headerHolder
        }
        return headerHolder?.itemView
    }

    private fun drawHeader(c: Canvas, header: View, paddingTop: Int) {
        c.save()
        c.translate(0f, paddingTop.toFloat())
        header.draw(c)
        c.restore()
    }

    private fun moveHeader(c: Canvas, currentHeader: View, nextHeader: View, paddingTop: Int) {
        c.save()
        if (!shouldFadeOutHeader) {
            c.clipRect(0, paddingTop, c.width, paddingTop + currentHeader.height)
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                c.saveLayerAlpha(
                    RectF(0f, 0f, c.width.toFloat(), c.height.toFloat()),
                    (((nextHeader.top - paddingTop) / nextHeader.height.toFloat()) * 255).toInt()
                )
            } else {
                c.saveLayerAlpha(
                    0f, 0f, c.width.toFloat(), c.height.toFloat(),
                    (((nextHeader.top - paddingTop) / nextHeader.height.toFloat()) * 255).toInt(),
                    Canvas.ALL_SAVE_FLAG
                )
            }

        }
        c.translate(0f, (nextHeader.top - currentHeader.height).toFloat() /*+ paddingTop*/)

        currentHeader.draw(c)
        if (shouldFadeOutHeader) {
            c.restore()
        }
        c.restore()
    }

    private fun getChildInContact(parent: RecyclerView, contactPoint: Int): View? {
        var childInContact: View? = null
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val mBounds = Rect()
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            if (mBounds.bottom > contactPoint) {
                if (mBounds.top <= contactPoint) {
                    // This child overlaps the contactPoint
                    childInContact = child
                    break
                }
            }
        }
        return childInContact
    }

    /**
     * Properly measures and layouts the top sticky header.
     *
     * @param parent ViewGroup: RecyclerView in this case.
     */
    private fun fixLayoutSize(parent: ViewGroup, view: View) {

        // Specs for parent (RecyclerView)
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec =
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        // Specs for children (headers)
        val childWidthSpec = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )
        val childHeightSpec = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )

        view.measure(childWidthSpec, childHeightSpec)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }

    private fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = RecyclerView.NO_POSITION
        var currentPosition = itemPosition
        do {
            if (isHeader?.invoke(currentPosition) == true) {
                headerPosition = currentPosition
                break
            }
            currentPosition -= 1
        } while (currentPosition >= 0)
        return headerPosition
    }
}
