package smartrecycleradapter.viewholder

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import smartadapter.viewholder.SmartViewHolder
import smartrecycleradapter.R
import smartrecycleradapter.feature.simpleitem.SimpleItemViewHolder

/*
 * Created by Manne Öhlund on 2019-08-14.
 * Copyright (c) All rights reserved.
 */

class SimpleSelectableItemViewHolder(parentView: ViewGroup) :
    SimpleItemViewHolder(parentView)

class SimpleSelectableCheckBoxViewHolder(parentView: ViewGroup) :
    SmartViewHolder<Int>(parentView, R.layout.simple_checkbox_item) {

    private var textView: TextView = itemView.findViewById(R.id.textView)
    private var checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

    override fun bind(item: Int) {
        textView.text = "Item $item"
    }
}

class SimpleSelectableSwitchViewHolder(parentView: ViewGroup) :
    SmartViewHolder<Int>(parentView, R.layout.simple_switch_item) {

    private var textView: TextView = itemView.findViewById(R.id.textView)
    private var switch: SwitchCompat = itemView.findViewById(R.id.switchButton)

    override fun bind(item: Int) {
        textView.text = "Item $item"
    }
}

class SimpleSelectableRadioButtonViewHolder(parentView: ViewGroup) :
    SmartViewHolder<Int>(parentView, R.layout.simple_radiobutton_item) {

    private var textView: TextView = itemView.findViewById(R.id.textView)
    private var radioButton: RadioButton = itemView.findViewById(R.id.radioButton)

    override fun bind(item: Int) {
        textView.text = "Item $item"
    }
}
