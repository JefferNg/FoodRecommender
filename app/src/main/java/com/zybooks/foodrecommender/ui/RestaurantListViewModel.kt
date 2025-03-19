package com.zybooks.foodrecommender.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zybooks.foodrecommender.RecommenderApplication
import com.zybooks.foodrecommender.data.RecommenderRepository
import com.zybooks.foodrecommender.data.Restaurant
import com.zybooks.foodrecommender.data.RestaurantDataSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RestaurantListViewModel(private val repo: RecommenderRepository) : ViewModel() {
//    val restaurantList = RestaurantDataSource().loadRestaurants()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RecommenderApplication)
                RestaurantListViewModel(application.recommenderRepository)
            }
        }
    }

    val uiState: StateFlow<RestaurantListScreenUiState> =
        repo.getRestaurants()
            .map {
                RestaurantListScreenUiState(
                    restaurantList = it
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = RestaurantListScreenUiState()
            )

    data class RestaurantListScreenUiState(
        val restaurantList: List<Restaurant> = emptyList()
    )
}