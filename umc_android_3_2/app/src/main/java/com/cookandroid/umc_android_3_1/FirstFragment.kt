package com.cookandroid.umc_android_3_1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.cookandroid.umc_android_3_1.databinding.FragmentFirstBinding

class FirstFragment: Fragment() {
    private lateinit var viewBinding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFirstBinding.inflate(layoutInflater)
        viewBinding.btnFragSend.setOnClickListener {
            Log.d("fragment1","send text")
            val result = viewBinding.etSend.text.toString()
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        }
        return viewBinding.root
    }
}