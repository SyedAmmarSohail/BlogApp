package com.structure.blog_presentation.blog_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_presentation.R
import com.structure.core_ui.*
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
                .padding(vertical = MaterialTheme.spacing.view_4x)
                .semantics {
                    contentDescription = "cardItem"
                },
            ) {
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
                            placeholder(com.structure.core.R.drawable.ic_logo)
                        }
                    ),
                    contentDescription = "cardItemImage",
                    modifier = Modifier.size(MaterialTheme.spacing.view_30x)
                )
                spacerWidth(width = MaterialTheme.spacing.view_2x)
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
                    spacerHeight(height = MaterialTheme.spacing.view_1x)
                    Text(text = blog.date, style = MaterialTheme.typography.caption, color = Gray)
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
            .padding(vertical = MaterialTheme.spacing.view_4x)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImagePlaceHolder(
                size = MaterialTheme.spacing.view_30x
            )
            spacerWidth(width = MaterialTheme.spacing.view_2x)
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                LinePlaceHolder(
                    width = MaterialTheme.spacing.view_50x
                )
                spacerHeight(height = MaterialTheme.spacing.view_1x)
                LinePlaceHolder(width = MaterialTheme.spacing.view_25x)
            }
        }

    }
}