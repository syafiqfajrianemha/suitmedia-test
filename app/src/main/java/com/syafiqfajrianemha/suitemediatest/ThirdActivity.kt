package com.syafiqfajrianemha.suitemediatest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.syafiqfajrianemha.suitemediatest.adapter.UserAdapter
import com.syafiqfajrianemha.suitemediatest.data.ApiConfig
import com.syafiqfajrianemha.suitemediatest.databinding.ActivityThirdBinding
import com.syafiqfajrianemha.suitemediatest.repository.UserRepository

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiConfig.getApiService()
        val userRepository = UserRepository(apiService)

        val adapter = UserAdapter { selectedUserName ->
            userViewModel.setSelectedUserName(selectedUserName)
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("selectedUserName", selectedUserName)
            startActivity(intent)
        }

        userViewModel = UserViewModel(userRepository)

        userViewModel.getUsers(1, 10)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
        userViewModel.userList.observe(this) { userList ->
            adapter.submitList(userList)
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}