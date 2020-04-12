package mds.mobile.autohunt.home.adapters

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import mds.mobile.autohunt.globalUser.AHCurrentUser
import mds.mobile.autohunt.home.views.fragments.AHHomeBaseFragment
import mds.mobile.autohunt.home.views.fragments.carForm.AHCarFormFragment
import mds.mobile.autohunt.home.views.fragments.carList.AHCarListFragment
import mds.mobile.autohunt.home.views.fragments.userData.AHUserDataFragment

enum class ScreenTypes(val position: Int) {
    SCREEN_CAR_LIST(0),
    SCREEN_CAR_FORM(1),
    SCREEN_USER_DATA(2)
}

class AHHomeViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    @SuppressLint("LogNotTimber")
    override fun createFragment(position: Int): Fragment =
        when (position) {
            ScreenTypes.SCREEN_CAR_LIST.position -> AHCarListFragment()
            ScreenTypes.SCREEN_CAR_FORM.position -> AHCarFormFragment()
            ScreenTypes.SCREEN_USER_DATA.position -> {
                AHCurrentUser.user?.let {
                    Log.e("tag","AHUser is not null")
                } ?: Log.e ("tag", "AHUSER NULL !!")

                AHUserDataFragment()
            }
            else -> AHHomeBaseFragment()
        }
}
