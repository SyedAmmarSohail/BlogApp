package com.structure.blog_presentation.blog_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_presentation.blog_overview.Blog
import com.structure.core.R
import com.structure.core_ui.DarkGray
import com.structure.core_ui.Gray

@Composable
fun BlogDetailScreen(
    blog: BlogModel,
    onNavigateUp: () -> Unit,
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box(/*modifier = Modifier.weight(1.5f)*/) {
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
                    .padding(16.dp)
            )
        }
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                Text(
                    text = blog.title,
                    style = MaterialTheme.typography.h4,
                    color = DarkGray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = blog.description, style = MaterialTheme.typography.body1, color = Gray)
            }

        }
    }
}