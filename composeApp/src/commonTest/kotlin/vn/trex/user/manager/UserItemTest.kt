package vn.trex.user.manager

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import vn.trex.user.manager.data.model.User
import vn.trex.user.manager.ui.components.UserItem
import kotlin.test.Test

class UserItemTest {
  @OptIn(ExperimentalTestApi::class)
  @Test
  fun testUserItemDisplay() = runComposeUiTest {
    val mockUser = User(
      id = 10,
      avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
      userName = "defunkt",
      htmlUrl = "https://github.com/defunkt"
    )

    setContent {
      UserItem(
        user = mockUser,
        onClick = {}
      )
    }

    onNodeWithText(mockUser.userName).assertExists()
    onNodeWithText(mockUser.htmlUrl).assertExists()
    onNodeWithText(mockUser.id.toString()).assertDoesNotExist()
  }
}