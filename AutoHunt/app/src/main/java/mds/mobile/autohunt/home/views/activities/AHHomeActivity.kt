package mds.mobile.autohunt.home.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import mds.mobile.autohunt.R
import mds.mobile.autohunt.databinding.AHHomeActivityBinding
import mds.mobile.autohunt.home.adapters.AHHomeViewPagerAdapter

class AHHomeActivity : AppCompatActivity() {

    lateinit var binding: AHHomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<AHHomeActivityBinding>(
            this, R.layout.activity_home
        )
        binding.lifecycleOwner = this
        binding.viewPagerAdapter = AHHomeViewPagerAdapter(this)
    }
}
