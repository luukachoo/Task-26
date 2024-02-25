package com.example.task26.presentation.event

sealed class HomeFragmentEvent {
    data object FetchCategories : HomeFragmentEvent()
    data class FetchCategoryByTitle(val title: String) : HomeFragmentEvent()
    data object ResetErrorMessage : HomeFragmentEvent()
}