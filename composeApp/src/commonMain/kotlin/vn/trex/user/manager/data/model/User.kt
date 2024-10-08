package vn.trex.user.manager.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
  @SerialName("id") var id: Int,
  @SerialName("avatar_url") var avatarUrl: String,
  @SerialName("login") var userName: String,
  @SerialName("html_url") var htmlUrl: String,
  @SerialName("location") var location: String? = "-",
  @SerialName("followers") var followers: Int? = 0,
  @SerialName("following") var following: Int? = 0,
  @SerialName("blog") var blog: String = "-",
)
