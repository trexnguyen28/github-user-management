package vn.trex.user.manager.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.stringResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import vn.trex.user.manager.ui.components.EndlessLazyColumn
import vn.trex.user.manager.ui.components.UserItem

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
      val mainViewModel: MainViewModel by inject()
      val uiState by mainViewModel.usersStateFlow.collectAsState()

      uiState.DisplayByState(
        onLoading = {
          Text(text = "Loading")
        },
        onSuccess = { users ->
          EndlessLazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            items = users,
            itemContent = { user -> UserItem(user = user, onClick = {}) },
            itemKey = { it.id },
            loadingItem = {},
            loadMore = {}
          )
        },
        onError = {
          Text(text = "Error")
        }
      )
    }
  }
}
