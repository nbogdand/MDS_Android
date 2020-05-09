package mds.mobile.autohunt.authentication.viewModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mds.mobile.autohunt.BuildConfig
import mds.mobile.autohunt.authentication.data.models.AHRegisterAPIForm
import mds.mobile.autohunt.authentication.data.repository.AHAuthAPIRepository
import mds.mobile.autohunt.authentication.data.repository.AHRegisterValidationRepository
import mds.mobile.autohunt.globalUser.AHCurrentUser
import mds.mobile.autohunt.globalUser.AHUser
import mds.mobile.autohunt.utils.isValidPassword

class AHRegisterFragmentViewModel
    : ViewModel() {

    var goToLogin: (() -> Unit)? = null
    var onRegisterSuccess: (() -> Unit)? = null

    var email = ObservableField("")
    var name = ObservableField("")
    var password = ObservableField("")
    var confirmPassword = ObservableField("")
    var error = ObservableField("")
    var registerEnabled = ObservableField(false)
    private val validationRepository = AHRegisterValidationRepository()

    init {
        if(BuildConfig.DEBUG){
            email.set("nbogdand@yahoo.com")
            name.set("Bogdan")
            password.set("password")
            confirmPassword.set("password")
        }
    }

    fun goToLogin() {
        goToLogin?.invoke()
    }

    fun updateRegisterCapability() {
        error.set("")

        registerEnabled.set(
            email.get()?.isNotEmpty() ?: false &&
                    password.get()?.isNotBlank() ?: false &&
                    name.get()?.isNotEmpty() ?: false
        )
    }

    fun fireRegisterUser() {
        val registerForm = AHRegisterAPIForm(
            email = email.get() ?: "",
            name = name.get() ?: "",
            password = password.get() ?: ""
        )

        if (password.get() != confirmPassword.get()) {
            error.set("Passwords don't match")
        }

        if (!validationRepository.isValidForm(registerForm)) {
            error.set("Invalid form. Please recheck")
            registerEnabled.set(false)
            return
        }

//        this._registerForm.value = Event(registerForm)
        doRegister(registerForm)
    }

    @SuppressLint("LogNotTimber")
    private fun doRegister(
        registerForm: AHRegisterAPIForm
    ) = AHAuthAPIRepository
        .registerUser(
            email = registerForm.email,
            name = registerForm.name,
            password = registerForm.password
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe( { user: AHUser? ->
            AHCurrentUser.user = user
            onRegisterSuccess?.invoke()
            Log.e("", "Register user success ${user.toString()}")
        }
        , {
            error.set(it.message)
            Log.e("tag", "Register user failed: ${it.message}")
        })
}