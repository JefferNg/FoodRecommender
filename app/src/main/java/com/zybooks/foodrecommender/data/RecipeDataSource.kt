package com.zybooks.foodrecommender.data

class RecipeDataSource {
    private val recipeList = listOf(
        Recipe(
            id = 1,
            name = "Oven Roasted Chicken Breast",
            description = "An oven roasted chicken breast perfect for any occasion.",
            rating = 8.7f,
            filters = listOf("chicken", "easy")
        ),
        Recipe(
            id = 2,
            name = "Italian Sausage Pasta",
            description = "A comfort dish served in just under 30 minutes.",
            rating = 9.3f,
            filters = listOf("pasta", "fast", "Italian", "easy", "sausage")
        ),
        Recipe(
            id = 3,
            name = "Orange Chicken with Rice",
            description = "A fan-favorite",
            rating = 8.3f,
            filters = listOf("chicken", "rice", "Asian")
        ),
    )

    fun loadRecipes() = recipeList

    fun getRecipe(id: Int) : Recipe? {
        for (recipe in recipeList) {
            if (recipe.id == id) {
                return recipe
            }
        }
        return null
    }
}