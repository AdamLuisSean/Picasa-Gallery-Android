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

package io.shtanko.picasagallery.view.profile

import android.os.Bundle
import dagger.Lazy
import io.shtanko.picasagallery.R
import io.shtanko.picasagallery.util.ActivityUtils
import io.shtanko.picasagallery.view.base.BaseActivity
import javax.inject.Inject


class ProfileActivity : BaseActivity() {

  @Inject lateinit var presenter: ProfilePresenter
  @Inject lateinit var fragmentProvider: Lazy<ProfileFragment>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.container_activity)
    addFragment()
  }

  private fun addFragment() {
    var fragment = supportFragmentManager.findFragmentById(
        R.id.content_frame) as ProfileFragment?
    if (fragment == null) {
      fragment = fragmentProvider.get()
      ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.content_frame)
    }
  }
}