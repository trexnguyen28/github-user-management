package vn.trex.user.manager.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.trex.user.manager.data.model.User
import vn.trex.user.manager.data.repository.UserRepository

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
          _items.value = users
          _isLoading.value = false
        }
        .onFailure {
          _isLoading.value = false
        }
    }
  }
}