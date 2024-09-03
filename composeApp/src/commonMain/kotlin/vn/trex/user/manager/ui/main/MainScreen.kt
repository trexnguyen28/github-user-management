package vn.trex.user.manager.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.stringResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
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
      val mainViewModel: MainViewModel by inject()

      EndlessLazyColumn(
        loading = mainViewModel.isLoading,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        items = mainViewModel.items,
        itemContent = { user -> UserItem(user = user, onClick = {}) },
        itemKey = { it.id },
        loadingItem = {
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
          ) {
            CircularProgressIndicator(modifier = Modifier.width(24.dp).height(24.dp))
          }
        },
        loadMore = { mainViewModel.getUsers() }
      )
    }
  }
}
