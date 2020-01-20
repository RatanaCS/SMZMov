package com.sumuzu.sumuzumovie.checkout

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
//import androidx.media.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumuzu.sumuzumovie.checkout.adapter.CheckoutAdapter
import com.sumuzu.sumuzumovie.R
import com.sumuzu.sumuzumovie.checkout.model.Checkout
import com.sumuzu.sumuzumovie.home.dashboard.DetailActivity
import com.sumuzu.sumuzumovie.home.model.Film
import com.sumuzu.sumuzumovie.home.tiket.TiketActivity
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
        val data = intent.getParcelableExtra<Film>("datas")


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

            showNotif(data)
        }


        btn_batal.setOnClickListener {

            total=0

            val intent = Intent(this@CheckoutActivity, DetailActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showNotif(datas: Film){
        val NOTIFICATION_CHANNEL_ID = "channel_smz_notif"
        val context = this.applicationContext
        var notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val channelName = "SMZMOV Notif Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

//        val mIntent = Intent(this, CheckoutSuccessActivity::class.java)
//        val bundle = Bundle()
//        bundle.putString("id", "id_film")
//        mIntent.putExtras(bundle)

        val mIntent = Intent(this, TiketActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("data", datas)
        mIntent.putExtras(bundle)

        val pendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.logo_mov)
            .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo_mov))
            .setTicker("notif SMZ starting")
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Sukses Terbeli")
            .setContentText("Tiket "  + "" + " berhasil terbeli")
            .setAutoCancel(true)

        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())

    }

}
