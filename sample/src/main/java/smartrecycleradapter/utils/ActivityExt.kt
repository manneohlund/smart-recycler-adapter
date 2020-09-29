package smartrecycleradapter.utils

import android.os.Handler


/*
 * Created by Manne Öhlund on 2019-12-12.
 * Copyright (c) All rights reserved.
 */
 
 inline fun runDelayed(delay: Long = 800, crossinline action: () -> Unit) {
     Handler().postDelayed({
         action.invoke()
     }, delay)
 }