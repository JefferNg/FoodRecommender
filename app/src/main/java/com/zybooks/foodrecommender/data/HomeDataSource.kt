package com.zybooks.foodrecommender.data

class HomeDataSource {
    private val cuisineFilters = listOf(
        "asian",
        "indian",
        "italian",
        "american",
        "vietnamese",
        "chinese",
        "greek",
        "japanese"
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