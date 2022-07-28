package com.structure.blog_presentation.blog_overview

sealed class BlogOverViewEvent{
    object onMarkFavorite : BlogOverViewEvent()
    data class onTabClick(val title : String) : BlogOverViewEvent()
}
