package com.cookandroid.umc_android_3_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.umc_android_3_1.databinding.FragmentFirstBinding

class FirstFragment: Fragment() {
    private lateinit var viewBinding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFirstBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}