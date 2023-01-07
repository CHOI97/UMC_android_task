package com.cookandroid.umc_android_6_1s.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.umc_android_6_1s.adapter.MainVPAdapter
import com.cookandroid.umc_android_6_1s.databinding.FragmentFeedBinding
import com.google.android.material.tabs.TabLayoutMediator

class FeedFragment: Fragment(){
    private lateinit var binding: FragmentFeedBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainVPAdapter = this.activity?.let { MainVPAdapter(it) }
        binding.mainVp.adapter = mainVPAdapter
        val tabTitleArray = arrayOf(
            "one","two","three"
        )
        TabLayoutMediator(binding.mainTab, binding.mainVp) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }
}

