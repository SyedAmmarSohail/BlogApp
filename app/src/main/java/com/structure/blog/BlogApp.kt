package com.structure.blog

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BlogApp: Application(){
    override fun onCreate() {
        appContext = this
        super.onCreate()
    }
    companion object{
        lateinit var appContext: Application
    }
}