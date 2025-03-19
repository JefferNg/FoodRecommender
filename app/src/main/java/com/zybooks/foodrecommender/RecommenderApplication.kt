package com.zybooks.foodrecommender

import android.app.Application
import com.zybooks.foodrecommender.data.RecipeApiService
import com.zybooks.foodrecommender.data.RecommenderRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecommenderApplication: Application() {
    lateinit var recommenderRepository: RecommenderRepository

    override fun onCreate() {
        super.onCreate()

        val recipeApiService: RecipeApiService by lazy {
            val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .build()
            retrofit.create(RecipeApiService::class.java)
        }

        recommenderRepository = RecommenderRepository(this.applicationContext, recipeApiService)
    }
}