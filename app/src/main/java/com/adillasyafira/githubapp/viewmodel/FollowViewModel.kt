package com.adillasyafira.githubapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adillasyafira.githubapp.api.ApiConfig
import com.adillasyafira.githubapp.model.FollowResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel(){
    private val _listFollowers = MutableLiveData<List<FollowResponseItem>>()
    val listFollowers: LiveData<List<FollowResponseItem>> = _listFollowers

    private val _listFollowing = MutableLiveData<List<FollowResponseItem>>()
    val listFollowing: LiveData<List<FollowResponseItem>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun showFollowers(getFollower: String) {
        _isLoading.value = true
        val getService = ApiConfig.getApiService().getDetailUsersFollowers(getFollower)
        getService.enqueue(object : Callback<List<FollowResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowResponseItem>>,
                response: Response<List<FollowResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowers.value = response.body()
                } else {
                    Log.e("FollowersViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowResponseItem>>, t: Throwable) {
                //                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun showFollowing(getFollowing: String) {
        _isLoading.value = true
        val getService = ApiConfig.getApiService().getDetailUsersFollowing(getFollowing)
        getService.enqueue(object : Callback<List<FollowResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowResponseItem>>,
                response: Response<List<FollowResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()
                } else {
                    Log.e("FollowersViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "FollowersViewModel"
    }
}