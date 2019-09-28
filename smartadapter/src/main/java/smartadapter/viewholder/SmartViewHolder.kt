package smartadapter.viewholder

/*
 * Created by Manne Öhlund on 2019-06-10.
 * Copyright (c) All rights reserved.
 */

import android.view.View

import androidx.recyclerview.widget.RecyclerView

/**
 * Extension of [RecyclerView.ViewHolder] containing data item binding method.
 * @param <T> Data item type
 */
abstract class SmartViewHolder<T : Any>(view: View) : RecyclerView.ViewHolder(view), BindableViewHolder<T>