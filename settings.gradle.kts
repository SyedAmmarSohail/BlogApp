pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version "8.7.3"
        id("com.android.library") version "8.7.3"
        id("org.jetbrains.kotlin.android") version "1.9.23"
        id("org.jetbrains.kotlin.jvm") version "1.9.23"
        id("com.google.dagger.hilt.android") version "2.54"
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BlogApp"
include(":app")
include(":core")
include(":core-ui")
include(":blog:blog_data")
include(":blog:blog_domain")
include(":blog:blog_presentation")
include(":onboarding:onboarding_presentation")
include(":profile:profile_presentation")
include(":profile:profile_domain") 