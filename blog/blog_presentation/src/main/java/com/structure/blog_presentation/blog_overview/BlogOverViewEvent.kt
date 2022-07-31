package com.structure.blog_presentation.blog_overview

sealed class BlogOverViewEvent{
    object OnMarkFavorite : BlogOverViewEvent()
    data class OnTabClick(val title : String) : BlogOverViewEvent()
    data class OnSearchKey(val key : String) : BlogOverViewEvent()
    data class OnSearchFocusedChanged(val isFocused : Boolean) : BlogOverViewEvent()
}
