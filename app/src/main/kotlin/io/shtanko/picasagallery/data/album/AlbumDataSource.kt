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

package io.shtanko.picasagallery.data.album

import io.reactivex.Flowable
import io.shtanko.picasagallery.core.prefs.PreferenceHelper
import io.shtanko.picasagallery.data.api.ApiManager
import io.shtanko.picasagallery.extensions.AlbumsList
import javax.inject.Inject
import javax.inject.Singleton

interface AlbumDataSource {
  fun getAlbums(): Flowable<AlbumsList>
}

@Singleton
class AlbumDataSourceImpl @Inject constructor(
  private val apiManager: ApiManager,
  private val preferencesHelper: PreferenceHelper,
  private val albumEntityMapper: AlbumEntityMapper
) : AlbumDataSource {

  override fun getAlbums(): Flowable<AlbumsList> {
    return apiManager.getUserAlbums(
        preferencesHelper.getUserId()
    )
        .map { albumEntityMapper.transform(it) }
  }
}