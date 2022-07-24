package com.structure.blog_presentation.blog_overview

import androidx.compose.runtime.Composable
import com.structure.blog_presentation.R

typealias ComposableFun = @Composable ((Blog) -> Unit) -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Feature : TabItem("Feature", { BlogListScreen(it)  })
    object Latest : TabItem("Latest", { BlogListScreen(it) })
    object Trending : TabItem("Trending", { BlogListScreen(it) })
}