/*
 * Copyright 2017 Alexey Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.shtanko.picasagallery.core.image

import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.ColorMatrixColorFilter
import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.bumptech.glide.DrawableRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.ViewPropertyAnimation.Animator
import com.bumptech.glide.request.target.Target
import io.shtanko.picasagallery.R
import io.shtanko.picasagallery.view.util.FadeAnimator
import io.shtanko.picasagallery.view.util.ObservableColorMatrix
import io.shtanko.picasagallery.view.util.OnLoadImageListener
import java.lang.Exception

class ImageHelperImpl : ImageHelper {


  override fun process(context: Context?, imageView: ImageView?, url: String) {
    load(context, url, object : OnLoadImageListener {
      override fun onLoadSucceed() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          imageView?.setHasTransientState(true)

          val matrix = ObservableColorMatrix
          val saturation = ObjectAnimator.ofFloat(matrix, ObservableColorMatrix.SATURATION, 0f,
              1f)

          saturation.addUpdateListener { imageView?.colorFilter = ColorMatrixColorFilter(matrix) }
          saturation.duration = 2800

          saturation.interpolator = AnimationUtils.loadInterpolator(context,
              android.R.interpolator.fast_out_slow_in)

          saturation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator?) {
              imageView?.clearColorFilter()
              if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
                imageView?.setHasTransientState(false)
              }
            }
          })
          saturation.start()
        }
      }

      override fun onLoadFailed() {
        imageView?.clearColorFilter()
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
          imageView?.setHasTransientState(false)
        }
      }
    }, imageView)
  }

  override fun load(context: Context?, url: String, listener: OnLoadImageListener?,
      imageView: ImageView?) {

    val thumbnailRequest = Glide
        .with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
        .placeholder(R.drawable.image_placeholder)
        .listener(object : RequestListener<String, GlideDrawable> {

          override fun onException(e: Exception?, model: String, target: Target<GlideDrawable>,
              isFirstResource: Boolean): Boolean = false

          override fun onResourceReady(resource: GlideDrawable, model: String,
              target: Target<GlideDrawable>,
              isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
            imageView?.isEnabled = true
            return false
          }
        })

    if (listener != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val matrix = ObservableColorMatrix
      matrix.setSaturation(0.0F)
      imageView?.colorFilter = ColorMatrixColorFilter(matrix)
      imageView?.isEnabled = false
    }

    loadImage(context, imageView, url, false, false,
        if (listener == null) null else thumbnailRequest, null,
        if (listener == null) null else FadeAnimator, listener)

  }

  override fun loadImage(context: Context?, imageView: ImageView?, url: String, noAnimate: Boolean,
      lowPriority: Boolean, thumbnail: DrawableRequestBuilder<String>?,
      transformation: BitmapTransformation?, animator: Animator?, listener: OnLoadImageListener?) {

    val builder = Glide
        .with(context)
        .load(url)
        .placeholder(R.drawable.image_placeholder)
        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
        .listener(object : RequestListener<String, GlideDrawable> {

          override fun onException(e: Exception?, model: String, target: Target<GlideDrawable>,
              isFirstResource: Boolean): Boolean {
            listener?.onLoadFailed()
            return false
          }

          override fun onResourceReady(resource: GlideDrawable, model: String,
              target: Target<GlideDrawable>,
              isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
            listener?.onLoadSucceed()
            return false
          }
        })

    if (noAnimate) {
      builder.dontAnimate()
    }
    if (lowPriority) {
      builder.priority(Priority.LOW)
    } else {
      builder.priority(Priority.NORMAL)
    }

    if (thumbnail != null) {
      builder.thumbnail(thumbnail)
    }
    if (transformation != null) {
      builder.transform(transformation)
    }
    if (animator != null) {
      builder.animate(animator)
    }
    builder.into(imageView)
  }
}