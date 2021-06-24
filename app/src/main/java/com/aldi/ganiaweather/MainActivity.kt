package com.aldi.ganiaweather

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldi.ganiaweather.databinding.ActivityMainBinding
import com.aldi.ganiaweather.model.ListItem
import com.aldi.ganiaweather.viewmodel.MainViewModel
import com.aldi.ganiaweather.etc.WeatherAdaptor
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showData()
        mainViewModel.isLoading.observe(this, { isLoading ->
            binding.progressDialog.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        binding.refreshLayout.setOnRefreshListener {
            showData()
            binding.refreshLayout.isRefreshing = false
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun showData(){
        mainViewModel.getForecast()
        mainViewModel.weatherList.observe(this, { weather ->
            binding.rvWeather.setHasFixedSize(true)
            binding.rvWeather.layoutManager = LinearLayoutManager(this)
            val adapter = WeatherAdaptor(weather.city, weather.list)
            binding.rvWeather.adapter = adapter
            adapter.setOnItemClickCallback(object : WeatherAdaptor.OnItemClickCallback {
                override fun onItemClicked(weather: ListItem) {
                    Log.d("Click","OnClicked")
                    showSelected(weather)
                }
            })
            binding.tvTitle.text = weather.list[0].weather[0].description
            val cal = Calendar.getInstance()
            val tanggal =   SimpleDateFormat("E,dd-MMM").format(cal.time)
            binding.tvDate.text = tanggal
            binding.tvTempMax.text = "${weather.list[0].main.temp}°"
            binding.tvTempMin.text = "${weather.list[0].main.tempMin}°"
            binding.tvCity.text = weather.city.name
            binding.tvStatus.text = weather.list[0].weather[0].main
            val a = binding.rvPrime
            a.setOnClickListener {
                val weatherr : ListItem = weather.list[0]
                showSelected(weatherr)
            }
            Log.d("check","${weather.city}, ${weather.list}")
        })
    }

    private fun showSelected(weather: ListItem) {
        val date = weather.dtTxt
        val tempMax = weather.main.tempMax.toString()
        val tempMin = weather.main.tempMin.toString()
        val status = weather.weather[0].main
        val humidity = weather.main.humidity.toString()
        val pressure = weather.main.pressure.toString()
        val speed = weather.wind.speed.toString()
        val gust = weather.wind.gust.toString()
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
//        intent.putExtras(bundle)
        Log.d("Test", tempMax)
        intent.putExtra("date",date)
        intent.putExtra("tempMax",tempMax)
        intent.putExtra("tempMin",tempMin)
        intent.putExtra("status",status)
        intent.putExtra("humidity",humidity)
        intent.putExtra("pressure",pressure)
        intent.putExtra("speed",speed)
        intent.putExtra("gust",gust)
        startActivity(intent)
    }
}