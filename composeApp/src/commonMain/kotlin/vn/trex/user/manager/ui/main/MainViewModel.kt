package vn.trex.user.manager.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import vn.trex.user.manager.data.model.HttpErrorResponse
import vn.trex.user.manager.data.model.User
import vn.trex.user.manager.data.repository.UserRepository
import vn.trex.user.manager.ui.components.ScreenState
import vn.trex.user.manager.utils.onError
import vn.trex.user.manager.utils.onSuccess

class MainViewModel(
  private val userRepository: UserRepository
) : ViewModel() {
  var items: MutableList<User> by mutableStateOf(mutableListOf())
  var isLoading: Boolean by mutableStateOf(true)

  init {
    getUsers()
  }

  fun getUsers() {
    isLoading = true
    //
    viewModelScope.launch(Dispatchers.IO) {
      userRepository.getUsers()
        .onSuccess { users ->
          viewModelScope.launch(Dispatchers.Main) {
            items = users
            isLoading = false
          }
        }
    }
  }
}