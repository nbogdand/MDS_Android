package mds.mobile.autohunt.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mds.mobile.autohunt.R
import mds.mobile.autohunt.utils.AHConstants
import java.util.concurrent.TimeUnit

class AHSplashScreenActivity : AppCompatActivity() {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        goToApp()
    }

    private fun goToApp() {
        val disposable = Observable.just { startSession() }
            .delay(AHConstants.SPLASH_SCREEN_TIME, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                startSession()
            }

        compositeDisposable.add(disposable)
    }

    private fun startSession() {
        val intent = Intent(this, AHHomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.dispose()
    }
}
