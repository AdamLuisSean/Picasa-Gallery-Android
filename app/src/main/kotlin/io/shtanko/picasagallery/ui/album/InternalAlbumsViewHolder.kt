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

package io.shtanko.picasagallery.ui.album

import android.view.View
import io.shtanko.picasagallery.R
import io.shtanko.picasagallery.data.entity.internal.Content
import io.shtanko.picasagallery.util.GlideApp
import io.shtanko.picasagallery.ui.base.BaseViewHolder
import io.shtanko.picasagallery.ui.delegate.ViewType
import io.shtanko.picasagallery.ui.util.Divided
import io.shtanko.picasagallery.ui.widget.FourThreeImageView

class InternalAlbumsViewHolder(itemView: View?) : BaseViewHolder(itemView), Divided {

  val image = itemView?.findViewById<FourThreeImageView>(R.id.album)

  init {
  }

  override fun bind(item: ViewType) {
    if (item is Content) {
      GlideApp.with(itemView.context)
          .load(item.src)
          .into(image)
    }
  }
}