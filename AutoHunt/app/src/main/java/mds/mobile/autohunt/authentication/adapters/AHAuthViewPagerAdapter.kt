package mds.mobile.autohunt.authentication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import mds.mobile.autohunt.authentication.views.fragments.AHLoginFragment
import mds.mobile.autohunt.authentication.views.fragments.AHRegisterFragment

enum class AuthScreenType(val position: Int) {
    SCREEN_LOGIN(0),
    SCREEN_REGISTER(1)
}

class AHAuthViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            AuthScreenType.SCREEN_LOGIN.position -> AHLoginFragment.newInstance()
            else -> AHRegisterFragment.newInstance()
        }

}
