package com.cookandroid.umc_android_6_1s.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cookandroid.umc_android_6_1s.tab_fragment.OneFragment
import com.cookandroid.umc_android_6_1s.tab_fragment.ThreeFragment
import com.cookandroid.umc_android_6_1s.tab_fragment.TwoFragment

class MainVPAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OneFragment()
            1 -> TwoFragment()
            2 -> ThreeFragment()
            else -> OneFragment()
        }
    }
}