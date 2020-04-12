package mds.mobile.autohunt.authentication.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_auth.*
import mds.mobile.autohunt.R
import mds.mobile.autohunt.authentication.adapters.AHAuthViewPagerAdapter
import mds.mobile.autohunt.authentication.adapters.AuthScreenType
import mds.mobile.autohunt.authentication.views.fragments.AHBaseAuthFragment
import mds.mobile.autohunt.home.adapters.ScreenTypes
import mds.mobile.autohunt.home.viewModels.AHHomeViewModel

class AHAuthActivity : AppCompatActivity(), AHBaseAuthFragment.Companion.AuthNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        initViews()
    }

    private fun initViews() {
        viewPager2?.adapter = AHAuthViewPagerAdapter(this)
    }

    override fun goToLogin() {
        viewPager2.setCurrentItem(
            AuthScreenType.SCREEN_LOGIN.position,
            true
        )
    }

    override fun goToRegister() {
        viewPager2.setCurrentItem(
            AuthScreenType.SCREEN_REGISTER.position,
            true
        )
    }
}
