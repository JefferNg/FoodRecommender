package com.zybooks.foodrecommender.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM Restaurant WHERE id = :id")
    fun getRestaurant(id: Long): Flow<Restaurant?>

    @Query("SELECT * FROM Restaurant ORDER BY name COLLATE NOCASE")
    fun getRestaurants(): Flow<List<Restaurant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRestaurant(restaurant: Restaurant): Long

    @Update
    fun updateRestaurant(restaurant: Restaurant)

    @Delete
    fun deleteRestaurant(restaurant: Restaurant)
}