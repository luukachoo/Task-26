package com.example.task26.presentation.screen.home

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task26.data.remote.utils.Resource
import com.example.task26.domain.remote.use_case.CategoriesUseCase
import com.example.task26.presentation.event.HomeFragmentEvent
import com.example.task26.presentation.mapper.toPresentationCategoryModel
import com.example.task26.presentation.state.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val categoriesUseCase: CategoriesUseCase) :
    ViewModel() {
    private val _homeState = MutableStateFlow(HomeViewState())
    val homeState get() = _homeState

    fun onEvent(event: HomeFragmentEvent) {
        when (event) {
            is HomeFragmentEvent.FetchCategories -> fetchCategories()
            is HomeFragmentEvent.FetchCategoryByTitle -> fetchCategoryByTitle(event.title)
            is HomeFragmentEvent.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun fetchCategories() {
        loading(true)
        viewModelScope.launch {
            categoriesUseCase.getCategoriesUseCase().collect { res ->
                when (res) {
                    is Resource.Success -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                categories = res.result.map { getCategory -> getCategory.toPresentationCategoryModel() },
                                errorMessage = null
                            )
                        }
                    }

                    is Resource.Failure -> {
                        updateErrorMessage(res.errorMessage)
                        d("ViewModellllll", res.errorMessage)
                    }
                }
            }
        }
    }

    private fun fetchCategoryByTitle(title: String) {
        loading(true)
        viewModelScope.launch {
            categoriesUseCase.getFilteredCategoriesUseCase(title).collect { res ->
                when (res) {
                    is Resource.Success -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                categories = res.result.map { getCategory -> getCategory.toPresentationCategoryModel() },
                                errorMessage = null
                            )
                        }
                    }

                    is Resource.Failure -> updateErrorMessage(res.errorMessage)
                }
            }
        }
    }

    private fun loading(isLoading: Boolean) =
        _homeState.update { it.copy(isLoading = isLoading) }

    private fun updateErrorMessage(message: String?) =
        _homeState.update { it.copy(errorMessage = message) }
}