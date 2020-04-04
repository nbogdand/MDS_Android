package mds.mobile.autohunt.home.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import mds.mobile.autohunt.authentication.views.AHLoginFragment
import mds.mobile.autohunt.authentication.views.AHRegisterFragment
import mds.mobile.autohunt.home.views.fragments.AHHomeBaseFragment


class AHHomeViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = AHHomeBaseFragment()
}
