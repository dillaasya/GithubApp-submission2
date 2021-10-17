package com.adillasyafira.githubapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adillasyafira.githubapp.api.ApiConfig
import com.adillasyafira.githubapp.model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel:ViewModel() {
    private val _detailUser = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailUser


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun showDetailUsers(detailUserParams : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUsers(detailUserParams)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailUser.value = response.body()
                } else {
                    Log.e("MainViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object{
        private const val TAG = "DetailViewModel"


    }
}