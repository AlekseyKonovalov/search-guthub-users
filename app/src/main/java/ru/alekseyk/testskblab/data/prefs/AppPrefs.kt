package ru.alekseyk.testskblab.data.prefs

import android.content.Context
import ru.alekseyk.testskblab.data.ext.putPrimitiveValue

private const val PREFS_APP = "app_prefs"
private const val PREF_TOKEN = "token"

class AppPrefs(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFS_APP, Context.MODE_PRIVATE)

    fun clearPrefs() {
        sharedPreferences.edit().clear().apply()
    }

    fun saveToken(token: String) {
        sharedPreferences.putPrimitiveValue(PREF_TOKEN, token)
    }

    fun getToken(): String? {
        return sharedPreferences.getString(PREF_TOKEN, "")
    }

    fun removeToken() {
        sharedPreferences.edit().remove(PREF_TOKEN).apply()
    }

}