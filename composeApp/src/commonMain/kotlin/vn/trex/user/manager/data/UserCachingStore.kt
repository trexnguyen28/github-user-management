package vn.trex.user.manager.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import vn.trex.user.manager.data.model.User

class UserCachingStore(
  private val dataStore: DataStore<Preferences>
) {
  private val USERS_PREFIX = "users"

  private val USER_PREFIX = "user"

  private fun generatePrefKey(prefix: String, key: String) =
    stringPreferencesKey("$prefix/$key")

  suspend fun saveUsers(key: String, users: List<User>) {
    val prefKey = generatePrefKey(prefix = USERS_PREFIX, key = key)
    val data = Json.encodeToString(users)

    Logger.d(messageString = "Save2 $key $data", tag = "Debug DataStore")


    dataStore.edit { preferences -> preferences[prefKey] = data }
  }

  suspend fun getUsers(key: String): List<User>? {
    val prefKey = generatePrefKey(prefix = USERS_PREFIX, key = key)
    val data = dataStore.data.map { preferences -> preferences[prefKey] }.first()

    return data?.let { Json.decodeFromString(data) }
  }

  suspend fun saveUser(key: String, user: User) {
    val prefKey = generatePrefKey(prefix = USER_PREFIX, key = key)
    val data = Json.encodeToString(user)

    dataStore.edit { preferences -> preferences[prefKey] = data }
  }

  suspend fun getUser(key: String): User? {
    val prefKey = generatePrefKey(prefix = USER_PREFIX, key = key)
    val data = dataStore.data.map { preferences -> preferences[prefKey] }.first()

    return data?.let { Json.decodeFromString(data) }
  }


}