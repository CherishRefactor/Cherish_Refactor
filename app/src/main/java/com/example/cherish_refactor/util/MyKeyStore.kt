package com.example.cherish_refactor.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object MyKeyStore {
    private const val alarmKeyName = "cherishAlarm"
    private const val encryptedSharedPreferencesName = "encryptedSharedPrefs"
    private const val loginTokenPrefsName = "cherishLoginToken"
    private const val loginUserId = "cherishUserId"
    private const val loginUserPassword = "cherishUserPassword"
    private const val loginUserNickname = "cherishUserNickname"
    private const val isSingleInvokeKey = "singleInvoke"
    private const val FCMTokenKey = "FCMToken"
    private const val NEED_TO_WATERING_NOTIFICATION_ID = 0
    private const val RECALL_REVIEW_NOTIFICATION_ID = 1


    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            context.packageName,
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

     fun setAlarmKey(alarmKey: Boolean) {
        sharedPreferences.edit().putBoolean(alarmKeyName, alarmKey).apply()
    }

     fun getAlarmKey(): Boolean =
        sharedPreferences.getBoolean(alarmKeyName, true)


     fun setToken(token: String) {
        sharedPreferences.edit().putString(loginTokenPrefsName, token).apply()
    }

     fun getToken(): String? = sharedPreferences.getString(loginTokenPrefsName, null)

     fun setUserId(userId: Int) {
        sharedPreferences.edit().putInt(loginUserId, userId).apply()
    }

     fun getUserId(): Int? = sharedPreferences.getInt(loginUserId, -1)

     fun setUserPassword(userPassword: String) {
        sharedPreferences.edit().putString(loginUserPassword.toString(), userPassword).apply()
    }

     fun getUserPassword(): String? = sharedPreferences.getString(loginUserPassword, null)

     fun setUserNickname(userNickname: String) {
        sharedPreferences.edit().putString(loginUserNickname, userNickname).apply()
    }

     fun getUserNickname(): String? = sharedPreferences.getString(loginUserNickname, null)

     fun deleteUserId() {
        sharedPreferences.edit().remove(loginUserId).apply()
    }

     fun deleteUserPassword() {
        sharedPreferences.edit().remove(loginUserPassword).apply()
    }

     fun deleteToken() {
        sharedPreferences.edit().remove(loginTokenPrefsName).apply()
    }

     fun isSingleInvoke(): Boolean = sharedPreferences.getBoolean(isSingleInvokeKey, false)


     fun singleInvoked() {
        sharedPreferences.edit().putBoolean(isSingleInvokeKey, true).apply()
    }

     fun setFCMToken(token: String) {
        sharedPreferences.edit().putString(FCMTokenKey, token).apply()
    }

     fun getFCMToken(): String? = sharedPreferences.getString(FCMTokenKey, null)


}