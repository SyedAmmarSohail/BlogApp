package com.structure.blog_presentation.blog_overview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.structure.core_ui.LocalSpacing
import com.structure.blog_presentation.R
import com.structure.core_ui.Gray

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.search),
    shouldShowHint: Boolean = false,
    iconTintColor: Color = Gray,
    onFocusChanged: (FocusState) -> Unit
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onSearch,
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = iconTintColor
            )
        }
        Box {
            BasicTextField(
                value = text,
                textStyle = TextStyle(color = iconTintColor),
                onValueChange = onValueChange,
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch()
                        defaultKeyboardAction(ImeAction.Search)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                ),
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .onFocusChanged { onFocusChanged(it) }
                    .testTag("searchField")
            )
            if (shouldShowHint) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Light,
                    color = iconTintColor,
                    modifier = Modifier
                        .padding(start = spacing.view_4x)
                )
            }
        }
    }
}