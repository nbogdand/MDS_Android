package mds.mobile.autohunt.utils

import java.util.regex.Pattern

object AHConstants {
    const val SPLASH_SCREEN_TIME = 3L
    const val BASE_API_URL = "https://029b6acd.ngrok.io/"

    object Keys {
        const val FRAGMENT_OBJECT_ID = "FRAGMENT_OBJECT_ID"
    }

    object FragmentTags {
        const val TAG_FRAGMENT_CAR_DETAILS = "TAG_FRAGMENT_CAR_DETAILS"
    }

    object ValidationRules {
        const val PASSWORD_MIN_CHARACTERS = 8
        const val PASSWORD_MAX_CHARACTERS = 32
        const val NAME_MAX_CHARACTERS = 40
        const val USERNAME_MIN_CHARACTERS = 3
        const val USERNAME_MAX_CHARACTERS = 40
        const val SEARCH_FILTER_MIN_CHARACTERS = 1
        const val CONNECTION_ESTABLISHED_ALERT_DURATION = 2000L //ms
    }

    object Patterns {
        val nameRegex: Pattern = Pattern.compile("[a-zA-z]+([ \'-][a-zA-Z]+)*")
        val numberPattern: Pattern = Pattern.compile("^[0-9]+([.,][0-9]{1,2})?$*")
        val passwordRegex: Pattern =
            Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,32}$")
        val spaceRegex: Pattern = Pattern.compile("^(.*\\s+.*)+$")
    }
}