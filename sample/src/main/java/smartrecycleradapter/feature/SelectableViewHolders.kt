package smartrecycleradapter.feature

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import smartadapter.state.SelectionStateHolder
import smartadapter.viewholder.SmartViewHolder
import smartadapter.viewholder.StatefulViewHolder
import smartrecycleradapter.R
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder

/*
 * Created by Manne Öhlund on 2019-08-14.
 * Copyright (c) All rights reserved.
 */

class SimpleSelectableItemViewHolder(parentView: View) :
        SimpleItemViewHolder(parentView),
        StatefulViewHolder<SelectionStateHolder> {

    override lateinit var stateHolder: SelectionStateHolder

    override fun bind(item: Int) {
        super.bind(item)
        if (stateHolder.isSelected(adapterPosition)) {
            itemView.setBackgroundColor(Color.RED)
        } else {
            itemView.setBackgroundAttribute(R.attr.selectableItemBackground)
        }
    }
}

class SimpleSelectableCheckBoxViewHolder(parentView: View) : SmartViewHolder<Int>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.simple_checkbox_item, parentView as ViewGroup, false)),
        StatefulViewHolder<SelectionStateHolder> {

    override lateinit var stateHolder: SelectionStateHolder
    private var textView: TextView = itemView.findViewById(R.id.textView)
    private var checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

    override fun bind(item: Int) {
        textView.text = "Item $item"
        checkBox.isChecked = stateHolder.isSelected(adapterPosition)
    }
}

class SimpleSelectableSwitchViewHolder(parentView: View) : SmartViewHolder<Int>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.simple_switch_item, parentView as ViewGroup, false)),
        StatefulViewHolder<SelectionStateHolder> {

    override lateinit var stateHolder: SelectionStateHolder
    private var textView: TextView = itemView.findViewById(R.id.textView)
    private var switch: SwitchCompat = itemView.findViewById(R.id.switchButton)

    override fun bind(item: Int) {
        textView.text = "Item $item"
        switch.isChecked = stateHolder.isSelected(adapterPosition)
    }
}

class SimpleSelectableRadioButtonViewHolder(parentView: View) : SmartViewHolder<Int>(
        LayoutInflater.from(parentView.context)
                .inflate(R.layout.simple_radiobutton_item, parentView as ViewGroup, false)),
        StatefulViewHolder<SelectionStateHolder> {

    override lateinit var stateHolder: SelectionStateHolder
    private var textView: TextView = itemView.findViewById(R.id.textView)
    private var radioButton: RadioButton = itemView.findViewById(R.id.radioButton)

    override fun bind(item: Int) {
        textView.text = "Item $item"
        radioButton.isChecked = stateHolder.isSelected(adapterPosition)
    }
}
