package com.structure.onboarding_presentation.onboarding.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.structure.core_ui.spacing

@Composable
fun OnBoardingScreenImage(imageId: Int, contentScale: ContentScale) {
    Row(
        modifier = Modifier
            .wrapContentSize(align = TopCenter)
            .padding(horizontal = MaterialTheme.spacing.view_12x, vertical = MaterialTheme.spacing.view_4x)
    ) {
        if (imageId != 0) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "OnBoardingImage",
                contentScale = contentScale
            )

        }
    }
}