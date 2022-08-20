package com.structure.profile_presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import com.structure.core_ui.*
import com.structure.core_ui.component.ActionAppBar
import com.structure.core_ui.component.ComposeVerticalList
import com.structure.core_ui.component.TextWithIcon
import com.structure.core.R

@Composable
fun ProfileScreen(
    user: User = dummyUser,
    onNavigateUp: () -> Unit,
) {

    val context = LocalContext.current

    Column {
        ActionAppBar(title = context.getString(R.string.profile), onBack = {
            onNavigateUp()
        })
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.view_4x),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(
                    data = user.imageUrl,
                    builder = {
                        crossfade(true)
                        error(R.drawable.ic_logo)
                        fallback(R.drawable.ic_logo)
                        placeholder(R.drawable.ic_logo)
                    }
                ),
                contentDescription = "cardItemImage",
                modifier = Modifier
                    .size(MaterialTheme.spacing.view_30x)
                    .clip(CircleShape)
            )
            Text(
                text = user.fullName,
                style = MaterialTheme.typography.h4,
                color = DarkGray,
                fontWeight = FontWeight.Bold
            )
            spacerHeight(height = MaterialTheme.spacing.view_1x)
            Text(text = user.email, style = MaterialTheme.typography.caption, color = Gray)
            Divider(
                color = colorGray,
                thickness = MaterialTheme.spacing.view_1,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.view_6x)
            )
            ComposeVerticalList(list = user.socialLink) { item, index ->
                TextWithIcon(text = item.name, leadingIcon = item.image, trailingIcon = R.drawable.ic_right_arrow) {
                    item
                }
            }
        }
    }
}