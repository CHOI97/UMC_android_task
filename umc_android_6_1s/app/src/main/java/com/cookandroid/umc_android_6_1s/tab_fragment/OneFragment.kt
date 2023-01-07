package com.cookandroid.umc_android_6_1s.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.umc_android_6_1s.databinding.FragmentOneBinding

class OneFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentOneBinding.inflate(layoutInflater).root
    }
}