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

package io.shtanko.picasagallery.ui.auth

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.shtanko.picasagallery.util.ActivityScoped
import io.shtanko.picasagallery.util.FragmentScoped
import io.shtanko.picasagallery.ui.auth.SignInContract.Presenter

@Module
abstract class SignInModule {

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun signInFragment(): SignInFragment

  @ActivityScoped
  @Binds abstract fun signInPresenter(presenter: SignInPresenter): Presenter
}