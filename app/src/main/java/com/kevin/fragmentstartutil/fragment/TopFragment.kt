package com.kevin.fragmentstartutil.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kevin.fragmentstartutil.R
import org.jetbrains.anko.support.v4.find

/**
 * Create by KevinTu on 2018/10/12
 */
class TopFragment: BaseFragment() {

    private val content: TextView by lazy {
        find<TextView>(R.id.content)
    }

    companion object {
        private const val ARG_EXTRAL_STR = "content"

        fun createTopFragment(content: String): TopFragment {
            var fragment = TopFragment()
            var bundle = Bundle()
            bundle.putString(ARG_EXTRAL_STR, content)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var contentStr = arguments?.getString(ARG_EXTRAL_STR)
        contentStr?.let {
            content.text = contentStr
        }
    }
}