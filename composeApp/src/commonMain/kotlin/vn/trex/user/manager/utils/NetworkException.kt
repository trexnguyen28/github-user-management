package vn.trex.user.manager.utils

enum class NetworkError {
  NO_INTERNET,
  RATE_LIMITING_ERROR,
  CLIENT_ERROR,
  SERVER_ERROR,
  SERIALIZATION,
  UNKNOWN;
}

class NetworkException(error: NetworkError) : Exception(
  "Something goes wrong: $error"
)