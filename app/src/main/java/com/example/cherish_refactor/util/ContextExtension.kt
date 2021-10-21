package com.example.cherish_refactor.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object ContextExtension {
    // 앱이 설치되었는지?
    fun Context.isInstalledApp(packageName: String): Boolean {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        return intent != null
    }

    fun Context.moveMarket(packageName: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=$packageName")
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "마켓을 열지 못했습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    inline fun <reified T : Any> Context.getIntent() = Intent(this, T::class.java)
}