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

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.shtanko.picasagallery.view.activities.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) class MainActivityTest {

  @get:Rule val main: ActivityTestRule<MainActivity> = ActivityTestRule(
      MainActivity::class.java)

  val context = InstrumentationRegistry.getTargetContext()
  var mainActivity: MainActivity? = null

  @Before fun setUp() {
    mainActivity = main.activity
  }

  @Test fun run() {
    Assert.assertNotNull(mainActivity)
    Assert.assertNotNull(context)
  }

}