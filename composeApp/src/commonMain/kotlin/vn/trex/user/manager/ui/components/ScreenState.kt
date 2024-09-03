package vn.trex.user.manager.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import vn.trex.user.manager.utils.FadeIn
import vn.trex.user.manager.utils.FadeOut

sealed class ScreenState<out T> {
  data object Loading : ScreenState<Nothing>()
  data class Success<T>(val data: T) : ScreenState<T>()
  data class Error(val message: String) : ScreenState<Nothing>()

  @Composable
  fun DisplayByState(
    onLoading: @Composable () -> Unit,
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable (String) -> Unit,
  ) {
    AnimatedContent(
      targetState = this,
      transitionSpec = {
        FadeIn togetherWith FadeOut
      },
      label = "Content Animation"
    ) { state ->
      when (state) {
        is Loading -> {
          onLoading()
        }

        is Success -> {
          onSuccess(state.data)
        }

        is Error -> {
          onError(state.message)
        }
      }
    }
  }
}