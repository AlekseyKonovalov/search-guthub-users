package ru.alekseyk.testskblab.data.prefs

import android.content.Context
import ru.alekseyk.testskblab.data.ext.putPrimitiveValue

private const val PREFS_APP = "app_prefs"
private const val PREF_USER_DATA = "user_data"

class AppPrefs(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFS_APP, Context.MODE_PRIVATE)

    fun clearPrefs() {
        sharedPreferences.edit().clear().apply()
    }

    fun saveCurrentUser(token: String) {
        sharedPreferences.putPrimitiveValue(PREF_USER_DATA, token)
    }

    fun getCurrentUser(): String? {
        return sharedPreferences.getString(PREF_USER_DATA, "")
    }

    fun removeCurrentUser() {
        sharedPreferences.edit().remove(PREF_USER_DATA).apply()
    }

}