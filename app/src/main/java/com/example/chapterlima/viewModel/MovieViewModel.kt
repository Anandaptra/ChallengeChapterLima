package com.example.chapterlima.viewModel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapterlima.network.ResponseMovie
import com.example.chapterlima.network.Result
import com.example.chapterlima.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    lateinit var liveDataMovie : MutableLiveData<List<Result>>
    lateinit var liveDetailMovie : MutableLiveData<ResponseMovie>
    val key = "a89633b1333a8e0f2bb90016feb3252a"

    init {
        liveDataMovie = MutableLiveData()
        liveDetailMovie = MutableLiveData()
    }

    fun getDataFilm() : MutableLiveData<List<Result>>{
        return liveDataMovie
    }
    fun getDetailMovie() : MutableLiveData<ResponseMovie>{
        return liveDetailMovie
    }

    fun getApiMovie() {
        RetrofitClient.instance.getTrendingMovie(key).enqueue(object :
            Callback<ResponseMovie> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<ResponseMovie>,
                response: Response<ResponseMovie>
            ) {
                if (response.isSuccessful){
                    liveDataMovie.postValue(response.body()!!.results)
                } else {
                    liveDataMovie.postValue(null)
                }
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                liveDataMovie.postValue(null)
            }

        })
    }

}

