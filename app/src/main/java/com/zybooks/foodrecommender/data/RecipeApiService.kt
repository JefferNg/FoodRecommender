package com.zybooks.foodrecommender.data

import retrofit2.http.GET
import retrofit2.http.Query

data class RecipeResponse(
    val meals: List<Recipe>?
)

interface RecipeApiService {
    @GET("filter.php")
    suspend fun getRecipes(
        @Query("i") ingredient: String
    ): RecipeResponse

}