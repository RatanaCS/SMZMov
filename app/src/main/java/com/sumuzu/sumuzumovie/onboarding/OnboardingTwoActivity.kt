package com.sumuzu.sumuzumovie.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumuzu.sumuzumovie.R
import kotlinx.android.synthetic.main.activity_onboarding_two.*

class OnboardingTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)

        btn_home.setOnClickListener {
            val intent = Intent(this@OnboardingTwoActivity,
                OnboardingThreeActivity::class.java)
            startActivity(intent)
        }
    }
}
