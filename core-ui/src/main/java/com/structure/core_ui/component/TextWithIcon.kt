package com.structure.core_ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.structure.core_ui.DarkGray
import com.structure.core_ui.Gray
import com.structure.core_ui.spacerWidth
import com.structure.core_ui.spacing

@Composable
fun TextWithIcon(
    text: String,
    leadingIcon: Int? = null,
    trailingIcon: Int? = null,
    textStyle: TextStyle = MaterialTheme.typography.h5.copy(
        color = DarkGray,
        fontWeight = FontWeight.SemiBold
    ),
    onClick: () -> Unit
) {
    Row(modifier = Modifier
        .clickable { onClick() }
        .padding(vertical = MaterialTheme.spacing.view_3x)
        .fillMaxWidth()) {
        leadingIcon?.let {
            Image(painterResource(id = leadingIcon), contentDescription = "leadingImage", Modifier.size(MaterialTheme.spacing.view_6x))
            spacerWidth(width = MaterialTheme.spacing.view_2x)
        }
        Text(
            text = text, style = textStyle,
        )
        trailingIcon?.let {
            val vector = ImageVector.vectorResource(id = it)
            Spacer(modifier = Modifier.weight(1f))
            Icon(vector, contentDescription = "leadingIcon", tint = DarkGray)
        }
    }
}