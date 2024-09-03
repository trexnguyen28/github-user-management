package vn.trex.user.manager.data.repository

import io.ktor.client.call.body
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

import vn.trex.user.manager.client.GithubClient
import vn.trex.user.manager.data.model.User
import vn.trex.user.manager.utils.NetworkError
import vn.trex.user.manager.utils.Result

class UserRepository(private val githubClient: GithubClient) {
  private var offset: Int = 0
  private var limit: Int = 20
  private val users: MutableList<User> = mutableListOf()

  suspend fun getUsers(): Result<MutableList<User>, NetworkError> {
    val response = try {
      githubClient.getUsers(offset, limit)
    } catch (e: UnresolvedAddressException) {
      return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
      return Result.Error(NetworkError.SERIALIZATION)
    }

    return when (response.status.value) {
      in 200..299 -> {
        val result = response.body<MutableList<User>>()

        // Update new list user and prepare for next offset
        users.addAll(result)
        // Offset = last item id to prevent delete / deactivated users
        offset += users.last().id
        Result.Success(users)
      }

      401 -> Result.Error(NetworkError.UNAUTHORIZED)
      409 -> Result.Error(NetworkError.CONFLICT)
      408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
      413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
      in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
      else -> Result.Error(NetworkError.UNKNOWN)
    }
  }
}