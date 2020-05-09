package mds.mobile.autohunt.utils

import android.app.Application
import com.google.firebase.FirebaseApp

class AHApplicationController: Application() {
    companion object{
        lateinit var instance: AHApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        FirebaseApp.initializeApp(this)
    }
}