package com.kamalnayan.commons.extensions

import android.content.res.Resources

/** @Author Kamal Nayan
Created on: 26/01/24
 **/

val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()