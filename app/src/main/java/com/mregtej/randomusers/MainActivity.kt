package com.mregtej.randomusers

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mregtej.randomusers.architecture.LiveDataEventObserver
import com.mregtej.randomusers.database.model.RandomUserDetailsData
import com.mregtej.randomusers.di.component.AppComponentProvider
import com.mregtej.randomusers.ui.AppFragmentFactory
import com.mregtej.randomusers.ui.MainFragmentDirections
import com.mregtej.randomusers.ui.sharedviewmodel.SharedViewModel
import com.mregtej.randomusers.ui.sharedviewmodel.SharedViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: AppFragmentFactory

    @Inject
    lateinit var viewModelFactory: SharedViewModelFactory

    private lateinit var navController: NavController

    private val viewModel: SharedViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectActivityComponent()
        setFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initNavigationController()
        bindViewModel()
    }

    private fun injectActivityComponent() =
        (application as AppComponentProvider).getAppComponent()?.inject(this)

    private fun setFragmentFactory() {
        supportFragmentManager.fragmentFactory = fragmentFactory
    }

    private fun initNavigationController() {
        navController = ((supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment?)?.navController)
                ?: throw RuntimeException("Activity should contain NavHostFragment")
    }

    private fun bindViewModel() =
        viewModel.navigateToUserDetails.observe(this, LiveDataEventObserver(::navigateToUserDetails))

    private fun navigateToUserDetails(user: RandomUserDetailsData) =
        navController.navigate(MainFragmentDirections.actionNavigateToUserDetails(user))
}