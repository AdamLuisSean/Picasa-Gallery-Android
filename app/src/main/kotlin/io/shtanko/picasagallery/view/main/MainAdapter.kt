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

package io.shtanko.picasagallery.view.main

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import io.shtanko.picasagallery.Config
import io.shtanko.picasagallery.data.entity.album.AlbumType
import io.shtanko.picasagallery.extensions.AlbumsList
import io.shtanko.picasagallery.view.delegate.ViewType
import io.shtanko.picasagallery.view.delegate.ViewTypeAdapterDelegate
import io.shtanko.picasagallery.view.util.OnItemClickListener

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private var items: ArrayList<ViewType> = ArrayList()
  private var delegateAdapters = SparseArrayCompat<ViewTypeAdapterDelegate>()
  private var onItemClickListener: OnItemClickListener? = null

  init {
    delegateAdapters.put(Config.MAIN_VIEW_TYPE_ID, MainAdapterDelegateImpl())
  }

  fun addAlbums(list: AlbumsList) {
    items.addAll(list)
  }

  fun addAlbum(item: AlbumType) {
    items.add(item)
  }

  fun setOnItemClickListener(listener: OnItemClickListener) {
    if (onItemClickListener == null)
      this.onItemClickListener = listener
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
      delegateAdapters.get(viewType).onCreateViewHolder(parent)

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val model = this.items[position]
    holder.itemView.setOnClickListener {
      onItemClickListener?.onItemClicked(model)
    }
    delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, model)
  }

  override fun getItemViewType(position: Int): Int = this.items[position].getViewType()

  override fun getItemCount() = items.count()

}