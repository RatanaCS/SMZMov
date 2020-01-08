package com.sumuzu.sumuzumovie.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumuzu.sumuzumovie.checkout.adapter.CheckoutAdapter
import com.sumuzu.sumuzumovie.R
import com.sumuzu.sumuzumovie.checkout.model.Checkout
import com.sumuzu.sumuzumovie.home.dashboard.DetailActivity
import com.sumuzu.sumuzumovie.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total:Int = 0

    private var saldo:Int = 0

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>


        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

//        dataList.add(Checkout("Total Harus Dibayar", total.toString()))

        rc_checkout.layoutManager = LinearLayoutManager(this)
        rc_checkout.adapter = CheckoutAdapter(dataList) {
        }

        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        tv_total_bayar.setText(formatRupiah.format(total.toString().toDouble()))
        tv_saldo.setText(formatRupiah.format(preferences.getValues("saldo")!!.toDouble()))

        saldo = preferences.getValues("saldo")!!.toInt()


        if(saldo >= total ) {
            btn_tiket.visibility = View.VISIBLE
            tv_money.visibility= View.INVISIBLE
        } else {
            btn_tiket.visibility = View.INVISIBLE
            tv_money.visibility= View.VISIBLE
        }

        btn_tiket.setOnClickListener {
            val intent = Intent(this@CheckoutActivity,
                CheckoutSuccessActivity::class.java)
            startActivity(intent)
        }


        btn_batal.setOnClickListener {

            total=0

            val intent = Intent(this@CheckoutActivity, DetailActivity::class.java)
            startActivity(intent)
        }

    }
}
