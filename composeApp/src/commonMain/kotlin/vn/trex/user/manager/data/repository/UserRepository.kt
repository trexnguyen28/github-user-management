package vn.trex.user.manager.data.repository

import co.touchlab.kermit.Logger
import vn.trex.user.manager.client.GithubClient
import vn.trex.user.manager.data.UserCachingStore
import vn.trex.user.manager.data.model.User

class UserRepository(
  private val githubClient: GithubClient,
  private val userCachingStore: UserCachingStore
) {
  private var offset: Int = 0
  private var limit: Int = 20
  private val users: MutableList<User> = mutableListOf()

  private fun generateCachingKey(curOff: Int, curLimit: Int) = "$curOff/$curLimit"

  suspend fun getUsers(): Result<MutableList<User>> {
    return try {
      val key = generateCachingKey(curOff = offset, curLimit = limit)
      var response = userCachingStore.getUsers(key)
      val isMissCache = response == null

      Logger.d(messageString = "Get1 $key $response", tag = "Debug DataStore")

      if (isMissCache) {
        response = githubClient.getUsers(offset, limit)
      }

      if (response != null) {
        // Offset = last item id to prevent delete / deactivated users
        users.addAll(response)
        offset += users.last().id

        // caching for next request
        if (isMissCache) {
          userCachingStore.saveUsers(key, response)
        }
      }

      Result.success(users)
    } catch (e: Exception) {
      e.printStackTrace()
      Result.failure(e)
    }
  }
}