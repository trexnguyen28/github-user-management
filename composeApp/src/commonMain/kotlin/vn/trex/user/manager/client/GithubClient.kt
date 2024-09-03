package vn.trex.user.manager.client

import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import vn.trex.user.manager.utils.NetworkConstants

class GithubClient {
  private val client = createPlatformHttpClient().config {
    defaultRequest {
      url(NetworkConstants.UserApi.ROUTE)
      contentType(ContentType.Application.Json)
    }

    install(Logging) {
      logger = Logger.DEFAULT
      level = LogLevel.ALL
    }

    install(HttpTimeout) {
      socketTimeoutMillis = NetworkConstants.TIMEOUT
      requestTimeoutMillis = NetworkConstants.TIMEOUT
    }

    install(ContentNegotiation) {
      json(Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
      })
    }
  }

  suspend fun getUsers(offset: Int = 0, limit: Int = NetworkConstants.pageLimit): HttpResponse {
    return client.get {
      url {
        parameters.append("per_page", limit.toString())
        parameters.append("since", offset.toString())
      }
    }
  }
}