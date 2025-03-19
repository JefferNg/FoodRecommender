package com.zybooks.foodrecommender.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zybooks.foodrecommender.RecommenderApplication
import com.zybooks.foodrecommender.data.Recipe
import com.zybooks.foodrecommender.data.RecommenderRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class RecipeListUiState {
    data class Success(val recipeList: List<Recipe>) : RecipeListUiState()
    data object Error : RecipeListUiState()
    data object Loading : RecipeListUiState()
}

class RecipeListViewModel(private val repo: RecommenderRepository) : ViewModel() {
//    val recipeList = RecipeDataSource().loadRecipes()
    var recipeUiState: RecipeListUiState by mutableStateOf(RecipeListUiState.Loading)
        private set


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

    fun getRecipes(ingredient: String, cuisine: String) {
        viewModelScope.launch {
            recipeUiState = try {
                RecipeListUiState.Success(
                    repo.getRecipesApi(
                        ingredient = ingredient,
                        cuisine = cuisine
                    )
                )
            } catch (e: Exception) {
                RecipeListUiState.Error
            }
        }
    }
}