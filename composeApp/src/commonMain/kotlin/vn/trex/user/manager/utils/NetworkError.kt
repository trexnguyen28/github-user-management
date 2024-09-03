package vn.trex.user.manager.utils

enum class NetworkError : Error {
  REQUEST_TIMEOUT,
  UNAUTHORIZED,
  CONFLICT,
  TOO_MANY_REQUESTS,
  NO_INTERNET,
  PAYLOAD_TOO_LARGE,
  SERVER_ERROR,
  SERIALIZATION,
  UNKNOWN;
}