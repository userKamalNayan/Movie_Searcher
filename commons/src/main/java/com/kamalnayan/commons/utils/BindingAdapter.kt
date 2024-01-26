package com.kamalnayan.commons.utils

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/** @Author Kamal Nayan
Created on: 26/01/24
 **/

@BindingAdapter("isVisible")
fun setIsVisible(view: View, isVisible: Boolean) {
 view.isVisible = isVisible
}


@BindingAdapter("loadImageByUrl")
fun loadImageByUrl(view: ImageView, url: String) {
 if (url.isNullOrEmpty())
  return

 Glide.with(view).load(url).into(view)
}