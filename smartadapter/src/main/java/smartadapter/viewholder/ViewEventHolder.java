package smartadapter.viewholder;

/*
 * Created by Manne Öhlund on 2019-06-10.
 * Copyright (c) All rights reserved.
 */

import java.util.HashMap;

import smartadapter.listener.OnViewActionListener;

public interface ViewEventHolder {
    void setViewEventListeners(HashMap<Integer, HashMap<Integer, OnViewActionListener>> viewEventListeners);
}
