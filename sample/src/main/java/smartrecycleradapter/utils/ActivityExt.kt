package smartrecycleradapter.utils

import android.os.Handler


/*
 * Created by Manne Öhlund on 2019-12-12.
 * Copyright (c) All rights reserved.
 */
 
 inline fun runDelayed(crossinline action: () -> Unit) {
     Handler().postDelayed({
         action.invoke()
     }, 800)
 }