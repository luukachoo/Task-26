package com.example.task26.presentation.screen.home

import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.task26.databinding.FragmentHomeBinding
import com.example.task26.presentation.common.BaseFragment
import com.example.task26.presentation.common.Listener
import com.example.task26.presentation.common.Observer
import com.example.task26.presentation.event.HomeFragmentEvent
import com.example.task26.presentation.extension.showSnackbar
import com.example.task26.presentation.state.HomeViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), Listener,
    Observer {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private val homeRecyclerAdapter by lazy { HomeRecyclerAdapter() }

    override fun init() {
        setUpRecycler()
        listeners()
        observers()
    }

    override fun listeners() {
        filterSearch()
    }

    private fun filterSearch() {
        var searchJob: Job? = null
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                searchJob?.cancel()
                searchJob = viewLifecycleOwner.lifecycleScope.launch {
                    val searchText = s.toString()
                    if (searchText.isNotEmpty()) {
                        viewModel.onEvent(HomeFragmentEvent.FetchCategoryByTitle(searchText))
                    } else {
                        viewModel.onEvent(HomeFragmentEvent.FetchCategories)
                    }
                }
            }
        })
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleHomeState(it)
                }
            }
        }
    }

    private fun setUpRecycler() = with(binding) {
        rvCategories.adapter = homeRecyclerAdapter
        viewModel.onEvent(HomeFragmentEvent.FetchCategories)
    }

    private fun handleHomeState(state: HomeViewState) {
        binding.progressBar.isVisible = state.isLoading

        state.categories?.let {
            homeRecyclerAdapter.submitList(it)
        }

        state.errorMessage?.let {
            binding.root.showSnackbar(it)
            viewModel.onEvent(HomeFragmentEvent.ResetErrorMessage)
        }
    }
}