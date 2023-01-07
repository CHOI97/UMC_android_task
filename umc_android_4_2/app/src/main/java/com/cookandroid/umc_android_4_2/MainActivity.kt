package com.cookandroid.umc_android_4_2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cookandroid.umc_android_4_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private var memo: String = ""
    private var notificationManager: NotificationManager? = null
    private val CHANNEL_ID = "testChannel01"   // Channel for notification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        createNotificationChannel(CHANNEL_ID, "testChannel", "this is a test Channel")
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
        displayNotification()
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
    private fun createNotificationChannel(channelId: String, name: String, channelDescription: String) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT // set importance
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = channelDescription
            }
            // Register the channel with the system
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun displayNotification() {
        val notificationId = 66

        val notification = Notification.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.memo_icon)
            .setContentTitle("메모 작성중")
            .setContentText(memo)
            .build()
        notificationManager?.notify(notificationId, notification)
    }
}