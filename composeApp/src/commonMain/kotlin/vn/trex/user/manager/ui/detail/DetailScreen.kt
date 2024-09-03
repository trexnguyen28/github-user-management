package vn.trex.user.manager.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import vn.trex.user.manager.data.model.User
import vn.trex.user.manager.di.koinViewModel
import vn.trex.user.manager.ui.components.PreviewInfoItem
import vn.trex.user.manager.ui.components.UserItem

class DetailScreen(val user: User) : Screen, KoinComponent {
  @Composable
  override fun Content() {
    MaterialTheme {
      val navigator = LocalNavigator.currentOrThrow
      val uriHandler = LocalUriHandler.current
      val detailViewModel =
        koinViewModel<DetailViewModel>(key = user.userName) { parametersOf(user) }
      val item by detailViewModel.item.collectAsState()

      Scaffold(
        topBar = {
          TopAppBar(
            title = { Text(text = "Users Details") },
            backgroundColor = Color.White,
            navigationIcon = {
              IconButton(onClick = { navigator.pop() }) {
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                  contentDescription = null,
                )
              }
            }
          )
        }
      ) {
        Column(modifier = Modifier.padding(all = 8.dp)) {
          UserItem(onClick = {}, user = item)
          Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
              .padding(8.dp)
          ) {
            PreviewInfoItem(
              iconVector = Icons.Default.LocationOn,
              description = item.location
            )
            PreviewInfoItem(
              iconVector = Icons.Default.Face,
              description = item.followers.toString(),
              subDescription = "Follower"
            )
            PreviewInfoItem(
              iconVector = Icons.Default.Person,
              description = item.following.toString(),
              subDescription = "Following"
            )
          }

          item.blog.let {
            Text(
              text = "Blog",
              fontSize = 28.sp,
              fontWeight = FontWeight.SemiBold,
              modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
            Button(
              onClick = { uriHandler.openUri(item.blog) },
              contentPadding = PaddingValues(0.dp),
              colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
              modifier = Modifier.padding(horizontal = 8.dp)
            ) {
              Text(
                text = item.blog,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
              )
            }
          }
        }
      }
    }
  }
}