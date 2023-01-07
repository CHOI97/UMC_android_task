package com.cookandroid.umc_android_4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cookandroid.umc_android_4_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var memo: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.mainBtnMemoNextbtn.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("main_memo",viewBinding.mainEtMemoText.text.toString())
            startActivity(intent)
        }
    }
    // 화면구성하기 EditText, TextView   Activity ContentView로 사용할 수 있도록하기
    override fun onStart() {
        super.onStart()
        Log.d("Now Now state -----------------","onStart")
        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show()
    }
    // onPause에서 저장한 전역변수 내용 EditText 내용으로 설정하기
    override fun onResume() {
        super.onResume()
        Log.d("Now Now state -----------------","onResume")
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show()
    }
    // 현재까지 작성한 내용 Activity의 전역변수에 담아두기
    override fun onPause() {
        super.onPause()
        Log.d("Now state -----------------","onPause")
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        memo = viewBinding.mainEtMemoText.text.toString()
        viewBinding.mainEtMemoText.setText("")
        Log.d("Now state -----------------","onStop")
        Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show()
    }
    // Dialog를 활용하여 다시 작성을 할 것인지 묻기  true = 아무것도안함 false = 전역변수 비우기
    override fun onRestart() {
        super.onRestart()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("onRestart_dialog").setMessage("다시 작성하시겠습니까?")
            .setPositiveButton("YES", DialogInterface.OnClickListener { dialogInterface, i ->
                viewBinding.mainEtMemoText.setText(memo)
            })
            .setNegativeButton("NO",DialogInterface.OnClickListener { dialogInterface, i ->
                memo=""
                viewBinding.mainEtMemoText.setText(memo)
            })
            .show()
        Log.d("Now state -----------------","onRestart")
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Now state -----------------","onDestroy")
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show()
    }

}