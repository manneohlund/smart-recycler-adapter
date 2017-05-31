package smartadapter.widget;

import smartadapter.viewholder.SmartViewHolder;

/**
 * Created by Manne Öhlund on 02/04/17.
 * Copyright © 2017 All rights reserved.
 */

public interface ViewTypeResolver {
    Class<? extends SmartViewHolder> getViewType(Object item, int position);
}