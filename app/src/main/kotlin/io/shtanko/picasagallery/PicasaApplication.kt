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

package io.shtanko.picasagallery

import android.app.Application
import android.os.Handler
import android.os.StrictMode
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.shtanko.picasagallery.core.di.component.DaggerAppComponent
import kotlin.properties.Delegates

class PicasaApplication : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
    DaggerAppComponent.builder().application(this).build()

  companion object {
    var app: Application by Delegates.notNull()
    @Volatile
    lateinit var applicationHandler: Handler

    fun enableStrictMode() {
      StrictMode.setThreadPolicy(
          StrictMode.ThreadPolicy.Builder()
              .detectAll()
              .penaltyLog()
              .penaltyDeath()
              .build()
      )
    }
  }

  override fun onCreate() {
    super.onCreate()
    app = this
    applicationHandler = Handler(mainLooper)
    setupLeakCanary()
  }

  private fun setupLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return
    }

    PicasaApplication.enableStrictMode()
    LeakCanary.install(this)
  }
}