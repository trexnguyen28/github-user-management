package vn.trex.user.manager.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException

suspend inline fun <reified T> handleErrors(
  crossinline response: suspend () -> HttpResponse
): T {

  val result = try {
    response()
  } catch (e: UnresolvedAddressException) {
    throw NetworkException(NetworkError.NO_INTERNET)
  }

  when (result.status.value) {
    in 200..299 -> Unit
    in 404..499 -> throw NetworkException(NetworkError.CLIENT_ERROR)
    500 -> throw NetworkException(NetworkError.SERVER_ERROR)
    403 -> throw NetworkException(NetworkError.RATE_LIMITING_ERROR)
    else -> throw NetworkException(NetworkError.UNKNOWN)
  }

  return try {
    result.body()
  } catch (e: Exception) {
    throw NetworkException(NetworkError.SERIALIZATION)
  }
}