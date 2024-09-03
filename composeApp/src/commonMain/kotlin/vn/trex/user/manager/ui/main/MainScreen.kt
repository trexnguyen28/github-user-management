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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.core.component.KoinComponent
import vn.trex.user.manager.di.koinViewModel
import vn.trex.user.manager.ui.components.EndlessLazyColumn
import vn.trex.user.manager.ui.components.UserItem
import vn.trex.user.manager.ui.detail.DetailScreen

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
      val navigator = LocalNavigator.currentOrThrow
      //
      val mainViewModel = koinViewModel<MainViewModel>(key = "Main")
      val isLoading by mainViewModel.isLoading.collectAsState()
      val items by mainViewModel.items.collectAsState()

      EndlessLazyColumn(
        loading = isLoading,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        items = items,
        itemContent = { user ->
          UserItem(
            user = user,
            onClick = { navigator.push(DetailScreen(user)) })
        },
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