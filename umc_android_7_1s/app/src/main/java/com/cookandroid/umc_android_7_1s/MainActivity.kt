package com.cookandroid.umc_android_7_1s

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.cookandroid.umc_android_7_1s.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    var total = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.btnStart.setOnClickListener {
            startThread()
        }
    }
    private fun startThread(){
        Thread{
            Thread(){
                for(i in 0 until 30){
                    println(i)
                    Thread.sleep(1000)
                    total++
                    val minute = String.format("%02d",total/60)
                    val second = String.format("%02d",total%60)
                    handler.post{
                        viewBinding.tvTimer.text = "$minute:$second"
                    }
                }
                Toast.makeText(this,"타이머가 종료되었습니다",Toast.LENGTH_SHORT).show()
            }.start()
        }
    }
}