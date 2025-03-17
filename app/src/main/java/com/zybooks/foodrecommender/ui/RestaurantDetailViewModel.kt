package com.zybooks.foodrecommender.ui

import androidx.lifecycle.ViewModel
import com.zybooks.foodrecommender.data.Restaurant
import com.zybooks.foodrecommender.data.RestaurantDataSource

class RestaurantDetailViewModel : ViewModel() {
    fun getRestaurant(id: Int) : Restaurant = RestaurantDataSource().getRestaurant(id) ?: Restaurant()
}