package vn.trex.user.manager.data.repository

import vn.trex.user.manager.client.GithubClient
import vn.trex.user.manager.data.model.User

class UserRepository(private val githubClient: GithubClient) {
  private var offset: Int = 0
  private var limit: Int = 20
  private val users: MutableList<User> = mutableListOf()

  suspend fun getUsers(): Result<MutableList<User>> {
    return try {
      val response = githubClient.getUsers(offset, limit)

      // Offset = last item id to prevent delete / deactivated users
      users.addAll(response)
      offset += users.last().id
      Result.success(users)
    } catch (e: Exception) {
      e.printStackTrace()
      Result.failure(e)
    }
  }
}