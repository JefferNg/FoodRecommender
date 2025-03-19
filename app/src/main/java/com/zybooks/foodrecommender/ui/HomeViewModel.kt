package com.zybooks.foodrecommender.ui

import androidx.lifecycle.ViewModel
import com.zybooks.foodrecommender.data.HomeDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    val foodFilters = HomeDataSource().getHomeFilters()

    private val selectedFiltersFlow = MutableStateFlow<List<String>>(emptyList())
    val selectedFilters = selectedFiltersFlow.asStateFlow()

    fun selectFilters(filter: String) {
        selectedFiltersFlow.value = if (selectedFiltersFlow.value.contains(filter)) {
            emptyList()
        } else {
            listOf(filter)
        }


        // Selecting multiple filters
//        selectedFiltersFlow.value = if (filter in selectedFiltersFlow.value) {
//            selectedFiltersFlow.value - filter
//        } else {
//            selectedFiltersFlow.value + filter
//        }
    }
}