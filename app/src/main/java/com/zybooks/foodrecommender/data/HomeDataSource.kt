package com.zybooks.foodrecommender.data

class HomeDataSource {
    private val cuisineFilters = listOf(
        "mexican",
        "italian",
        "american",
        "vietnamese",
        "chinese",
        "mediterranean",
        "japanese",
        "thai"
    )

    private val ingredientFilters = listOf(
        "chicken",
        "pork",
        "beef",
        "sausage",
        "rice",
        "salmon",
        "bacon",
        "asparagus"
    )

    fun getCuisineFilters() = cuisineFilters
    fun getIngredientFilters() = ingredientFilters
}