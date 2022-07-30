package com.structure.blog_presentation.blog_overview

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_presentation.R
import com.structure.blog_presentation.blog_overview.components.CardItem
import com.structure.core_ui.DarkGreen
import com.structure.core_ui.White
import com.structure.core_ui.component.ComposeVerticalList


@Composable
fun BlogListScreen(
    state: BlogOverviewState,
    onNavigateToDetail: (BlogModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        ComposeVerticalList(list = state.blogs/*, state = state*/) { item, index ->
            CardItem(state.isSearching, item) {
                onNavigateToDetail(item)
            }
        }
    }
}