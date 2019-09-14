package smartadapter.listener

/*
 * Created by Manne Öhlund on 2019-07-23.
 * Copyright © 2019. All rights reserved.
 */

import smartadapter.viewholder.LoadMoreViewHolder

/**
 * Type alias lambda callback for [smartadapter.SmartEndlessScrollRecyclerAdapter] to intercept when the adapter
 * has scrolled to the last item. The [smartadapter.viewholder.LoadMoreViewHolder] will show without any
 * mapped data type.
 */
typealias OnLoadMoreListener = (loadMoreViewHolder: LoadMoreViewHolder) -> Unit
