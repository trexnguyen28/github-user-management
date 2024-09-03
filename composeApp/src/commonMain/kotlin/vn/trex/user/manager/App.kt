package vn.trex.user.manager

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import vn.trex.user.manager.ui.main.MainScreen

@Composable
@Preview
fun App() {
  MaterialTheme {
    Navigator(MainScreen())
  }
}