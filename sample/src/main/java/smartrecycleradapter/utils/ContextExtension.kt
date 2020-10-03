package smartrecycleradapter.utils

import android.content.Context
import android.widget.Toast

/*
 * Created by Manne Öhlund on 2019-06-23.
 * Copyright (c) All rights reserved.
 */

val Context.displayWidth: Int
    get() = resources.displayMetrics.widthPixels

val Context.displayHeight: Int
    get() = resources.displayMetrics.heightPixels

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()