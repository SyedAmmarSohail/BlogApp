package com.structure.core_ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.structure.core_ui.DarkGray
import com.structure.core_ui.R
import com.structure.core_ui.White

@Composable
fun ActionAppBar(
    title: String,
    actionVector: ImageVector? = null,
    onBack: () -> Unit,
    action: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        content = {
            IconButton(
                onClick = onBack
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = DarkGray)
            }
            Text(
                text = title,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.h4,
                color = DarkGray,
                fontWeight = FontWeight.Bold
            )
            actionVector?.let {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier.align(alignment = Alignment.CenterEnd),
                        onClick = action
                    ) {
                        Icon(imageVector = actionVector, contentDescription = "")
                    }
                }
            }


        },
        verticalAlignment = Alignment.CenterVertically
    )
}