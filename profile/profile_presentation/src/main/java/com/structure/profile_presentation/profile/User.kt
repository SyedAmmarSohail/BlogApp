package com.structure.profile_presentation.profile

import androidx.annotation.DrawableRes
import com.structure.core.R

data class User(
    val fullName: String,
    val email: String,
    val imageUrl: String,
    val socialLink: List<SocialLink> = emptyList(),
)

data class SocialLink(
    @DrawableRes
    val image: Int,
    val name: String,
    val url: String,
    val packageName: String,
    val profileAppLink: String
)

val dummyListSocialLink = listOf(
    SocialLink(
        image = R.drawable.linkedin,
        name = "Linkedin",
        url = "https://www.linkedin.com/in/ammar-sohail/",
        packageName = "com.linkedin.android",
        profileAppLink = "linkedin://profile/309883783"
    ),
    SocialLink(
        image = R.drawable.github,
        name = "Github",
        url = "https://github.com/SyedAmmarSohail",
        packageName = "com.github.android",
        profileAppLink = "github://profile/15625516"
    ),
    SocialLink(
        image = R.drawable.medium,
        name = "Medium",
        url = "https://medium.com/@ammarsohail321",
        packageName = "com.medium.reader",
        profileAppLink = "github://profile/@ammarsohail321"
    )
)

val dummyUser = User(
    fullName = "Syed Ammar",
    email = "ammarsohail321@gmail.com",
    imageUrl = "https://firebasestorage.googleapis.com/v0/b/blogapp-a96e5.appspot.com/o/ninja.jpg?alt=media&token=a0d15035-ad7b-44b1-b893-8a97d730cf49",
    socialLink = dummyListSocialLink
)
