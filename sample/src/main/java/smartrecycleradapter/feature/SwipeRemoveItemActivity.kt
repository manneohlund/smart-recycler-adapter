package smartrecycleradapter.feature

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_simple_item.*
import smartadapter.SmartRecyclerAdapter
import smartadapter.listener.OnClick
import smartadapter.listener.OnItemClickListener
import smartadapter.widget.BasicSwipeExtension
import smartadapter.widget.Direction
import smartadapter.widget.SwipeExtensionBuilder
import smartrecycleradapter.R
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder

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
                .addViewEventListener(object : OnItemClickListener {
                    override val listener: OnClick = { _, _, position ->
                        Toast.makeText(applicationContext, "onClick $position", Toast.LENGTH_SHORT).show()
                    }
                })
                .addExtensionBuilder(SwipeExtensionBuilder(SwipeRemoveItemExtension()).apply {
                    swipeFlags = ItemTouchHelper.LEFT
                    onItemSwipedListener = { viewHolder, direction ->
                        showToast(viewHolder, direction)
                        // Remove item from SmartRecyclerAdapter data set
                        smartRecyclerAdapter.removeItem(viewHolder.adapterPosition)
                    }
                })
                .into(recyclerView)
    }

    val showToast = { viewHolder: RecyclerView.ViewHolder, _: Direction ->
        Toast.makeText(applicationContext, "onItemSwipeRemove @ ${viewHolder.adapterPosition}", Toast.LENGTH_SHORT).show()
    }

    class SwipeRemoveItemExtension : BasicSwipeExtension() {
        override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
            val itemView = viewHolder.itemView
            val icon = ContextCompat.getDrawable(viewHolder.itemView.context, R.drawable.ic_delete_black_24dp)
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
                    iconBottom)

            background.setBounds(
                    (itemView.right + dX).toInt(),
                    itemView.top,
                    itemView.right,
                    itemView.bottom)

            if (dX.toInt() == 0) { // view is unSwiped
                background.setBounds(0, 0, 0, 0)
            }

            background.draw(canvas)

            if (-dX > (icon.intrinsicWidth + iconMargin)) // Draw icon only on full visibility
                icon.draw(canvas)
        }
    }
}
