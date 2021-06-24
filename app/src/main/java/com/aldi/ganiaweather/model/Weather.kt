package com.aldi.ganiaweather.model

import com.google.gson.annotations.SerializedName

data class Weather(

        @field:SerializedName("city")
        val city: City,

        @field:SerializedName("list")
        val list: List<ListItem>
)

data class ListItem(

        @field:SerializedName("dt")
        val dt: Int,

        @field:SerializedName("dt_txt")
        val dtTxt: String,

        @field:SerializedName("weather")
        val weather: List<WeatherItem>,

        @field:SerializedName("main")
        val main: Main,

        @field:SerializedName("wind")
        val wind: Wind,
)

data class WeatherItem(

        @field:SerializedName("description")
        val description: String,

        @field:SerializedName("main")
        val main: String,

        )

data class Main(

        @field:SerializedName("temp")
        val temp: Double,

        @field:SerializedName("temp_min")
        val tempMin: Double,

        @field:SerializedName("temp_max")
        val tempMax: Double,

        @field:SerializedName("humidity")
        val humidity: Int,

        @field:SerializedName("pressure")
        val pressure: Int
)

data class Wind(
        @field:SerializedName("speed")
        val speed: Double,

        @field:SerializedName("gust")
        val gust: Double,

)

data class City(

        @field:SerializedName("country")
        val country: String,

        @field:SerializedName("name")
        val name: String,
)