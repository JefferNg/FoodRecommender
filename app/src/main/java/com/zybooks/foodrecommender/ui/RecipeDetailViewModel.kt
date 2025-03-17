package com.zybooks.foodrecommender.ui

import androidx.lifecycle.ViewModel
import com.zybooks.foodrecommender.data.Recipe
import com.zybooks.foodrecommender.data.RecipeDataSource

class RecipeDetailViewModel : ViewModel() {
    fun getRecipe(id: Int): Recipe = RecipeDataSource().getRecipe(id) ?: Recipe()
}