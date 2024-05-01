package com.example.githubappfinal.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.githubappfinal.data.response.User
import com.example.githubappfinal.data.response.UserResponse
import com.example.githubappfinal.data.retrofit.ApiConfig
import com.example.githubappfinal.ui.setting.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val preferences: SettingPreferences) : ViewModel() {

    fun getThemeSettings() = preferences.getThemeSetting().asLiveData()

    val listUser = MutableLiveData<ArrayList<User>>()

    init {
        setSearchUsers("Andi")
    }

    fun setSearchUsers(query: String) {
        ApiConfig.apiInstance
            .getUsers(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getSearchUsers(): LiveData<ArrayList<User>> {
        return listUser
    }

    class Factory(private val pref: SettingPreferences ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(pref) as T
    }

}