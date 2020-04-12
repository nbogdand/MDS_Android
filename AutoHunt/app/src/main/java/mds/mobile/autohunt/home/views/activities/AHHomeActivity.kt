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
        binding = DataBindingUtil.setContentView<AHHomeActivityBinding>(
            this, R.layout.activity_home
        )
        binding.lifecycleOwner = this
        binding.viewPagerAdapter = AHHomeViewPagerAdapter(this)

        initViews()
    }

    private fun initViews() {
        binding.bottomNavigationConstraint.setNavigationChangeListener { _, position ->
            when (position) {
                ScreenTypes.SCREEN_USER_DATA.position -> {
                    AHCurrentUser.user?.let {

                    } ?: goToAuth()
                }
            }
        }
    }

    private fun goToAuth() {
        val intent = Intent(this, AHAuthActivity::class.java)
        startActivity(intent)
    }
}
