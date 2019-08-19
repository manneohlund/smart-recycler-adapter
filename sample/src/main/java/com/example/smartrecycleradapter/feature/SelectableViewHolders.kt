package com.example.smartrecycleradapter.feature

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import com.example.smartrecycleradapter.R
import com.example.smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder
import smartadapter.state.SelectionStateHolder
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.StatefulViewHolder


/*
 * Created by Manne Öhlund on 2019-08-14.
 * Copyright (c) All rights reserved.
 */

class SimpleSelectableItemViewHolder(parentView: View) : SimpleItemViewHolder(parentView), StatefulViewHolder<SelectionStateHolder> {
    private var selectionStateHolder: SelectionStateHolder? = null

    override fun setStateHolder(selectionStateHolder: SelectionStateHolder) {
        this.selectionStateHolder = selectionStateHolder
    }

    override fun bind(index: Int?) {
        super.bind(index)
        if (selectionStateHolder?.isSelected(adapterPosition) == true) {
            itemView.setBackgroundColor(Color.RED)
        } else {
            itemView.setBackgroundAttribute(R.attr.selectableItemBackground)
        }
    }
}

class SimpleSelectableCheckBoxViewHolder(parentView: View) : SmartViewHolder<Int>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.simple_checkbox_item, parentView as ViewGroup, false)
), StatefulViewHolder<SelectionStateHolder> {
    private var selectionStateHolder: SelectionStateHolder? = null
    private var textView: TextView = itemView.findViewById(R.id.textView)
    private var checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

    override fun setStateHolder(selectionStateHolder: SelectionStateHolder) {
        this.selectionStateHolder = selectionStateHolder
    }

    override fun bind(index: Int?) {
        textView.text = "Item $index"
        checkBox.isChecked = selectionStateHolder?.isSelected(adapterPosition) ?: false
    }
}

class SimpleSelectableSwitchViewHolder(parentView: View) : SmartViewHolder<Int>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.simple_switch_item, parentView as ViewGroup, false)
), StatefulViewHolder<SelectionStateHolder> {
    private var selectionStateHolder: SelectionStateHolder? = null
    private var textView: TextView = itemView.findViewById(R.id.textView)
    private var switch: SwitchCompat = itemView.findViewById(R.id.switchButton)

    override fun setStateHolder(selectionStateHolder: SelectionStateHolder) {
        this.selectionStateHolder = selectionStateHolder
    }

    override fun bind(index: Int?) {
        textView.text = "Item $index"
        switch.isChecked = selectionStateHolder?.isSelected(adapterPosition) ?: false
    }
}