package com.zybooks.foodrecommender.ui

import androidx.lifecycle.ViewModel
import com.zybooks.foodrecommender.data.RestaurantDataSource

class RestaurantListViewModel : ViewModel() {
    val restaurantList = RestaurantDataSource().loadRestaurants()
}