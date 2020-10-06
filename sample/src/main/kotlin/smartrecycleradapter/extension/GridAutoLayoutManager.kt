package smartrecycleradapter.extension

/*
 * Created by Manne Öhlund on 2019-06-20.
 * Copyright (c) All rights reserved.
 */

import android.content.Context
import android.util.TypedValue

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class GridAutoLayoutManager : GridLayoutManager {
    private var mColumnWidth: Int = 0
    private var mColumnWidthChanged = true
    private var mWidthChanged = true
    private var mWidth: Int = 0

    constructor(context: Context, columnWidth: Int) : super(context, 1) {
        setColumnWidth(checkedColumnWidth(context, columnWidth))
    }/* Initially set spanCount to 1, will be changed automatically later. */

    constructor(
        context: Context,
        columnWidth: Int,
        orientation: Int,
        reverseLayout: Boolean
    ) : super(context, 1, orientation, reverseLayout) {
        setColumnWidth(checkedColumnWidth(context, columnWidth))
    }/* Initially set spanCount to 1, will be changed automatically later. */

    private fun checkedColumnWidth(context: Context, columnWidth: Int): Int {
        var width = columnWidth
        if (width <= 0) {
            width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, sColumnWidth.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        } else {
            width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, width.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        }
        return width
    }

    private fun setColumnWidth(newColumnWidth: Int) {
        if (newColumnWidth > 0 && newColumnWidth != mColumnWidth) {
            mColumnWidth = newColumnWidth
            mColumnWidthChanged = true
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        val width = width
        val height = height

        if (width != mWidth) {
            mWidthChanged = true
            mWidth = width
        }

        if (mColumnWidthChanged && mColumnWidth > 0 && width > 0 && height > 0 || mWidthChanged) {
            val totalSpace: Int
            if (orientation == LinearLayoutManager.VERTICAL) {
                totalSpace = width - paddingRight - paddingLeft
            } else {
                totalSpace = height - paddingTop - paddingBottom
            }
            val spanCount = max(1, totalSpace / mColumnWidth)
            setSpanCount(spanCount)
            mColumnWidthChanged = false
            mWidthChanged = false
        }
        super.onLayoutChildren(recycler, state)
    }

    companion object {
        private const val sColumnWidth = 200 // assume cell width of 200dp
    }
}
