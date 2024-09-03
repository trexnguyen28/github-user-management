package vn.trex.user.manager.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreviewInfoItem(
  iconVector: ImageVector,
  description: String? = null,
  subDescription: String? = null
) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Icon(
      imageVector = iconVector,
      contentDescription = null,
      modifier = Modifier
        .padding(8.dp)
        .size(46.dp)
        .clip(CircleShape)
        .background(Color.LightGray)
        .padding(8.dp)
    )
    Text(
      text = description ?: "-",
      fontSize = 18.sp,
      fontWeight = FontWeight.Normal,
    )
    subDescription?.let {
      Text(
        text = subDescription,
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
      )
    }
  }
}