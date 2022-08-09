package com.structure.core.domain.preferences

interface Preferences {

    fun saveShouldShowOnboarding(shouldShow: Boolean)
    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }
}