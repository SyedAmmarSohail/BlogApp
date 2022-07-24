package com.structure.blog_presentation.blog_overview

import androidx.annotation.DrawableRes
import com.structure.blog_presentation.R

data class Blog(
    val title : String,
    val description : String,
    val date : String,
    @DrawableRes val image : Int
)

val blogList = listOf<Blog>(
    Blog(
        "Create UI by Jetpack Compose—Part 1",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        "Jan 2 . 8 min read",
        R.drawable.white_ninja
    ),
    Blog(
        "Nested LazyVerticalGrid with Jetpack Compose",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        "Jan 2 . 8 min read",
        R.drawable.chocolate
    )
)
