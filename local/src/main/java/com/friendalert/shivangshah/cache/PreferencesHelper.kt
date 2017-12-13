package com.friendalert.shivangshah.cache

import android.content.Context
import android.content.SharedPreferences
import com.friendalert.shivangshah.cache.user.CachedUser
import javax.inject.Inject
import javax.inject.Singleton
import com.google.gson.Gson

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
@Singleton
class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private val PREF_BUFFER_PACKAGE_NAME = "com.friendalert.shivangshah.preferences"

        private val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val bufferPref: SharedPreferences

    init {
        bufferPref = context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = bufferPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = bufferPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

    fun getSharedPreferences(): SharedPreferences { return bufferPref }

}