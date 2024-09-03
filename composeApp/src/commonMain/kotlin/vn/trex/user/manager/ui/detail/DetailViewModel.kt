package vn.trex.user.manager.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.trex.user.manager.data.model.User
import vn.trex.user.manager.data.repository.UserRepository

class DetailViewModel(
  private val userRepository: UserRepository,
  private val user: User
) : ViewModel() {
  private var _item = MutableStateFlow(user)

  //
  val item = _item.asStateFlow()

  init {
    getUserDetail()
  }

  private fun getUserDetail() {
    //
    viewModelScope.launch(Dispatchers.IO) {
      userRepository.getUserDetail(user.userName)
        .onSuccess { data ->
          if (data != null) {
            _item.value = data
          }
        }
        .onFailure {}
    }
  }
}