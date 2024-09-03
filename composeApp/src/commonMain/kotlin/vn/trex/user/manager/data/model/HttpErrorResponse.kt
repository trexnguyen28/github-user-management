package vn.trex.user.manager.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HttpErrorResponse(
  @SerialName("code")
  val code: String,
  @SerialName("message")
  val message: String,
  @SerialName("status")
  val status: String
)