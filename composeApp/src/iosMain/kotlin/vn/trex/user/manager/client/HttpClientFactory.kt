package vn.trex.user.manager.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual fun createPlatformHttpClient(): HttpClient {
  return HttpClient(Darwin)
}