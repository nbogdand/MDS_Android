package mds.mobile.autohunt.utils

import android.util.Patterns


fun String.isValidNameOrUsername(): Boolean = this.trim().isNotEmpty()

fun String.isValidName(): Boolean = this.isNotEmpty() &&
        this.length <= AHConstants.ValidationRules.NAME_MAX_CHARACTERS

fun String.isValidUsername(): Boolean = this.isNotEmpty() &&
        this.length >= AHConstants.ValidationRules.USERNAME_MIN_CHARACTERS &&
        this.length <= AHConstants.ValidationRules.USERNAME_MAX_CHARACTERS &&
        this.matches(Regex("^[a-zA-Z0-9_.-]*\$"))

fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword(): Boolean = this.isNotEmpty() &&
        AHConstants.Patterns.passwordRegex.matcher(this).matches() &&
        !AHConstants.Patterns.spaceRegex.matcher(this).matches()

fun String.isValidNumber(): Boolean = this.isNotEmpty() &&
        AHConstants.Patterns.numberPattern.matcher(this).matches()

fun String.isValidUrl(): Boolean = this.isNotEmpty() &&
        Patterns.WEB_URL.matcher(this).matches()

fun String.validate(validator: (String) -> Boolean, message: String): String? {
    return if (validator(this.trim())) {
        null
    } else {
        message
    }
}

fun String.isValidField(): Boolean = this.isNotEmpty()
