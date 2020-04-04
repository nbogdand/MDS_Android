package mds.mobile.autohunt.authentication.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_auth.*
import mds.mobile.autohunt.R
import mds.mobile.autohunt.authentication.adapters.AHAuthViewPagerAdapter

class AHAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        initViews()
    }

    private fun initViews() {
        viewPager2?.adapter = AHAuthViewPagerAdapter(this)
    }
}
