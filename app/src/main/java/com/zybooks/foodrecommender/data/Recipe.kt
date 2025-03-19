package com.zybooks.foodrecommender.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(ignoredColumns = ["ingredients", "measurements"])
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    @SerializedName(value = "idMeal")
    var id: Long = 0,
    @SerializedName(value = "strMeal")
    var name: String = "",
    @SerializedName(value = "strInstructions")
    var instruction: String = "",
    var rating: Float = 0.0f,
    @SerializedName(value = "strYoutube")
    var website: String = "",
    var filters: List<String> = emptyList(),
    @SerializedName(value = "strMealThumb")
    var imageId: String = "",
    var ingredients: List<String> = emptyList(),
    var measurements: List<String> = emptyList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Recipe

        if (id != other.id) return false
        if (name != other.name) return false
        if (filters != other.filters) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (filters?.hashCode() ?: 0)
        return result
    }
}