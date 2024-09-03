package vn.trex.user.manager.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import vn.trex.user.manager.data.model.User
import vn.trex.user.manager.data.repository.UserRepository
import vn.trex.user.manager.ui.components.EndlessLazyColumn
import vn.trex.user.manager.ui.components.UserItem
import vn.trex.user.manager.utils.USERS

class MainScreen : Screen, KoinComponent {
  @Composable
  override fun Content() {
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text(text = "Github Users") },
          backgroundColor = Color.White
        )
      },
    ) {
      var users by remember {
        mutableStateOf<List<User>?>(null)
      }
      val scope = rememberCoroutineScope()
      val userRepository: UserRepository by inject()

      LaunchedEffect(Unit) {
        scope.launch {
          users = userRepository.getUsers(offset = 0, limit = 25)
        }
      }

      users?.let {
        EndlessLazyColumn(
          modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
          items = users!!,
          itemContent = { user -> UserItem(user = user, onClick = {}) },
          itemKey = { it.id },
          loadingItem = {},
          loadMore = {}
        )
      }
    }
  }
}
