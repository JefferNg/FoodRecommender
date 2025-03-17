package com.zybooks.foodrecommender.ui

import androidx.lifecycle.ViewModel
import com.zybooks.foodrecommender.data.RecipeDataSource

class RecipeListViewModel : ViewModel() {
    val recipeList = RecipeDataSource().loadRecipes()
}