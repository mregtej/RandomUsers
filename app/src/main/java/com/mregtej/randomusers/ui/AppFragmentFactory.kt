package com.mregtej.randomusers.ui

import androidx.fragment.app.FragmentFactory
import com.mregtej.randomusers.ui.viewmodel.MainViewModelFactory

class AppFragmentFactory (
    private val mainViewModelFactory: MainViewModelFactory
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String) = when (className) {
        MainFragment::class.java.name -> instantiateMainFragment()
        else -> super.instantiate(classLoader, className)
    }

    private fun instantiateMainFragment() = MainFragment(mainViewModelFactory)
}