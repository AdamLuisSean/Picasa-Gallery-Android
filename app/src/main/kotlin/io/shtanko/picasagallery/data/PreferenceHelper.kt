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

package io.shtanko.picasagallery.data

import android.content.SharedPreferences
import io.shtanko.picasagallery.Config
import io.shtanko.picasagallery.data.entity.User
import javax.inject.Singleton

@Singleton
class PreferenceHelper constructor(val sharedPreferences: SharedPreferences) {

  private val editor = sharedPreferences.edit()

  fun saveUserData(user: User) {
    if (editor != null) {
      editor.putString(Config.SAVED_PERSON_NAME_PREF, user.personName)
      editor.putString(Config.SAVED_PERSON_GIVEN_NAME_PREF, user.personGivenName)
      editor.putString(Config.SAVED_PERSON_FAMILY_NAME_PREF, user.personFamilyName)
      editor.putString(Config.SAVED_EMAIL_PREF, user.personEmail)
      editor.putString(Config.SAVED_ID_PREF, user.personId)
      editor.commit()
      editor.apply()
    }
  }

  fun getUser(): User {
    val personName = sharedPreferences.getString(Config.SAVED_PERSON_NAME_PREF, "")
    val personGivenName = sharedPreferences.getString(Config.SAVED_PERSON_GIVEN_NAME_PREF, "")
    val personFamilyName = sharedPreferences.getString(Config.SAVED_PERSON_FAMILY_NAME_PREF, "")
    val personEmail = sharedPreferences.getString(Config.SAVED_EMAIL_PREF, "")
    val personId = sharedPreferences.getString(Config.SAVED_ID_PREF, "")
    val user = User(personName, personGivenName, personFamilyName, personEmail, personId)
    return user
  }

  fun saveToken(token: String) {
    if (editor != null) {
      editor.putString(Config.SAVED_TOKEN_PREF, token)
      editor.commit()
    }
  }

  fun getToken(): String = sharedPreferences.getString(Config.SAVED_TOKEN_PREF, "")

  fun getUserId(): String = sharedPreferences.getString(Config.SAVED_ID_PREF, "")

}