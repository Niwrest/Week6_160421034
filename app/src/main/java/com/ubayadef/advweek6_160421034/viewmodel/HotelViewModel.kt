package com.ubayadef.advweek6_160421034.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubayadef.advweek6_160421034.model.Hotel

class HotelViewModel(application: Application): AndroidViewModel(application) {
    val HotelLd = MutableLiveData<ArrayList<Hotel>>()
    val loadingLD = MutableLiveData<Boolean>()
    val HotelLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyHotelTag"
    private var queue: RequestQueue? = null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    //namanya bisa fetch, load
    fun refresh() {

        loadingLD.value = true
        HotelLoadErrorLD.value = false

        val url = "http://10.0.2.2/hotel.json"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                loadingLD.value = false

                Log.d("show_volley", it)

                val sType = object : TypeToken<List<Hotel>>() {}.type
                val result = Gson().fromJson<List<Hotel>>(it, sType)

                HotelLd.value = result as ArrayList<Hotel>


            },
            {
                Log.d("show_volley", it.toString())

            }

        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
}