package com.sumuzu.sumuzumovie.home.tiket

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumuzu.sumuzumovie.home.model.Film
import com.bumptech.glide.Glide
import com.sumuzu.sumuzumovie.R
import com.sumuzu.sumuzumovie.checkout.model.Checkout

import kotlinx.android.synthetic.main.activity_tiket.*

class TiketActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        val data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data.judul
        tv_genre.text = data.genre
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster_image)

        rc_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1",""))
        dataList.add(Checkout("C2",""))

        rc_checkout.adapter = TiketAdapter(dataList) {
        }

        iv_back.setOnClickListener {
            finish()
        }

        iv_qr_bc.setOnClickListener {

            showQR()

        }
    }

    fun showQR(){
        val inflater_view = layoutInflater.inflate(R.layout.qr_barcode_tiket,null)

        val desc = inflater_view.findViewById(R.id.tv_desc) as TextView
        val bnt_tutup = inflater_view.findViewById(R.id.btn_tutup) as Button

        val alertDialog = AlertDialog.Builder(this)
//        alertDialog.setTitle("QR Barcode Tiket")
        alertDialog.setView(inflater_view)
        alertDialog.setCancelable(true)

        val dialog= alertDialog.create()
        dialog.show()

    }


}
