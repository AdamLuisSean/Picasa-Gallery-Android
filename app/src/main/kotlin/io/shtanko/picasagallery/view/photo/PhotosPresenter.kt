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

package io.shtanko.picasagallery.view.photo

import io.shtanko.picasagallery.util.ActivityScoped
import io.shtanko.picasagallery.view.photo.PhotosContract.View
import javax.annotation.Nullable
import javax.inject.Inject

@ActivityScoped
class PhotosPresenter @Inject constructor() : PhotosContract.Presenter {

  @Nullable
  private var view: PhotosContract.View? = null

  override fun takeView(view: View) {
    this.view = view
  }

  override fun dropView() {
    this.view = null
  }
}