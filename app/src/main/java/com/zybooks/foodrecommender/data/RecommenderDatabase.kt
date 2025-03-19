package com.zybooks.foodrecommender.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Recipe::class, Restaurant::class], version = 1)
@TypeConverters(Converters::class)
abstract class RecommenderDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun restaurantDao(): RestaurantDao
}