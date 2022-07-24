package com.example.onboarding.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun TitleAndContent(title: String, description: String, titleColor: Color,
                                   descriptionColor: Color, titleFontSize: TextUnit,
                                   descriptionFontSize: TextUnit, titleFontFamily: FontFamily,
                                   descriptionFontFamily: FontFamily
) {
    Column(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(0.dp,36.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title, color = titleColor,
            fontFamily = titleFontFamily,
            fontSize = titleFontSize,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(0.dp, 24.dp, 0.dp, 0.dp),
            text = description, color = descriptionColor,
            fontFamily = descriptionFontFamily,
            fontSize = descriptionFontSize,
            textAlign = TextAlign.Center
        )
    }


}