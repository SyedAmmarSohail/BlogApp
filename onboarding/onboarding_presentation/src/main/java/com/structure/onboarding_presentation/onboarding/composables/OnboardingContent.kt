package com.structure.onboarding_presentation.onboarding.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.structure.core_ui.spacing

@Composable
fun TitleAndContent(
    title: String, description: String, titleColor: Color,
    descriptionColor: Color, titleFontSize: TextUnit,
    descriptionFontSize: TextUnit, titleFontFamily: FontFamily,
    descriptionFontFamily: FontFamily
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.view_9x),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title, color = titleColor,
            fontFamily = titleFontFamily,
            fontSize = titleFontSize,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.padding(top = MaterialTheme.spacing.view_6x),
            text = description, color = descriptionColor,
            fontFamily = descriptionFontFamily,
            fontSize = descriptionFontSize,
            textAlign = TextAlign.Center
        )
    }


}