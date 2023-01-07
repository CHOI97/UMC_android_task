package com.cookandroid.umc_android_3_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cookandroid.umc_android_3_1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var mViewbinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewbinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(mViewbinding.root)
        // main에서 받은 String
        val extras = intent.extras
        val data = extras!!["first_msg"] as String
        // 받은 string second activity textview에 data삽입
        mViewbinding.secondTv.text = data
        mViewbinding.btnToThird.setOnClickListener {
            val intent = Intent(this,ThirdActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        intent.putExtra("callback_msg","back")
        setResult(RESULT_OK, intent);
        finish()
        super.onBackPressed()
    }

}