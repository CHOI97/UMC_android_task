package com.cookandroid.umc_android_3_1

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.umc_android_3_1.databinding.ActivityThirdBinding

class ThirdActivity: AppCompatActivity() {
    private lateinit var viewBinding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.flFragment.id, FirstFragment())
            .commitAllowingStateLoss()



        viewBinding.btnFrag1.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.flFragment.id, FirstFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.btnFrag2.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.flFragment.id, SecondFragment())
                .commitAllowingStateLoss()
        }
    }
}