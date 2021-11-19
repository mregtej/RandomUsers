package com.mregtej.randomusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.mregtej.randomusers.architecture.LiveDataEventObserver
import com.mregtej.randomusers.database.model.RandomUserDetailsData
import com.mregtej.randomusers.database.model.RandomUserListData
import com.mregtej.randomusers.databinding.MainFragmentBinding
import com.mregtej.randomusers.ui.adapter.MainAdapter
import com.mregtej.randomusers.ui.helper.OnEndOfListReachedListener
import com.mregtej.randomusers.ui.sharedviewmodel.SharedViewModel
import com.mregtej.randomusers.ui.state.Resource
import com.mregtej.randomusers.ui.state.Status.*
import com.mregtej.randomusers.ui.viewmodel.MainViewModel
import com.mregtej.randomusers.ui.viewmodel.MainViewModelFactory

class MainFragment(private val viewModelFactory: MainViewModelFactory) : Fragment() {

    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val endOfListReachedListener = OnEndOfListReachedListener {
        viewModel.addNewRandomUsers()
    }

    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindViewModel() = viewModel.apply {
        randomUsersState.observe(viewLifecycleOwner, Observer(::handleRandomUsersState))
        detailedUserState.observe(viewLifecycleOwner, LiveDataEventObserver(::handleDetailedUserState))
    }

    private fun initRecyclerView() {
        adapter = MainAdapter(viewModel::onUserClicked)
        binding.randomUserList.let {
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(context, VERTICAL))
            it.addOnScrollListener(endOfListReachedListener)
        }
    }

    private fun handleRandomUsersState(state: Resource<List<RandomUserListData>>) = when (state.status) {
        LOADING -> showLoading()
        SUCCESS -> state.data?.let { showContent(it) }
        ERROR -> state.message?.let { showError(it) }
    }

    private fun handleDetailedUserState(state: Resource<RandomUserDetailsData>) = when (state.status) {
        LOADING -> showLoading()
        SUCCESS -> state.data?.let { onNavigateToUserDetails(it) }
        ERROR -> state.message?.let { showError(it) }
    }

    private fun showLoading() { binding.progressBar.isVisible = true }

    private fun hideLoading() { binding.progressBar.isVisible = false }

    private fun showContent(data: List<RandomUserListData>) {
        hideLoading()
        adapter.submitList(data)
    }

    private fun onNavigateToUserDetails(user: RandomUserDetailsData) {
        hideLoading()
        sharedViewModel.onNavigateToUserDetails(user)
    }

    private fun showError(message: String) {
        hideLoading()
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}