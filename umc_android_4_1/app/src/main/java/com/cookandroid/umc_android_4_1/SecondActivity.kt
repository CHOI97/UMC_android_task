package com.cookandroid.umc_android_4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cookandroid.umc_android_4_1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.secondTvText.text = intent.getStringExtra("main_memo")
    }
}