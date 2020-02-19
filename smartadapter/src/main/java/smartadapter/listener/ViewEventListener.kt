package smartadapter.listener

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import io.github.manneohlund.smartrecycleradapter.R
import smartadapter.SmartViewHolderType
import smartadapter.ViewId
import smartadapter.viewholder.SmartViewHolder

/**
 * Callback added in [smartadapter.SmartRecyclerAdapter] for view events listening in [SmartViewHolder] extensions.
 */
private open class ViewEventListener<T>(
    /**
     * Default implementation returns [SmartViewHolder] class which
     * [smartadapter.SmartRecyclerAdapter] will bind to all [SmartViewHolder] extensions.
     *
     * Can be overridden to a specific target [SmartViewHolder] extension.
     */
    val viewHolderType: SmartViewHolderType = SmartViewHolder::class,

    /**
     * Default implementation returns [R.id.undefined] which will point to the root view of the view in the view holder.
     *
     * Can be overridden to target specific view id.
     */
    val viewId: ViewId = R.id.undefined,

    /**
     * Listener that can reference anything
     */
    val listener: T
)

fun test() {

    val listener = object : OnQueryTextChangeListener {
        override val listener: OnQueryTextListener
            get() = { view, smartRecyclerAdapter, i ->
                { queryText, type ->
                    when (type) {
                        QueryTextType.SUBMIT -> true
                        QueryTextType.CHANGE -> false
                    }
                }
            }
    }

    View.OnFocusChangeListener { v, hasFocus ->

    }

    object : OnTouchListener {
        override val listener: OnTouch
            get() = { view, event, _, _ ->
                when (event) {

                }
                true
            }
    }

    object : TextView.OnEditorActionListener {
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    open class OnItemCheckedChangeListener(
        l: OnCheckedChangeListener
    ) : ViewEventListener<OnCheckedChangeListener>(
        viewId = R.id.undefined,
        listener = l
    )
}

fun <T> set(listener: T) {
    (listener as OnItemClickListener)
}