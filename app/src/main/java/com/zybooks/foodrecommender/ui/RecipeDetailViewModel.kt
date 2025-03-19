package com.zybooks.foodrecommender.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.zybooks.foodrecommender.RecommenderApplication
import com.zybooks.foodrecommender.data.Recipe
import com.zybooks.foodrecommender.data.RecipeDataSource
import com.zybooks.foodrecommender.data.RecommenderRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class RecipeDetailUiState {
    data class Success(val recipe: List<Recipe>) : RecipeDetailUiState()
    data object Error : RecipeDetailUiState()
    data object Loading : RecipeDetailUiState()
}

class RecipeDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repo: RecommenderRepository
) : ViewModel() {

    var recipeUiState: RecipeDetailUiState by mutableStateOf(RecipeDetailUiState.Loading)
        private set


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RecommenderApplication)
                RecipeDetailViewModel(
                    savedStateHandle = createSavedStateHandle(),
                    repo = application.recommenderRepository
                )
            }
        }
    }

    private val recipeId: Long = savedStateHandle.toRoute<Routes.RecipeDetail>().recipeId

    val uiState: StateFlow<RecipeDetailScreenUiState> =
        repo.getRecipe(recipeId)
            .filterNotNull()
            .map {
                RecipeDetailScreenUiState(
                    recipe = it
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = RecipeDetailScreenUiState()
            )

    data class RecipeDetailScreenUiState(
        val recipe: Recipe = Recipe()
    )

    fun getRecipe(id: Long) {
        viewModelScope.launch {
            recipeUiState = try {
                RecipeDetailUiState.Success(repo.getRecipeApi(id))
            } catch (e: Exception) {
                RecipeDetailUiState.Error
            }
        }
    }
}