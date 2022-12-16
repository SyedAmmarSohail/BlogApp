package com.structure.blog_presentation.blog_detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import com.structure.blog_domain.model.BlogModel
import com.structure.core.R
import com.structure.core.extensions.openURL
import com.structure.core_ui.*

@Composable
fun BlogDetailScreen(
    blog: BlogModel,
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = rememberImagePainter(
                    data = blog.imageUrl,
                    builder = {
                        crossfade(true)
                        error(R.drawable.ic_logo)
                        fallback(R.drawable.ic_logo)
                        placeholder(R.drawable.ic_logo)
                    }
                ),
                contentDescription = "cardDetailImage",
                modifier = Modifier.aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "backIcon",
                modifier = Modifier
                    .clickable { onNavigateUp() }
                    .padding(MaterialTheme.spacing.view_4x)
            )

            Text(
                text = "Read Complete Article",
                style = MaterialTheme.typography.caption,
                color = DarkGray,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    context.openURL(blog.link)
                }
                    .padding(MaterialTheme.spacing.view_2x)
                    .clip(
                        RoundedCornerShape(MaterialTheme.spacing.view_2x)
                    )
                    .background(White)
                    .padding(MaterialTheme.spacing.view_2x)
                    .align(Alignment.BottomCenter)
            )
        }
        Box(modifier = Modifier.padding(MaterialTheme.spacing.view_4x)) {
            Column {
                Text(
                    text = blog.title,
                    style = MaterialTheme.typography.h4,
                    color = DarkGray,
                    fontWeight = FontWeight.Bold
                )
                spacerHeight(height = MaterialTheme.spacing.view_1x)
                Text(text = blog.description, style = MaterialTheme.typography.body1, color = Gray)
            }

        }
    }
}