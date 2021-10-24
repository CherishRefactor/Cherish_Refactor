package com.example.cherish_refactor.util

import android.content.Context
import android.content.SharedPreferences

object MySharedPreference {
    private val MY_ACCOUNT : String = "account"
    private val PREF_IS_FIRST_ENTER :String ="isFirst"

    fun setUserId(context: Context, input: String) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_ID", input)
        editor.commit()
    }

    fun getUserId(context: Context): String {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString("MY_ID", "").toString()
    }

    fun setUserPass(context: Context, input: String) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_PASS", input)
        editor.commit()
    }

    fun getUserPass(context: Context): String {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString("MY_PASS", "").toString()
    }

    fun clearUser(context: Context) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.commit()
    }

    fun saveFirstEnter(context: Context) {
        val prefs : SharedPreferences = context.getSharedPreferences(PREF_IS_FIRST_ENTER, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(PREF_IS_FIRST_ENTER, false)
            .apply()
    }

    fun isFirstEnter(context: Context): Boolean {
        val prefs : SharedPreferences = context.getSharedPreferences(PREF_IS_FIRST_ENTER, Context.MODE_PRIVATE)
        return prefs.getBoolean(PREF_IS_FIRST_ENTER, true)
    }
}