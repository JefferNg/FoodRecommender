package com.zybooks.foodrecommender.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.net.URI

@Entity
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    @SerializedName(value = "idMeal")
    var id: Long = 0,
    @SerializedName(value = "strMeal")
    var name: String = "",
    var description: String = "",
    var rating: Float = 0.0f,
    var website: String ?= null,
    var filters: List<String> = emptyList(),
    @SerializedName(value = "strMealThumb")
    var imageId: String = ""
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