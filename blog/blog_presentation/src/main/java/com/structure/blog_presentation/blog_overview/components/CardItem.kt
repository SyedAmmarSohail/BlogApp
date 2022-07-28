package com.structure.blog_presentation.blog_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_presentation.R
import com.structure.blog_presentation.blog_overview.Blog
import com.structure.core_ui.DarkGray
import com.structure.core_ui.Gray

@Composable
fun CardItem(
    blog: BlogModel,
    onClick: () -> Unit
) {

    Box(
        Modifier
            .clickable { onClick() }
            .padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.Start) {
//            Image(
//                painterResource(blog.imageUrl),
//                contentDescription = "cardItemImage",
//                modifier = Modifier.size(120.dp)
//            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Center
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
}