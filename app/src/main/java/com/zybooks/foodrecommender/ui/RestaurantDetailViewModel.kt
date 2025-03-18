package com.zybooks.foodrecommender.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.zybooks.foodrecommender.RecommenderApplication
import com.zybooks.foodrecommender.data.RecommenderRepository
import com.zybooks.foodrecommender.data.Restaurant
import com.zybooks.foodrecommender.data.RestaurantDataSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RestaurantDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repo: RecommenderRepository
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RecommenderApplication)
                RestaurantDetailViewModel(
                    savedStateHandle = createSavedStateHandle(),
                    repo = application.recommenderRepository
                )
            }
        }
    }

    private val restaurantId: Long = savedStateHandle.toRoute<Routes.RestaurantDetail>().restaurantId

    val uiState: StateFlow<RestaurantDetailScreenUiState> =
        repo.getRestaurant(restaurantId)
            .filterNotNull()
            .map {
                RestaurantDetailScreenUiState(
                    restaurant = it
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = RestaurantDetailScreenUiState()
            )

    data class RestaurantDetailScreenUiState(
        val restaurant: Restaurant = Restaurant()
    )


//    fun getRestaurant(id: Long) : Restaurant = RestaurantDataSource().getRestaurant(id) ?: Restaurant()
}