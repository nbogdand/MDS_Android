package mds.mobile.autohunt.carDetails.views.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mds.mobile.autohunt.R
import mds.mobile.autohunt.carDetails.views.fragments.AHCarDetailsFragment
import mds.mobile.autohunt.databinding.AHCarActivityBinding
import mds.mobile.autohunt.utils.AHConstants.FragmentTags.TAG_FRAGMENT_CAR_DETAILS
import mds.mobile.autohunt.utils.AHConstants.Keys.FRAGMENT_OBJECT_ID

class AHCarActivity: AppCompatActivity() {

    private lateinit var binding: AHCarActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_car)
        binding.lifecycleOwner = this

        loadCarDetailFragment()
    }

    private fun loadCarDetailFragment() {
        val bundle = Bundle()
        bundle.putInt(FRAGMENT_OBJECT_ID, getCarId())
        val carFragment = AHCarDetailsFragment.newInstance(bundle)

        supportFragmentManager.beginTransaction()
            .replace(binding.flyContainer.id, carFragment)
            .addToBackStack(TAG_FRAGMENT_CAR_DETAILS)
            .commit()
    }

    private fun getCarId(): Int {
        return intent.extras?.getInt(FRAGMENT_OBJECT_ID) ?: run {
            Toast.makeText(applicationContext, "Car ID not found", Toast.LENGTH_LONG).show()
            finish()
            0
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        }

        super.onBackPressed()
    }
}