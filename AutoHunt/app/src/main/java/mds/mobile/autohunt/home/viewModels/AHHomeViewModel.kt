package mds.mobile.autohunt.home.viewModels

import android.view.View
import androidx.lifecycle.ViewModel
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener
import mds.mobile.autohunt.globalUser.AHCurrentUser
import mds.mobile.autohunt.home.adapters.ScreenTypes

class AHHomeViewModel(): ViewModel() {

    var goToCarList: (() -> Unit)? = null
    var goToCarForm: (() -> Unit)? = null
    var goToUserData: (() -> Unit)? = null
    var goToAuth: (() -> Unit)? = null

    val navigationChangeListener =
        BubbleNavigationChangeListener { _, position ->
            when (position) {
                ScreenTypes.SCREEN_USER_DATA.position -> {
                    AHCurrentUser.user?.let {
                        goToUserData?.invoke()
                    } ?: goToAuth?.invoke()
                }
            }
        }
}