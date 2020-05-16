package mds.mobile.autohunt.utils

import android.app.Application
import com.google.firebase.FirebaseApp
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class AHApplicationController: Application() {
    companion object{
        lateinit var instance: AHApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        FirebaseApp.initializeApp(this)
        AppCenter.start(
            this, "f6930552-29b1-4d64-86da-706862420856",
            Analytics::class.java, Crashes::class.java
        )
    }
}