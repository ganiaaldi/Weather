@file:Suppress("DEPRECATION")

package com.aldi.ganiaweather.etc

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldi.ganiaweather.databinding.ListWeatherBinding
import com.aldi.ganiaweather.model.City
import com.aldi.ganiaweather.model.ListItem

class WeatherAdaptor(private val city: City, private val listData: List<ListItem>)
    : RecyclerView.Adapter<WeatherAdaptor.WeatherViewHolder>(),Extensions {
    private lateinit var onItemClickCallback: OnItemClickCallback
    override fun getItemCount(): Int = Math.min(listData.size, 30);

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): WeatherAdaptor.WeatherViewHolder {

        val weatherItemBinding =
                ListWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(weatherItemBinding)
    }


    override fun onBindViewHolder(holder: WeatherAdaptor.WeatherViewHolder, position: Int) {
        holder.bind(listData[position])
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listData[holder.adapterPosition]) }
    }

    inner class WeatherViewHolder(private val binding: ListWeatherBinding) :
            RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat", "NewApi")
        fun bind(weather: ListItem) {
            val date = weather.dtTxt.toString()
            val formatDate = date?.let { changeFormatDate(it) }
            val formatTime = date?.let { getTime(it) }
            with(binding) {
                tvDay.text = "${formatDate} ${formatTime}"
                tvMaxTemp.text = "${weather.main.tempMax}°"
                tvMinTemp.text = "${weather.main.tempMin}°"
                tvStatusRv.text = weather.weather[0].main
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(weather: ListItem)
    }
}