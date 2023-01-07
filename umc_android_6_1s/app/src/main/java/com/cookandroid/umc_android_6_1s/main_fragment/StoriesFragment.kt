package com.cookandroid.umc_android_6_1s.main_fragment

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.umc_android_6_1s.R
import com.cookandroid.umc_android_6_1s.adapter.StoriesVPAdapter
import com.cookandroid.umc_android_6_1s.databinding.FragmentStoriesBinding


class StoriesFragment : Fragment(){
    private lateinit var viewBinding: FragmentStoriesBinding
    var currentPosition=0
    val handlerThread: HandlerThread by lazy {
        HandlerThread("viewPager auto slide")
    }
    private lateinit var autosldie:PagerRunnable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentStoriesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataList: ArrayList<Int> = arrayListOf()
        dataList.apply{
            add(R.drawable.test_image1)
            add(R.drawable.test_image2)
            add(R.drawable.test_image3)
            add(R.drawable.test_image4)
            add(R.drawable.test_image5)
            add(R.drawable.test_image6)
            add(R.drawable.test_image7)
        }
        val storiesVPAdapter = this.activity?.let { StoriesVPAdapter(dataList) }
        viewBinding.vpViewpager.adapter = storiesVPAdapter
        viewBinding.indicator.setViewPager(viewBinding.vpViewpager)

        handlerThread.start()
        autosldie = PagerRunnable()
        autosldie?.start()
    }
    private fun setPage(){
        viewBinding.vpViewpager.setCurrentItem(currentPosition,true)
        currentPosition+=1
    }

    inner class PagerRunnable:Thread(){
        override fun run() {
            while(handlerThread.isAlive){
                if(viewBinding.vpViewpager.currentItem+1==viewBinding.vpViewpager.adapter?.itemCount){
                    handlerThread.quitSafely()
                    break
                }
                Handler(handlerThread.looper).post{
                    setPage()
                    Log.d("viewpager auto slide","running")
                }
                sleep(2000)
            }
        }
    }
}