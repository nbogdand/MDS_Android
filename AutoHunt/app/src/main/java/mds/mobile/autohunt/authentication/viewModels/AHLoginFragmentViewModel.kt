package mds.mobile.autohunt.authentication.viewModels

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mds.mobile.autohunt.BuildConfig
import mds.mobile.autohunt.authentication.data.models.AHLoginAPIForm
import mds.mobile.autohunt.authentication.data.repository.AHAuthAPIRepository
import mds.mobile.autohunt.authentication.data.repository.AHAuthValidationRepository
import mds.mobile.autohunt.globalUser.AHCurrentUser
import mds.mobile.autohunt.utils.AHApplicationController
import mds.mobile.autohunt.utils.AHCarModels
import mds.mobile.autohunt.utils.logErrorMessage
import mds.mobile.autohunt.utils.showToastMessage

class AHLoginFragmentViewModel : ViewModel() {

    var goToRegister: (() -> Unit)? = null
    var onLoginSuccess: (() -> Unit)? = null

    var email = ObservableField("")
    var password = ObservableField("")
    var error = ObservableField("")
    var loginEnabled = ObservableField(false)
    private val authValidationRepository = AHAuthValidationRepository()

    init {
        if (BuildConfig.DEBUG) {
            email.set("nbogdand@yahoo.com")
            password.set("password")
        }
    }

    fun goToRegister() {
        goToRegister?.invoke()
    }

    fun fireLoginUser() {
        val loginForm = AHLoginAPIForm(
            email = email.get() ?: "",
            password = password.get() ?: "",
            token = ""
        )

        if (!authValidationRepository.isValidForm(loginForm)) {
            error.set("Invalid format")
            loginEnabled.set(false)
            return
        }

        generateFCM(loginForm)
    }

    fun updateLoginCapability() {
        error.set("")
        loginEnabled.set(
            email.get()?.isNotEmpty() ?: false &&
                    password.get()?.isNotEmpty() ?: false
        )
    }

    private fun generateFCM(
        loginForm: AHLoginAPIForm
    ) {
        FirebaseApp.initializeApp(AHApplicationController.instance)
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            val fcmToken = task.result?.token
            "FCM TOKEN: $fcmToken".logErrorMessage()
            doLogin(loginForm, fcmToken)
        }
    }

    @SuppressLint("LogNotTimber")
    private fun doLogin(
        loginForm: AHLoginAPIForm,
        fcmToken: String?
    ) {
        val disposable = AHAuthAPIRepository.loginUser(
            email = loginForm.email,
            password = loginForm.password,
            token = fcmToken ?: ""
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    AHCurrentUser.user = response?.user
                    AHCarModels.carModels = response?.carModels
                    AHCarModels.carBrands = response?.brandTypes

                    onLoginSuccess?.invoke()
                    Log.e("tag", "Login user success: ${response?.user.toString()}")
                    "Login user success: ${response?.user?.email?.toString()}".showToastMessage()
                }, {
                    Log.e("tag", "Login user failed ${it.message}")
                    "Login user failed ${it.message}".showToastMessage()
                }
            )

    }

    override fun onCleared() {
        super.onCleared()
    }
}