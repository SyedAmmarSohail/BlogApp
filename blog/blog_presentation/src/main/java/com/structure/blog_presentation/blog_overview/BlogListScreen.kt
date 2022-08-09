package com.structure.blog_presentation.blog_overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_presentation.blog_overview.components.CardItem
import com.structure.core_ui.White
import com.structure.core_ui.component.ComposeVerticalList
import java.util.*


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
        val filterList = if (state.searchKey.isEmpty()) {
            state.blogs
        } else state.blogs.filter {
            it.title.lowercase(
                Locale.getDefault()
            ).contains(state.searchKey.lowercase(Locale.getDefault()))
        }

        ComposeVerticalList(list = filterList) { item, index ->
            CardItem(state.isSearching, item) {
                onNavigateToDetail(item)
            }
        }
    }
}