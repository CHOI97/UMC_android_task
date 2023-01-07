package com.cookandroid.umc_android_5_2s

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.umc_android_5_2s.databinding.ActivityMemoBinding

class MemoActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.memoSave.setOnClickListener {
            val memo: String = viewBinding.memoDesc.text.toString()
            val intent = Intent(this,MainActivity::class.java).apply{
                putExtra("key",memo)
            }
            setResult(RESULT_OK, intent);
            finish()
        }
    }
}