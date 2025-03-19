package com.zybooks.foodrecommender.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class RecommenderRepository(context: Context, private val recipeApiService: RecipeApiService) {
    // api code
    suspend fun getRecipesApi(ingredient: String, cuisine: String): List<Recipe> =
        recipeApiService.getRecipesByIngredient(ingredient).meals ?:
        recipeApiService.getRecipesByCuisine(cuisine).meals ?:
        emptyList()

    suspend fun getRecipeApi(id: Long): List<Recipe> = recipeApiService.getRecipeById(id).meals ?: emptyList()

    // database code
    private val databaseCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            CoroutineScope(Dispatchers.IO).launch {
                addStarterData()
            }
        }
    }

    private val database: RecommenderDatabase = Room.databaseBuilder(
        context,
        RecommenderDatabase::class.java,
        "recommender.db"
    )
        .addCallback(databaseCallback)
        .build()

    private val recipeDao = database.recipeDao()
    private val restaurantDao = database.restaurantDao()

    fun getRecipe(recipeId: Long) = recipeDao.getRecipe(recipeId)

    fun getRecipes() = recipeDao.getRecipes()

    fun addRecipe(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch {
            recipe.id = recipeDao.addRecipe(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch {
            recipeDao.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        CoroutineScope(Dispatchers.IO).launch {
            recipeDao.deleteRecipe(recipe)
        }
    }

    fun getRestaurant(restaurantId: Long) = restaurantDao.getRestaurant(restaurantId)

    fun getRestaurants() = restaurantDao.getRestaurants()

    fun addRestaurant(restaurant: Restaurant) {
        CoroutineScope(Dispatchers.IO).launch {
            restaurant.id = restaurantDao.addRestaurant(restaurant)
        }
    }

    fun updateRestaurant(restaurant: Restaurant) {
        CoroutineScope(Dispatchers.IO).launch {
            restaurantDao.updateRestaurant(restaurant)
        }
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        CoroutineScope(Dispatchers.IO).launch {
            restaurantDao.deleteRestaurant(restaurant)
        }
    }

    private fun addStarterData() {
        recipeDao.addRecipe(
            Recipe(
                id = 1,
                name = "Oven Roasted Chicken Breast",
                instruction = "An oven roasted chicken breast perfect for any occasion.",
                rating = 8.7f,
                filters = listOf("chicken", "easy")
            ))

        recipeDao.addRecipe(
            Recipe(
                id = 2,
                name = "Italian Sausage Pasta",
                instruction = "A comfort dish served in just under 30 minutes.",
                rating = 9.3f,
                filters = listOf("pasta", "fast", "Italian", "easy", "sausage")
            )
        )

        recipeDao.addRecipe(
            Recipe(
                id = 3,
                name = "Orange Chicken with Rice",
                instruction = "A fan-favorite",
                rating = 8.3f,
                filters = listOf("chicken", "rice", "Asian")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 1,
                name = "Olive Garden",
                rating = 3.7f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("Italian", "pasta", "soup")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 2,
                name = "Panda Express",
                rating = 3.0f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("Chinese", "fast", "chicken", "beef", "rice")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 3,
                name = "Fire Stone",
                rating = 4.5f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("American", "BBQ", "burgers", "sandwiches")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 4,
                name = "Thai Delight Cuisine",
                rating = 3.4f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("Thai", "noodles", "chicken", "beef", "tofu")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 5,
                name = "Chipotle",
                rating = 3.8f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("Mexican", "burritos", "beans", "chicken", "beef")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 6,
                name = "Petra Mediterranean Pizza and Grill",
                rating = 4.3f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("Mediterranean", "pizza", "gyro", "chicken", "bread")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 7,
                name = "At Bites",
                rating = 3.9f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("Vietnamese", "pho", "banh mi", "chicken", "beef")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 8,
                name = "Woodstock's Pizza SLO",
                rating = 3.5f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("Italian", "pizza", "chicken")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 9,
                name = "Poke Chef",
                rating = 4.1f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("Japanese", "sushi", "fish", "rice")
            )
        )

        restaurantDao.addRestaurant(
            Restaurant(
                id = 10,
                name = "Shalimar",
                rating = 3.0f,
                location = "San Luis Obispo",
                phone = "tel: 123-456-7890",
                filters = listOf("Indian", "curry", "rice", "chicken")
            )
        )

    }
}