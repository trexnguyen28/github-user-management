package vn.trex.user.manager.utils

object NetworkConstants {
  const val BASE_URL = "https://api.github.com/"

  const val TIMEOUT: Long = 60_000

  const val pageLimit = 20

  // TODO: You need to put token here
  // In fact: We should not implement like that -> Put token in .env file or obtain from server
  const val API_TOKEN = ""

  object UserApi {
    const val ROUTE = BASE_URL + "users"
    val byId: (String) -> String = { id -> "users/$id" }
  }
}

const val dataStoreFileName = "caching.preferences_pb"
