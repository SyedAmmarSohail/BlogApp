package com.structure.core_ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable

@Composable
fun <T> ComposeVerticalList(
    list: List<T>,
//    state: LazyListState,
    item: @Composable (T, Int) -> Unit,
) {
    LazyColumn(/*state = state*/) {
        itemsIndexed(list) { index, item ->
            item(item, index)
        }
    }
}