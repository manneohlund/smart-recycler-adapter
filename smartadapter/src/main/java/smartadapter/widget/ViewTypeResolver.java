package smartadapter.widget;

/*
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

import smartadapter.viewholder.SmartViewHolder;

public interface ViewTypeResolver {
    Class<? extends SmartViewHolder> getViewType(Object item, int position);
}