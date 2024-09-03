package vn.trex.user.manager.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import github_user_manager.composeapp.generated.resources.Res
import github_user_manager.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.currentKoinScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import vn.trex.user.manager.data.model.User
import vn.trex.user.manager.data.repository.UserRepository
import vn.trex.user.manager.di.koinViewModel
import vn.trex.user.manager.ui.components.EndlessLazyColumn
import vn.trex.user.manager.ui.components.UserItem
import vn.trex.user.manager.utils.USERS
import vn.trex.user.manager.utils.onSuccess


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
      val mainViewModel = koinViewModel<MainViewModel>()
      val isLoading by mainViewModel.isLoading.collectAsState()
      val items by mainViewModel.items.collectAsState()

      EndlessLazyColumn(
        loading = isLoading,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        items = items,
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