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

package io.shtanko.picasagallery.data.user

import io.reactivex.Flowable
import io.shtanko.picasagallery.data.entity.User

interface UserCache {

  /**
   * Gets an {@link rx.Flowable} which will emit a {@link User}.
   */
  fun get(): Flowable<User>

  /**
   * Puts and element into the cache.
   *
   * @param user Element to insert in the cache.
   */
  fun put(user: User?)

  /**
   * Checks if an element (User) exists in the cache.
   *
   * @return true if the element is cached, otherwise false.
   */
  fun isCached(): Boolean

  /**
   * Checks if the cache is expired.
   *
   * @return true, the cache is expired, otherwise false.
   */
  fun isExpired(): Boolean

}