package com.cookandroid.umc_android_5_2s



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.umc_android_5_2s.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val dataList: ArrayList<Data> = arrayListOf()
        val dataRVAdapter = DataRVAdapter(dataList)
        dataList.apply{
            add(Data("첫번째 메모"))
        }
        viewBinding.rvData.adapter = dataRVAdapter
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val mString = result.data?.getStringExtra("key") ?: ""
                Toast.makeText(this, mString, Toast.LENGTH_SHORT).show()
                dataList.apply{
                    add(Data(mString))
                    dataRVAdapter.notifyItemInserted(dataRVAdapter.itemCount)
                }
            }
        }

        viewBinding.mainAddMemo.setOnClickListener{
            val intent = Intent(this,MemoActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }
}