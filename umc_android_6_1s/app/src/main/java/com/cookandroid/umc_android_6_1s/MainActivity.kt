package com.cookandroid.umc_android_6_1s

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cookandroid.umc_android_6_1s.databinding.ActivityMainBinding
import com.cookandroid.umc_android_6_1s.main_fragment.FeedFragment
import com.cookandroid.umc_android_6_1s.main_fragment.HomeFragment
import com.cookandroid.umc_android_6_1s.main_fragment.StoriesFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.containerFragment.id, HomeFragment())
            .commitAllowingStateLoss()

        viewBinding.navBottom.run{
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_feed -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, FeedFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_stories -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, StoriesFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            //초기 설정
            selectedItemId = R.id.menu_home
        }
    }
}