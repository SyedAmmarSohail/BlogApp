package com.structure.onboarding_presentation.onboarding.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.structure.core_ui.DarkGray
import com.structure.core_ui.fontSize
import com.structure.core_ui.spacing

@Composable
fun OnBoardingButtons(
    navController: NavController,
    skipButtonName: String,
    nextButtonName: String,
    fontFamily: FontFamily,
    buttonColor: Color = DarkGray,
    skipTo: String,
    nextOnClick: () -> Unit,
    arrowDrawableId: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = MaterialTheme.spacing.view_6x, vertical = MaterialTheme.spacing.view_6x),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = skipButtonName,
            Modifier
                .clickable {
                    navController.popBackStack()
                    navController.navigate(skipTo)
                },
            color = buttonColor,
            fontFamily = fontFamily,
            fontSize = MaterialTheme.fontSize.view_16x,
            fontWeight = FontWeight.SemiBold
        )
        Row(modifier = Modifier.wrapContentSize()) {
            Text(
                text = nextButtonName,
                Modifier.clickable {
                    nextOnClick()
                },
                color = buttonColor,
                fontFamily = fontFamily,
                fontSize = MaterialTheme.fontSize.view_16x,
                fontWeight = FontWeight.SemiBold
            )
            if (arrowDrawableId != 0) {
                Image(
                    painter = painterResource(id = arrowDrawableId),
                    contentDescription = "nextArrow",
                    colorFilter = ColorFilter.tint(buttonColor)
                )
            }

        }

    }

}
