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

package io.shtanko.picasagallery.extensions

import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout.LayoutParams
import io.shtanko.picasagallery.ui.util.AndroidUtils.dp

private fun getSize(size: Float): Int = (if (size < 0) size.toInt() else dp(size))

fun ViewGroup.inflate(layoutRes: Int): View =
  from(context).inflate(layoutRes, this, false)

fun createFrame(
  width: Int,
  height: Int,
  gravity: Int
): LayoutParams =
  LayoutParams(getSize(width.toFloat()), getSize(height.toFloat()), gravity)

fun createFrame(
  width: Int,
  height: Float
): LayoutParams =
  LayoutParams(getSize(width.toFloat()), getSize(height))