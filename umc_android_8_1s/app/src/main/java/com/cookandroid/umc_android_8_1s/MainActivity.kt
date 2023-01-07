package com.cookandroid.umc_android_8_1s


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.umc_android_8_1s.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private val roomDb by lazy {
        AppDatabase.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var dataList: ArrayList<Memo> = arrayListOf()
        var dataRVAdapter = DataRVAdapter(dataList)

        viewBinding.rvData.adapter = dataRVAdapter
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)
        //클릭 인터페이스
        runOnUiThread{
            dataList.addAll(ArrayList(roomDb!!.MemoDao().selectAll()))
            Log.d("roomDb all data",roomDb!!.MemoDao().selectAll().toString())
            dataRVAdapter.notifyDataSetChanged()
        }
        dataRVAdapter.setOnItemClickListener(object: DataRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: Memo, position: Int) {
                val intent = Intent(this@MainActivity,MemoActivity::class.java)
                intent.putExtra("memo_type",FIX)
                intent.putExtra("FIX_desc",data.content)
                intent.putExtra("item_position",position)
                activityResultLauncher.launch(intent)
            }
            override fun onLongClick(v: View, data: Memo, position: Int) {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("메모삭제").setMessage("메모를 삭제하시겠습니까?")
                    .setPositiveButton("삭제하기", DialogInterface.OnClickListener { dialogInterface, i ->
                        dataList.apply{
                            if(position !=null){
                                dataList.removeAt(position)
                                dataRVAdapter.notifyItemRemoved(position)
                            }
                        }
                        roomDb!!.MemoDao().delete(data)
                    })
                    .setNegativeButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->
                    })
                    .show()
            }

        })
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == SAVE) {
                val mString = result.data?.getStringExtra("key") ?: ""
                Toast.makeText(this, mString, Toast.LENGTH_SHORT).show()
                dataList.apply{
                    add(Memo(mString))
                    dataRVAdapter.notifyItemInserted(dataRVAdapter.itemCount)
                }
            }else if(result.resultCode == FIX){
                val mString = result.data?.getStringExtra("key") ?: ""
                val mPosition = result.data?.getIntExtra("key_position",0)
                dataList.apply{
                    if (mPosition != null) {
                        set(mPosition,Memo(mString))
                        dataRVAdapter.notifyItemChanged(mPosition)
                    }
                }
            }
        }

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == SAVE) {
                val mString = result.data?.getStringExtra("key") ?: ""
                Toast.makeText(this, mString, Toast.LENGTH_SHORT).show()
                dataList.apply{
                    roomDb!!.MemoDao().insert(Memo(mString))
                    add(Memo(mString))
                    dataRVAdapter.notifyItemInserted(dataRVAdapter.itemCount)
                }
            }else if(result.resultCode == FIX){
                val mString = result.data?.getStringExtra("key") ?: ""
                val mPosition = result.data?.getIntExtra("key_position",0)
                dataList.apply{
                    if (mPosition != null) {
                        set(mPosition,Memo(mString))
                        dataRVAdapter.notifyItemChanged(mPosition)
                    }
                }
            }
        }


        viewBinding.mainAddMemo.setOnClickListener{
            val intent = Intent(this,MemoActivity::class.java)
            intent.putExtra("memo_type",SAVE)
            activityResultLauncher.launch(intent)
        }
    }
    companion object{
        const val SAVE = 1001
        const val FIX = 1002
    }
}

