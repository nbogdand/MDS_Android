package mds.mobile.autohunt.authentication.viewModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mds.mobile.autohunt.BuildConfig
import mds.mobile.autohunt.authentication.data.models.AHLoginAPIForm
import mds.mobile.autohunt.authentication.data.repository.AHAuthAPIRepository
import mds.mobile.autohunt.authentication.data.repository.AHAuthValidationRepository
import mds.mobile.autohunt.globalUser.AHCurrentUser

class AHLoginFragmentViewModel : ViewModel() {

    var goToRegister: (() -> Unit)? = null
    var onLoginSuccess: (() -> Unit)? = null

    var email = ObservableField("")
    var password = ObservableField("")
    var error = ObservableField("")
    var loginEnabled = ObservableField(false)
    private val authValidationRepository = AHAuthValidationRepository()

    init {
        if(BuildConfig.DEBUG) {
            email.set("nbogdand@yahoo.com")
            password.set("Test1234")
        }
    }

    fun goToRegister() {
        goToRegister?.invoke()
    }

    fun fireLoginUser() {
        val loginForm = AHLoginAPIForm(
            email = email.get() ?: "",
            password = password.get() ?: ""
        )

        if (!authValidationRepository.isValidForm(loginForm)) {
            error.set("Invalid format")
            loginEnabled.set(false)
            return
        }

        doLogin(loginForm)
    }

    fun updateLoginCapability() {
        error.set("")
        loginEnabled.set(
            email.get()?.isNotEmpty() ?: false &&
                    password.get()?.isNotEmpty() ?: false)
    }

    @SuppressLint("LogNotTimber")
    private fun doLogin(
        loginForm: AHLoginAPIForm
    ) {
        val disposable = AHAuthAPIRepository.loginUser(
            email = loginForm.email,
            password = loginForm.password
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { user ->
                AHCurrentUser.user = user
                onLoginSuccess?.invoke()
                Log.e("tag", "Login user success: ${user.toString()}")
            }
            ,{
                Log.e("tag", "Login user failed ${it.message}")
            }
            )
    }

    override fun onCleared() {
        super.onCleared()
    }
}