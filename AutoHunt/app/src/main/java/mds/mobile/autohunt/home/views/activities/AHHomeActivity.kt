package mds.mobile.autohunt.home.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener
import mds.mobile.autohunt.R
import mds.mobile.autohunt.authentication.views.activities.AHAuthActivity
import mds.mobile.autohunt.databinding.AHHomeActivityBinding
import mds.mobile.autohunt.globalUser.AHCurrentUser
import mds.mobile.autohunt.home.adapters.AHHomeViewPagerAdapter
import mds.mobile.autohunt.home.adapters.ScreenTypes
import mds.mobile.autohunt.home.viewModels.AHHomeViewModel

class AHHomeActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(AHHomeViewModel::class.java)
    }

    lateinit var binding: AHHomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_home
        )
        binding.lifecycleOwner = this
        binding.viewPagerAdapter = AHHomeViewPagerAdapter(this)

        initViews()
    }

    private fun initViews() {
        binding.vpHome.isUserInputEnabled = false
        binding.bottomNavigationConstraint.setNavigationChangeListener { _, position ->
            when (position) {
                ScreenTypes.SCREEN_USER_DATA.position -> {
                    AHCurrentUser.user?.let {
                        setToolbarTitle("Account")
                        goToUserData()
                    } ?: goToAuth()
                }

                ScreenTypes.SCREEN_CAR_LIST.position -> {
                    setToolbarTitle("List")
                    goToCarList()
                }
                ScreenTypes.SCREEN_CAR_FORM.position -> {
                    setToolbarTitle("Form")
                    goToCarForm()
                }
            }
        }
    }

    private fun goToAuth() {
        val intent = Intent(this, AHAuthActivity::class.java)
        startActivity(intent)
    }

    private fun goToCarList() {
        binding.vpHome.setCurrentItem(ScreenTypes.SCREEN_CAR_LIST.position, true)
    }

    private fun goToCarForm() {
        binding.vpHome.setCurrentItem(ScreenTypes.SCREEN_CAR_FORM.position, true)
    }

    private fun goToUserData() {
        binding.vpHome.setCurrentItem(ScreenTypes.SCREEN_USER_DATA.position, true)
    }

    private fun setToolbarTitle(title: String) {
        binding.tvTitle.text = title
    }
}
