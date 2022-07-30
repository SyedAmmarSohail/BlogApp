package com.structure.blog_presentation.blog_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_presentation.R
import com.structure.blog_presentation.blog_overview.Blog
import com.structure.core_ui.DarkGray
import com.structure.core_ui.Gray
import com.structure.core_ui.component.ImagePlaceHolder
import com.structure.core_ui.component.LinePlaceHolder

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CardItem(
    isLoading: Boolean,
    blog: BlogModel,
    onClick: () -> Unit
) {
    if (!isLoading)
        Box(
            Modifier
                .clickable { onClick() }
                .padding(vertical = 16.dp)) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = blog.imageUrl,
                        builder = {
                            crossfade(true)
                            error(com.structure.core.R.drawable.ic_logo)
                            fallback(com.structure.core.R.drawable.ic_logo)
                            placeholder(R.drawable.ic_logo)
                        }
                    ),
                    contentDescription = "cardItemImage",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = blog.title,
                        style = MaterialTheme.typography.h4,
                        color = DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = blog.date, style = MaterialTheme.typography.body1, color = Gray)
                }
            }

        }
    else
        PlaceholderCardItem()

}

@Composable
fun PlaceholderCardItem() {
    Box(
        Modifier
            .padding(vertical = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImagePlaceHolder(
                size = 120.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                LinePlaceHolder(
                    width = 200.dp
                )
                Spacer(modifier = Modifier.height(4.dp))
                LinePlaceHolder(width = 100.dp)
            }
        }

    }
}