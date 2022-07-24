package com.example.onboarding.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreenImage(imageId: Int, contentScale: ContentScale) {
    Row(
        modifier = Modifier
            .wrapContentSize(align = TopCenter)
            .padding(48.dp, 48.dp, 48.dp, 16.dp)
    ) {
        if (imageId != 0) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Onboarding image",
                contentScale = contentScale
            )

        }
    }
}