package com.kevin.fragmentstartutil.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevin.fragmentstartutil.R
import kotlinx.android.synthetic.main.fragment_main.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.anim.FragmentAnimator
import org.jetbrains.anko.onClick

/**
 * Create by KevinTu on 2018/10/12
 */
class MainFragment: BaseFragment() {

    companion object {
        private const val ARG_EXTRAL_STR = "content"

        fun createMainFragment(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        button.onClick { // Normal Start Fragment
            var topFragment = TopFragment.createTopFragment("Normal Start Fragment")
            topFragment.fragmentAnimator = FragmentAnimator(R.anim.bottom_in, R.anim.bottom_out)
            start(topFragment)
            //start(topFragment, ISupportFragment.SINGLETASK) // launchMode is SINGLETASK
        }

        button2.onClick { // Start Fragment Don't Hide Self (Use add), launchModel is STANDARD
            var topFragment = TopFragment.createTopFragment("Start Fragment Don't Hide Self (Use add)")
            var transaction = fragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(R.anim.bottom_in, R.anim.bottom_out, R.anim.bottom_in, R.anim.bottom_out)
            transaction?.add(R.id.container, topFragment)
            transaction?.commit()
        }

        button3.onClick { // Start Fragment Don't Hide Self, launchModel is STANDARD
            var topFragment = TopFragment.createTopFragment("Start Fragment Don't Hide Self")
            topFragment.fragmentAnimator = FragmentAnimator(R.anim.bottom_in, R.anim.bottom_out)
            extraTransaction().startDontHideSelf(topFragment)
        }

        button4.onClick { // Start Fragment Don't Hide Self (Use Invoke), launchModel is SINGLETASK
            var topFragment = TopFragment.createTopFragment("Start Fragment Don't Hide Self (Use Invoke)")
            topFragment.fragmentAnimator = FragmentAnimator(R.anim.bottom_in, R.anim.bottom_out)
            startDontHideSelf(this, topFragment, ISupportFragment.SINGLETASK)
        }
    }
}