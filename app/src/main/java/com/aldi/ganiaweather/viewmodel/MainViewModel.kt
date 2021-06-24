package com.aldi.ganiaweather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldi.ganiaweather.services.ApiConfig
import com.aldi.ganiaweather.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _weatherList = MutableLiveData<Weather>()
    val weatherList: LiveData<Weather> = _weatherList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    companion object {
        private const val TAG = "MainViewModel"
        private const val API_KEY = "0cca552331f720f698d4e003df064e40"
    }

    fun getForecast() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getForecast("bandung", API_KEY, "metric")
        client.enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _weatherList.postValue(response.body())
                    // Log.e(TAG, "onSucesss: ${response.body()}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}