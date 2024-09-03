package vn.trex.user.manager.ui.main

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

class MainViewModel(
  private val userRepository: UserRepository
) : ViewModel() {

  private val _usersStateFlow =
    MutableStateFlow<ScreenState<List<User>>>(ScreenState.Loading)
  val usersStateFlow: StateFlow<ScreenState<List<User>>>
    get() = _usersStateFlow

  init {
    getUsers()
  }

  private fun getUsers() {
    viewModelScope.launch(Dispatchers.IO) {
      _usersStateFlow.emit(ScreenState.Loading)

      try {
        val httpResponse = userRepository.getUsers()

        if (httpResponse.status.value in 200..299) {
          val body = httpResponse.body<List<User>>()
          _usersStateFlow.emit(ScreenState.Success(body))
        } else {
          val body = httpResponse.body<HttpErrorResponse>()
          _usersStateFlow.emit(ScreenState.Error(body.message))
        }
      } catch (e: Exception) {
        _usersStateFlow.emit(ScreenState.Error(e.message.toString()))
      }
    }
  }
}