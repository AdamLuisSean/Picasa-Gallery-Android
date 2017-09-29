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

package io.shtanko.picasagallery.view.album

import io.shtanko.picasagallery.data.entity.internal.Content
import io.shtanko.picasagallery.extensions.ContentList
import io.shtanko.picasagallery.view.base.BaseContentView
import io.shtanko.picasagallery.view.base.BaseErrorView
import io.shtanko.picasagallery.view.base.BasePresenter
import io.shtanko.picasagallery.view.base.BaseProgressView
import io.shtanko.picasagallery.view.base.BaseView
import io.shtanko.picasagallery.view.base.Clickable

interface InternalAlbumsContract {
	abstract interface View : BaseView<Presenter>, BaseProgressView, BaseErrorView, BaseContentView<ContentList> {
		fun viewAlbum(model: Content)
	}

	abstract interface Presenter : BasePresenter<View>, Clickable {
		fun getContent()
	}
}