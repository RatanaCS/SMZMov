package com.sumuzu.sumuzumovie.home


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sumuzu.sumuzumovie.R
import com.sumuzu.sumuzumovie.sign.signin.SignInActivity
import com.sumuzu.sumuzumovie.utils.Preferences
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 *
 *
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(context!!.applicationContext)

        iv_nama.text = preferences.getValues("nama")
        tv_email.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        tv_sign_out.setOnClickListener {

//            finishAffinity()

//            preferences.setValues("nama", "")
//            preferences.setValues("user", "")
//            preferences.setValues("url", "")
//            preferences.setValues("email", "")
//            preferences.setValues("saldo", "")
//            preferences.setValues("status", "")

//            val intent= Intent(this, SignInActivity::class.java)
//                startActivity(intent)


        }

    }
}
