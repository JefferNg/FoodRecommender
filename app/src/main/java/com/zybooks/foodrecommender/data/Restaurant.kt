package com.zybooks.foodrecommender.data

import java.net.URI

data class Restaurant (
    val id: Int = -1,
    val name: String = "",
    val rating: Float = 0.0f,
    val location: String = "",
    val website: URI ?= null,
    val phone: String = "tel:",
    val filters: Array<String> ?= null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Restaurant

        if (id != other.id) return false
        if (filters != null) {
            if (other.filters == null) return false
            if (!filters.contentEquals(other.filters)) return false
        } else if (other.filters != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (filters?.contentHashCode() ?: 0)
        return result
    }
}