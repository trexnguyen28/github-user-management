package vn.trex.user.manager

import androidx.compose.ui.window.ComposeUIViewController
import vn.trex.user.manager.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
  initKoin()
}) {
  App()
}