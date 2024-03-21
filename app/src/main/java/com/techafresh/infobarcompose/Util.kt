package com.techafresh.infobarcompose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radusalagean.infobarcompose.BaseInfoBarMessage


class CustomInfoBarMessageWithActionButton(
    val text : String?,
    val actionButton1Text : String?,
    val actionButton2Text: String?,
    val onActionButton1Clicked: () -> Unit,
    val onActionButton2Clicked: () -> Unit,
    override val containsControls: Boolean = true,
    override val displayTimeSeconds: Int? = 4
) : BaseInfoBarMessage()

val customInfoBarContentWithActionButton : @Composable (CustomInfoBarMessageWithActionButton) -> Unit = { message ->
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        message.text?.let { Text(text = it , modifier = Modifier.weight(1f), color = Color.White) }
//        Spacer(modifier = Modifier.width(8.dp))
        TextButton(onClick = message.onActionButton1Clicked) {
            message.actionButton1Text?.let { Text(text = it) }
        }
//        Spacer(modifier = Modifier.width(5.dp))
        TextButton(onClick = message.onActionButton2Clicked) {
            message.actionButton2Text?.let { Text(text = it) }
        }
    }
}


class CustomMessage(
    val textString: String,
    val icon: ImageVector,
    val iconColor: Color,
    val textColor: Color = Color.Unspecified,
    override val backgroundColor: Color? = null,
    override val displayTimeSeconds: Int? = 4,
    override val containsControls: Boolean = false
) : BaseInfoBarMessage()



val content: @Composable (CustomMessage) -> Unit = { message ->
    Row {
        Icon(
            modifier = Modifier.padding(8.dp).align(Alignment.CenterVertically),
            imageVector = message.icon,
            contentDescription = null,
            tint = message.iconColor
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = message.textString,
            color = message.textColor
        )
    }
}