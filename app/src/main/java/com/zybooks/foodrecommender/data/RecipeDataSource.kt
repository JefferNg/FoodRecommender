package com.zybooks.foodrecommender.data

import androidx.compose.material3.Card

class RecipeDataSource {
    private val recipeList = listOf(
        Recipe(
            id = 1,
            name = "Oven Roasted Chicken Breast",
            description = "An oven roasted chicken breast perfect for any occasion.",
            rating = 8.7f,
            filters = arrayOf("chicken", "easy")
        ),
        Recipe(
            id = 2,
            name = "Italian Sausage Pasta",
            description = "A comfort dish served in just under 30 minutes.",
            rating = 9.3f,
            filters = arrayOf("pasta", "fast", "Italian", "easy", "sausage")
        ),
        Recipe(
            id = 3,
            name = "Orange Chicken with Rice",
            description = "A fan-favorite",
            rating = 8.3f,
            filters = arrayOf("chicken", "rice", "Asian")
        ),
    )

    fun loadRecipes() = recipeList
}