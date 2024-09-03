package vn.trex.user.manager.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual fun createPlatformHttpClient(): HttpClient {
  return HttpClient(OkHttp)
}