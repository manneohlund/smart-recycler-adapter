package smartrecycleradapter.extension

/*
 * Created by Manne Öhlund on 2019-08-15.
 * Copyright (c) All rights reserved.
 */

import android.app.Activity
import android.content.Context
import android.graphics.Point

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PreCachingLinearLayoutManager : LinearLayoutManager {
    private var extraLayoutSpace = -1
    private var context: Context? = null

    constructor(context: Context) : super(context) {
        this.context = context
    }

    constructor(context: Context, extraLayoutSpace: Int) : super(context) {
        this.context = context
        this.extraLayoutSpace = extraLayoutSpace
    }

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    ) {
        this.context = context
    }

    fun setExtraLayoutSpace(extraLayoutSpace: Int) {
        this.extraLayoutSpace = extraLayoutSpace
    }

    override fun getExtraLayoutSpace(state: RecyclerView.State): Int {
        return if (extraLayoutSpace > 0) {
            extraLayoutSpace
        } else DEFAULT_EXTRA_LAYOUT_SPACE
    }

    companion object {
        private val DEFAULT_EXTRA_LAYOUT_SPACE = 600

        fun getInstance(activity: Activity): PreCachingLinearLayoutManager {
            // Setup layout manager
            val size = Point()
            activity.windowManager.defaultDisplay.getSize(size)
            val height = size.y
            val preCachingLinearLayoutManager = PreCachingLinearLayoutManager(activity)
            preCachingLinearLayoutManager.orientation = RecyclerView.VERTICAL
            preCachingLinearLayoutManager.setExtraLayoutSpace(height * 2)
            return preCachingLinearLayoutManager
        }
    }
}
