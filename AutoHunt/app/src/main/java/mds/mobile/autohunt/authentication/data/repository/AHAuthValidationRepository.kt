package mds.mobile.autohunt.authentication.data.repository

import androidx.lifecycle.MutableLiveData
import mds.mobile.autohunt.authentication.data.models.AHLoginAPIForm
import mds.mobile.autohunt.utils.isValidNameOrUsername
import mds.mobile.autohunt.utils.isValidPassword
import mds.mobile.autohunt.utils.validate
import okhttp3.internal.notifyAll

class AHAuthValidationRepository {

    var emailOrUsernameError = MutableLiveData<String>()
    var passwordError = MutableLiveData<String>()

    private fun configureFormErrors(
        loginForm: AHLoginAPIForm
    ) {
        emailOrUsernameError.value = loginForm.email.validate(String::isValidNameOrUsername, "Invalid email/username")
        passwordError.value = loginForm.password.validate(String::isValidPassword, "Invalid password")
    }

    fun isValidForm(loginForm: AHLoginAPIForm): Boolean {
        configureFormErrors(loginForm)

        return emailOrUsernameError.value.isNullOrEmpty() &&
                passwordError.value.isNullOrEmpty()
    }
}