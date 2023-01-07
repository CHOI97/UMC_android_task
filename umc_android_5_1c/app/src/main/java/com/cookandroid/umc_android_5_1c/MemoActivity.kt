package com.cookandroid.umc_android_5_1c

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.umc_android_5_1c.databinding.ActivityMemoBinding

class MemoActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val memo_type = intent.getIntExtra("memo_type",0)
        val memo_position = intent.getIntExtra("item_position",0)
        if(memo_type==FIX){
            viewBinding.memoSave.text = "수정하기"
            if(intent.hasExtra("FIX_desc")){
                viewBinding.memoDesc.setText(intent.getStringExtra("FIX_desc"))
            }
        }
        viewBinding.memoSave.setOnClickListener {
            val memo: String = viewBinding.memoDesc.text.toString()
            val intent = Intent(this,MainActivity::class.java).apply{
                putExtra("key",memo)
                putExtra("key_position",memo_position)
            }
            if(memo_type==SAVE){
                setResult(SAVE, intent);
            }else if(memo_type==FIX){
                setResult(FIX, intent);
            }
            finish()
        }
    }
    companion object{
        const val SAVE = 1001
        const val FIX = 1002
    }
}