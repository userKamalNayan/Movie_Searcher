package com.kamalnayan.commons.utils

import android.os.SystemClock
import android.view.View
import kotlinx.coroutines.delay

/**
 * This class is used to prevent multiple / frequent clicks.
 * The [onSafeClick] method is only invoked when the [interval] is
 * reached.
 */
  class SafeClickListener(
    private val interval: Long = 1000,
    private val onSafeClick: (View?) -> Unit
) :
    View.OnClickListener {
    private var lastClickTime: Long = 0
    override fun onClick(p0: View?) {
        val timeDiff = SystemClock.elapsedRealtime() - lastClickTime
        if (timeDiff < interval) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        onSafeClick.invoke(p0)
    }
}