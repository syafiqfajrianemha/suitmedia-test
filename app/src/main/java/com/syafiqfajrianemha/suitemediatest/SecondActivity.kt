package com.syafiqfajrianemha.suitemediatest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syafiqfajrianemha.suitemediatest.data.ApiConfig
import com.syafiqfajrianemha.suitemediatest.databinding.ActivitySecondBinding
import com.syafiqfajrianemha.suitemediatest.repository.UserRepository

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }

        val apiService = ApiConfig.getApiService()
        val userRepository = UserRepository(apiService)

        userViewModel = UserViewModel(userRepository)

        val selectedUserName = intent.getStringExtra("selectedUserName")
        binding.tvSelectedUserName.text = selectedUserName ?: "Selected User Name"
        userViewModel.selectedUserName.observe(this) { userName ->
            binding.tvSelectedUserName.text = userName ?: "Selected User Name"
        }

        binding.btnChooseUser.setOnClickListener {
            val toThirdActivity = Intent(this, ThirdActivity::class.java)
            startActivity(toThirdActivity)
        }

        val name = intent.getStringExtra(EXTRA_NAME).toString()
        if (name.isNotEmpty()) {
            binding.tvName.text = name
        }

        if (binding.tvName.text == "null") {
            binding.tvName.text = selectedUserName
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}