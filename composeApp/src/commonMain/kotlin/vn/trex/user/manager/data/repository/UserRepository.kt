package vn.trex.user.manager.data.repository

import io.ktor.client.call.body

import vn.trex.user.manager.client.GithubClient
import vn.trex.user.manager.data.model.User

class UserRepository(private val githubClient: GithubClient) {
  suspend fun getUsers(offset: Int, limit: Int): List<User> {
    val response = githubClient.getUsers(offset, limit)
    return response.body<List<User>>()

//    val response = try {
//      githubClient.getUsers()
//    } catch (e: UnresolvedAddressException) {
//      return Result.Error(NetworkError.NO_INTERNET)
//    } catch (e: SerializationException) {
//      return Result.Error(NetworkError.SERIALIZATION)
//    }
//
//    return when (response.status.value) {
//      in 200..299 -> {
//        val censoredText = response.body<List<GithubUser>>()
//        Result.Success(censoredText)
//      }
//
//      401 -> Result.Error(NetworkError.UNAUTHORIZED)
//      409 -> Result.Error(NetworkError.CONFLICT)
//      408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
//      413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
//      in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
//      else -> Result.Error(NetworkError.UNKNOWN)
//    }
  }
}