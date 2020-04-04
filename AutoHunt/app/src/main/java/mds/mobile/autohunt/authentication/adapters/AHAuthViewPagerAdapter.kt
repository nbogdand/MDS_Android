package mds.mobile.autohunt.authentication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import mds.mobile.autohunt.authentication.views.AHLoginFragment
import mds.mobile.autohunt.authentication.views.AHRegisterFragment

class AHAuthViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> AHLoginFragment.newInstance()
            else -> AHRegisterFragment.newInstance()
        }

}
