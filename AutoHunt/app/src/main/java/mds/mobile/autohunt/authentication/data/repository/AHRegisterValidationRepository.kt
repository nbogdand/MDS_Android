package mds.mobile.autohunt.authentication.data.repository

import androidx.lifecycle.MutableLiveData
import mds.mobile.autohunt.authentication.data.models.AHRegisterAPIForm
import mds.mobile.autohunt.utils.isValidEmail
import mds.mobile.autohunt.utils.isValidName
import mds.mobile.autohunt.utils.isValidPassword
import mds.mobile.autohunt.utils.validate

class AHRegisterValidationRepository {

    var emailError = MutableLiveData<String>("")
    var passwordError = MutableLiveData<String>("")
    var nameError = MutableLiveData<String>("")

    private fun configureFormErrors(
        registerForm: AHRegisterAPIForm
    ) {
        emailError.value = registerForm.email.validate(String::isValidEmail, "Invalid email format") ?: ""
        passwordError.value = registerForm.password.validate(String::isValidPassword, "Invalid password") ?: ""
        nameError.value = registerForm.name.validate(String::isValidName, "Invalid name format") ?: ""
    }

    fun isValidForm(registerForm: AHRegisterAPIForm): Boolean {
        configureFormErrors(registerForm)

        return emailError.value.isNullOrEmpty() &&
                passwordError.value.isNullOrEmpty() &&
                nameError.value.isNullOrEmpty()
    }
}