package com.structure.core.data.preferences

import android.content.SharedPreferences
import com.structure.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow)
            .apply()
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPref.getBoolean(
            Preferences.KEY_SHOULD_SHOW_ONBOARDING,
            true
        )
    }
}