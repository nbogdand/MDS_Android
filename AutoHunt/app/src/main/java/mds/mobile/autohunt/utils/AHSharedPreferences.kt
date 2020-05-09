package mds.mobile.autohunt.utils

import android.content.Context

object AHSharedPreferences {
    private const val KEY_FCM_TOKEN = "KEY_FCM_TOKEN"

    fun saveFCM(fcm: String) {
        val editor = AHApplicationController.instance.getSharedPreferences(
            AHApplicationController.instance.packageName, Context.MODE_PRIVATE
        ).edit()
        editor.putString(KEY_FCM_TOKEN, fcm)
        editor.apply()
    }

    fun getFCM(fcm: String): String?{
        val sharedPref = AHApplicationController.instance.getSharedPreferences(
            AHApplicationController.instance.packageName, Context.MODE_PRIVATE
        )
        return sharedPref.getString(KEY_FCM_TOKEN, null)
    }
}