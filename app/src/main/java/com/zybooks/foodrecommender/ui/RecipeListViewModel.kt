package com.zybooks.foodrecommender.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zybooks.foodrecommender.RecommenderApplication
import com.zybooks.foodrecommender.data.Recipe
import com.zybooks.foodrecommender.data.RecipeDataSource
import com.zybooks.foodrecommender.data.RecommenderRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RecipeListViewModel(private val repo: RecommenderRepository) : ViewModel() {
//    val recipeList = RecipeDataSource().loadRecipes()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RecommenderApplication)
                RecipeListViewModel(application.recommenderRepository)
            }
        }
    }

    val uiState: StateFlow<RecipeListScreenUiState> =
        repo.getRecipes()
            .map {
                RecipeListScreenUiState(
                    recipeList = it
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = RecipeListScreenUiState(),
            )

    data class RecipeListScreenUiState(
        val recipeList: List<Recipe> = emptyList()
    )
}