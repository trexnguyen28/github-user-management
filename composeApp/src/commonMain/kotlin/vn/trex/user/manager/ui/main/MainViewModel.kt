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
import kotlinx.coroutines.flow.asStateFlow
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
  private var _items = MutableStateFlow<MutableList<User>>(mutableListOf())
  private var _isLoading = MutableStateFlow(true)

  //
  val items = _items.asStateFlow()
  val isLoading = _isLoading.asStateFlow()

  init {
    getUsers()
  }

  fun getUsers() {
    _isLoading.value = true
    //
    viewModelScope.launch(Dispatchers.IO) {
      userRepository.getUsers()
        .onSuccess { users ->
          viewModelScope.launch(Dispatchers.Main) {
            _items.value = users
            _isLoading.value = false
          }
        }
    }
  }
}