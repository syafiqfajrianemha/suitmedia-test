package com.syafiqfajrianemha.suitemediatest.repository

import com.syafiqfajrianemha.suitemediatest.data.ApiService
import com.syafiqfajrianemha.suitemediatest.data.UserResponse
import retrofit2.Call

class UserRepository(private val apiService: ApiService) {

    fun getUsers(page: Int, perPage: Int): Call<UserResponse> {
        return apiService.getUsers(page, perPage)
    }
}