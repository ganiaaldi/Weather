package com.aldi.ganiaweather

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.aldi.ganiaweather.databinding.ActivityDetailBinding
import com.aldi.ganiaweather.etc.Extensions

class DetailActivity : AppCompatActivity(), Extensions {
    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
        binding.imgShare.setOnClickListener {
            val shareIntent = Intent()
            val message = "Date : ${binding.detailDate.text} \n Weather Status : ${binding.tvStatusDetail.text} \n Location : Bandung \n With Temperature : ${binding.tvTempDetail.text} \n ${binding.tvWindyDetail.text}"
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(shareIntent, message))
        }
        val date=intent.getStringExtra("date")
        val tempMax=intent.getStringExtra("tempMax")
        val tempMin=intent.getStringExtra("tempMin")
        val status=intent.getStringExtra("status")
        val humidity=intent.getStringExtra("humidity")
        val pressure=intent.getStringExtra("pressure")
        val speed=intent.getStringExtra("speed")
        val gust=intent.getStringExtra("gust")
        val formatDate = date?.let { changeFormatDate(it) }
        val formatTime = date?.let { getTime(it) }
        binding.detailDate.text = "${formatDate}\n${formatTime}"
        binding.tvTempDetail.text = "${tempMax}°"
        binding.tvTempMinDetail.text = "${tempMin}°"
        binding.tvStatusDetail.text = status
        binding.tvHumidityDetail.text = "Humidity : $humidity"
        binding.tvPressureDetail.text = "Pressure : $pressure hpa"
        binding.tvWindyDetail.text = "Wind : ${speed}km/7h"
        binding.tvNumberDetail.text = "$gust"

        Log.d("Extras", "${tempMax}°")
    }

}