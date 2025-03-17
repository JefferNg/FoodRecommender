package com.zybooks.foodrecommender.data

class HomeDataSource {
    private val homeFilters = listOf(
        "asian",
        "indian",
        "italian",
        "american",
        "chicken",
        "pork",
        "beef",
        "thai",
        "vietnamese",
        "chinese",
        "greek",
        "japanese"
    )

    fun getHomeFilters() = homeFilters
}