package com.zybooks.foodrecommender.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe WHERE id = :id")
    fun getRecipe(id: Long): Flow<Recipe?>

    @Query("SELECT * FROM Recipe ORDER BY name COLLATE NOCASE")
    fun getRecipes(): Flow<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipe(recipe: Recipe): Long

    @Update
    fun updateRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)
}