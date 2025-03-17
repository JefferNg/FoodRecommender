package com.zybooks.foodrecommender.data

import java.net.URI

data class Recipe (
    val id: Int = -1,
    val name: String = "",
    val description: String = "",
    val rating: Float = 0.0f,
    val website: URI ?= null,
    val filters: List<String> ?= null,
    val imageId: Int = 0
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
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + (filters?.hashCode() ?: 0)
        return result
    }
}