package com.structure.blog_presentation.blog_overview

import androidx.compose.runtime.Composable
import com.structure.blog_domain.model.BlogModel
import com.structure.blog_presentation.R
import com.structure.core.domain.model.BlogType

typealias ComposableFun = @Composable (blogOverviewState: BlogOverviewState, (BlogModel) -> Unit) -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Feature : TabItem(BlogType.FEATURED.value, { state, callBack ->
        BlogListScreen(state = state, onNavigateToDetail = callBack)
    })

    object Latest : TabItem(BlogType.LATEST.value, { state, callBack ->
        BlogListScreen(state = state, onNavigateToDetail = callBack)
    })

    object Trending : TabItem(BlogType.TRENDING.value, { state, callBack ->
        BlogListScreen(state = state, onNavigateToDetail = callBack)
    })
}