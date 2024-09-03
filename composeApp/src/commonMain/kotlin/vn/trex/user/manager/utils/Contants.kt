package vn.trex.user.manager.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import vn.trex.user.manager.data.model.User

val USERS: List<User> = listOf(
  User(
    id = 1,
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    userName = "defunkt",
    htmlUrl = "https://github.com/defunkt"
  ),
  User(
    id = 2,
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    userName = "defunkt",
    htmlUrl = "https://github.com/defunkt"
  ),
  User(
    id = 3,
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    userName = "defunkt",
    htmlUrl = "https://github.com/defunkt"
  ),
  User(
    id = 5,
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    userName = "defunkt",
    htmlUrl = "https://github.com/defunkt"
  ),
  User(
    id = 6,
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    userName = "defunkt",
    htmlUrl = "https://github.com/defunkt"
  ),
  User(
    id = 7,
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    userName = "defunkt",
    htmlUrl = "https://github.com/defunkt"
  ),
  User(
    id = 8,
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    userName = "defunkt",
    htmlUrl = "https://github.com/defunkt"
  ),
  User(
    id = 9,
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    userName = "defunkt",
    htmlUrl = "https://github.com/defunkt"
  ),
  User(
    id = 10,
    avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
    userName = "defunkt",
    htmlUrl = "https://github.com/defunkt"
  )
)

object NetworkConstants {
  const val BASE_URL = "https://api.github.com/"

  const val TIMEOUT: Long = 60_000

  const val pageLimit = 20

  object UserApi {
    val ROUTE = BASE_URL + "users"
    val byId: (String) -> String = { id -> "$ROUTE/$id" }
  }
}

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
  scaleIn(
    initialScale = 0.92f,
    animationSpec = tween(220, delayMillis = 90)
  )

val FadeOut = fadeOut(animationSpec = tween(90))