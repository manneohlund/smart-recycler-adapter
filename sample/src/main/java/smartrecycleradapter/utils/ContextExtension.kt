package smartrecycleradapter.utils

import android.content.Context


/*
 * Created by Manne Öhlund on 2019-06-23.
 * Copyright (c) All rights reserved.
 */
 
val Context.displayWidth : Int
    get() = resources.displayMetrics.widthPixels

val Context.displayHeight : Int
    get() = resources.displayMetrics.heightPixels