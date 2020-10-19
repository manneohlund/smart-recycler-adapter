package smartrecycleradapter.feature

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_simple_item.recyclerView
import smartadapter.SmartRecyclerAdapter
import smartadapter.extension.SmartViewHolderBinder
import smartadapter.viewevent.listener.OnClickEventListener
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewevent.swipe.BasicSwipeEventBinder
import smartadapter.viewevent.swipe.SwipeFlags
import smartrecycleradapter.R
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import smartrecycleradapter.utils.showToast

/*
 * Created by Manne Öhlund on 2019-08-11.
 * Copyright (c) All rights reserved.
 */

class SwipeRemoveItemActivity : BaseSampleActivity() {

    private lateinit var smartRecyclerAdapter: SmartRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Swipe remove item"

        val items = (0..50).toMutableList()

        smartRecyclerAdapter = SmartRecyclerAdapter
            .items(items)
            .map(Integer::class, SimpleItemViewHolder::class)
            .add(OnClickEventListener {
                showToast("onClick ${it.position}")
            })
            .add(SwipeRemoveItemBinder(ItemTouchHelper.LEFT) {
                // Remove item from SmartRecyclerAdapter data set
                smartRecyclerAdapter.removeItem(it.viewHolder.adapterPosition)
            })
            .into(recyclerView)
    }

    class SwipeRemoveItemBinder(
        override var swipeFlags: SwipeFlags,
        override var eventListener: (ViewEvent.OnItemSwiped) -> Unit
    ) : BasicSwipeEventBinder(
        eventListener = eventListener
    ), SmartViewHolderBinder {
        override fun onChildDraw(
            canvas: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val itemView = viewHolder.itemView
            val icon = ContextCompat.getDrawable(
                viewHolder.itemView.context,
                R.drawable.ic_delete_black_24dp
            )
            val background = ColorDrawable(Color.RED)

            val iconMargin = (itemView.height - icon!!.intrinsicHeight) / 2
            val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
            val iconBottom = iconTop + icon.intrinsicHeight

            val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
            val iconRight = itemView.right - iconMargin

            icon.setBounds(
                iconLeft,
                iconTop,
                iconRight,
                iconBottom
            )

            background.setBounds(
                (itemView.right + dX).toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )

            if (dX.toInt() == 0) { // view is unSwiped
                background.setBounds(0, 0, 0, 0)
            }

            background.draw(canvas)

            if (-dX > (icon.intrinsicWidth + iconMargin)) // Draw icon only on full visibility
                icon.draw(canvas)
        }
    }
}
