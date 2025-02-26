package com.zybooks.foodrecommender.data

import androidx.compose.material3.Card

class RecipeDataSource {
    private val recipeList = listOf(
        Recipe(
            id = 1,
            name = "Oven Roasted Chicken Breast",
            rating = 8.7f,
            filters = arrayOf("chicken", "easy")
        )
    )

    fun loadRecipes() = recipeList
}