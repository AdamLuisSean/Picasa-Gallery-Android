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

package io.shtanko.picasagallery.util

import android.app.Activity
import android.content.pm.ActivityInfo


class TestUtils {

  private fun rotateToLandscape(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
  }

  private fun rotateToPortrait(activity: Activity) {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
  }

}