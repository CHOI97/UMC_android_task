package com.cookandroid.umc_android_3_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.cookandroid.umc_android_3_1.databinding.ActivityFirstBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mViewbinding: ActivityFirstBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewbinding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(mViewbinding.root)
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result -> if(result.resultCode == RESULT_OK){
            Log.d("restart first activity","toast")
            val mString = result.data?.getStringExtra("callback_msg")?: ""
            Toast.makeText(this,mString,Toast.LENGTH_SHORT).show()
        }
        }
        mViewbinding.btnFirstSend.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("first_msg",mViewbinding.etFirst.text.toString())
            activityResultLauncher.launch(intent)
        }
//
    }
}