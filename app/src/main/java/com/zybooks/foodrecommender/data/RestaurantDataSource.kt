package com.zybooks.foodrecommender.data

class RestaurantDataSource {
    private val restaurantList = listOf(
        Restaurant(
            id = 1,
            name = "Olive Garden",
            rating = 3.7f,
            location = "San Luis Obispo",
            phone = "tel: 123-456-7890",
            filters = arrayOf("Italian", "pasta")
        ),
        Restaurant(
            id = 2,
            name = "Panda Express",
            rating = 3.0f,
            location = "San Luis Obispo",
            phone = "tel: 123-456-7890",
            filters = arrayOf("Chinese", "fast")
        ),
        Restaurant(
            id = 3,
            name = "Fire Stone",
            rating = 4.5f,
            location = "San Luis Obispo",
            phone = "tel: 123-456-7890",
            filters = arrayOf("American", "BBQ", "burgers", "sandwiches")
        ),
    )

    fun loadRestaurants() = restaurantList
}