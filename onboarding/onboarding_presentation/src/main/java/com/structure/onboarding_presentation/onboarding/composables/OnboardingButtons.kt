package com.example.onboarding.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun OnboardingButtons(
    navController: NavController,
    skipButtonName: String,
    nextButtonName: String,
    fontFamily: FontFamily,
    buttonColor: Color,
    skipTo: String,
    nextOnClick: () -> Unit,
    arrowDrawableId: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp, 16.dp, 24.dp, 48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = skipButtonName,
            Modifier.clickable {
                navController.popBackStack()
                navController.navigate(skipTo)
            },
            color = buttonColor,
            fontFamily = fontFamily,
            fontSize = 18.sp
        )
        Row(modifier = Modifier.wrapContentSize()) {
            Text(
                text = nextButtonName,
                Modifier.clickable {

                    nextOnClick()
                },
                color = buttonColor,
                fontFamily = fontFamily,
                fontSize = 18.sp
            )
            if(arrowDrawableId != 0) {
                Image(
                    painter = painterResource(id = arrowDrawableId),
                    contentDescription = "next arrow",
                    colorFilter = ColorFilter.tint(buttonColor)
                )
            }

        }

    }

}
