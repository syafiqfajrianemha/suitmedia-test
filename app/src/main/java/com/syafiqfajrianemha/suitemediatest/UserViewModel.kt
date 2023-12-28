package com.syafiqfajrianemha.suitemediatest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syafiqfajrianemha.suitemediatest.data.DataItem
import com.syafiqfajrianemha.suitemediatest.data.UserResponse
import com.syafiqfajrianemha.suitemediatest.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userList = MutableLiveData<List<DataItem>>()
    val userList: LiveData<List<DataItem>> get() = _userList

    private val _selectedUserName = MutableLiveData<String>()
    val selectedUserName: LiveData<String> get() = _selectedUserName

    fun setSelectedUserName(user: String) {
        _selectedUserName.value = user
    }

    fun getUsers(page: Int, perPage: Int) {

        val call = userRepository.getUsers(page, perPage)

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    userResponse?.let {
                        _userList.postValue(it.data as List<DataItem>?)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d(UserViewModel::class.java.simpleName, "getUsers Failure")
            }
        })
    }
}