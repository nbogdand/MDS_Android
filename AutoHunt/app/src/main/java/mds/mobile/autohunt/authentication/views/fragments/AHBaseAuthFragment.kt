package mds.mobile.autohunt.authentication.views.fragments

import android.content.Context
import androidx.fragment.app.Fragment

open class AHBaseAuthFragment : Fragment() {

    companion object {
        interface AuthNavigation {
            fun goToLogin()
            fun goToRegister()
        }
    }

    var authNavigation: AuthNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        when (context) {
            is AuthNavigation ->
                authNavigation = context
        }
    }
}