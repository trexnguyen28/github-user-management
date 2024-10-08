package vn.trex.user.manager.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import vn.trex.user.manager.data.model.User

@Composable
fun UserItem(
  user: User,
  onClick: () -> Unit,
) {
  val size = 64.dp

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .padding(all = 8.dp),
    elevation = 4.dp,
    shape = RoundedCornerShape(16.dp),
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)
        .padding(all = 8.dp)
    ) {
      AsyncImage(
        model = user.avatarUrl,
        modifier = Modifier
          .height(size)
          .width(size)
          .clip(CircleShape),
        contentDescription = null
      )
      Column(
        modifier = Modifier.padding(start = 8.dp)
      ) {
        Text(text = user.userName, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = user.htmlUrl, fontStyle = FontStyle.Italic, fontSize = 14.sp)
      }
    }
  }
}
