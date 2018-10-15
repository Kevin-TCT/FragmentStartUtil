package com.kevin.fragmentstartutil

import android.os.Bundle
import com.kevin.fragmentstartutil.fragment.MainFragment

class MainActivity : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadRootFragment(R.id.container, MainFragment.createMainFragment())
    }
}
