package com.zybooks.foodrecommender.ui

import androidx.lifecycle.ViewModel
import com.zybooks.foodrecommender.data.HomeDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    val cuisineFilters = HomeDataSource().getCuisineFilters()
    val ingredientFilters = HomeDataSource().getIngredientFilters()

    private val selectedCuisineFlow = MutableStateFlow<String?>(null)
    private val selectedIngredientFlow = MutableStateFlow<String?>(null)

    val selectedCuisine = selectedCuisineFlow.asStateFlow()
    val selectedIngredient = selectedIngredientFlow.asStateFlow()

    fun selectCuisine(cuisine: String) {
        selectedCuisineFlow.value = if (selectedCuisineFlow.value == cuisine) null else cuisine
    }

    fun selectIngredient(ingredient: String) {
        selectedIngredientFlow.value = if (selectedIngredientFlow.value == ingredient) null else ingredient
    }
}