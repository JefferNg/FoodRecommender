package com.zybooks.foodrecommender.data

class HomeDataSource {
    private val homeFilters = listOf(
        "Asian",
        "Indian",
        "Italian",
        "American",
        "Chicken",
        "Pork",
        "Beef",
        "Thai",
        "Vietnamese",
        "Chinese",
        "Greek",
        "Japanese"
    )

    fun getHomeFilters() = homeFilters
}