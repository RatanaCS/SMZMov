package com.sumuzu.sumuzumovie.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumuzu.sumuzumovie.R
import com.sumuzu.sumuzumovie.home.HomeActivity
import com.sumuzu.sumuzumovie.home.tiket.TiketActivity
import com.sumuzu.sumuzumovie.home.tiket.TiketFragment
import kotlinx.android.synthetic.main.activity_checkout_success.*

class CheckoutSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        btn_home.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@CheckoutSuccessActivity,
                HomeActivity::class.java).putExtra("index","1")
            startActivity(intent)
        }

        btn_tiket.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@CheckoutSuccessActivity,
                HomeActivity::class.java).putExtra("index","2")
            startActivity(intent)
        }

    }
}
