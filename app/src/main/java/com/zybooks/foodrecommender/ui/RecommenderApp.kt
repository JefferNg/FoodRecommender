package com.zybooks.foodrecommender.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zybooks.foodrecommender.R
import com.zybooks.foodrecommender.data.HomeDataSource
import com.zybooks.foodrecommender.data.Recipe
import com.zybooks.foodrecommender.data.RecipeDataSource
import com.zybooks.foodrecommender.data.Restaurant
import com.zybooks.foodrecommender.data.RestaurantDataSource
import com.zybooks.foodrecommender.ui.theme.FoodRecommenderTheme
import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Home

    @Serializable
    data class RecipeList(
        val foodFilters: List<String>
    )

    @Serializable
    data class RestaurantList(
        val foodFilters: List<String>
    )

    @Serializable
    data class RecipeDetail(
        val recipeId: Int
    )

    @Serializable
    data class RestaurantDetail(
        val restaurantId: Int
    )
}

@Preview
@Composable
fun RecommenderApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(
                onRecipeListClick = { selectedFilters ->
                    navController.navigate(Routes.RecipeList(selectedFilters)) },
                onRestaurantListClick = { selectedFilters ->
                    navController.navigate(Routes.RestaurantList(selectedFilters)) }
            )
        }
        composable<Routes.RecipeList> { backstackEntry ->
            val recipes: Routes.RecipeList = backstackEntry.toRoute()

            RecipeListScreen(
                foodFilters = recipes.foodFilters,
                onRecipeClick = { recipe ->
                    navController.navigate(
                        Routes.RecipeDetail(recipe.id)
                    )
                },
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.RecipeDetail> { backstackEntry ->
            val recipe: Routes.RecipeDetail = backstackEntry.toRoute()

            RecipeDetailScreen(
                recipeId = recipe.recipeId,
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.RestaurantList> { backstackEntry ->
            val restaurants: Routes.RestaurantList = backstackEntry.toRoute()

            RestaurantListScreen(
                foodFilters = restaurants.foodFilters,
                onRestaurantClick = { restaurant ->
                    navController.navigate(
                        Routes.RestaurantDetail(restaurant.id)
                    )
                },
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.RestaurantDetail> { backstackEntry ->
            val restaurant: Routes.RestaurantDetail = backstackEntry.toRoute()

            RestaurantDetailScreen(
                restaurantId = restaurant.restaurantId,
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    titleText: String = "",
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit = { }
) {
    CenterAlignedTopAppBar(
        title = { Text(titleText) },
        navigationIcon = {
            IconButton(onClick = onUpClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back button"
                )
            }
        },
        actions = {
            IconButton(onClick = { Unit }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Account button"
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun RecipeListScreen(
    foodFilters: List<String>,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecipeListViewModel = viewModel(),
    onUpClick: () -> Unit = { }
) {
    val filteredRecipes = viewModel.recipeList.filter { recipe ->
        foodFilters.isEmpty() || recipe.filters?.any { it.lowercase() in foodFilters } == true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                titleText = "Recommended Recipes",
                onUpClick = onUpClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { Unit }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh Recommendations"
                )
            }
        }
    ) {
        innerPadding ->
        LazyColumn (
            modifier = Modifier.padding(innerPadding)
        ) {
            items(filteredRecipes) { recipe ->
            Card (
                modifier = modifier
                    .clickable(onClick = { onRecipeClick(recipe) })
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row (
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.user_profile),
                        contentDescription = "User profile picture",
                        modifier = Modifier.size(40.dp)
                    )
                    Column (
                        Modifier.weight(2f)
                    ) {
                        Text(
                            text = recipe.name,
                            Modifier.padding(16.dp, 0.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = recipe.description,
                            Modifier.padding(16.dp, 0.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.image),
                        contentDescription = "Empty image icon",
                        modifier = Modifier.size(40.dp)
                    )
                }

            }
                }
        }
    }

}

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailViewModel = viewModel(),
    onUpClick: () -> Unit = { }
) {
    val recipe = viewModel.getRecipe(recipeId)

    Scaffold (
        topBar = {
            TopAppBar(
                titleText = recipe.name,
                onUpClick = onUpClick
            )
        }
    ) {
        innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = recipe.name,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp)
            )
            Text(
                text = recipe.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(0.dp, 16.dp)
            )
            Text(
                text = recipe.description,
                fontSize = 20.sp,
                modifier = Modifier.padding(0.dp, 16.dp)
            )
            Text(
                text = "Rating: ${recipe.rating}"
            )

        }
    }
}

@Composable
fun RestaurantListScreen(
    foodFilters: List<String>,
    onRestaurantClick: (Restaurant) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RestaurantListViewModel = viewModel(),
    onUpClick: () -> Unit = { }
) {
    val filteredRestaurants = viewModel.restaurantList.filter { restaurant ->
        foodFilters.isEmpty() || restaurant.filters?.any { it.lowercase() in foodFilters } == true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                titleText = "Recommended Restaurants",
                onUpClick = onUpClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { Unit }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh Recommendations"
                )
            }
        }
    ) {
        innerPadding ->
        LazyColumn (
            modifier = Modifier.padding(innerPadding)
        ) {
            items(filteredRestaurants) { restaurant ->
                Card (
                    modifier = modifier
                        .clickable(onClick = { onRestaurantClick(restaurant) })
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row (
                        modifier = Modifier
                            .padding(16.dp, 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.image),
                            contentDescription = "Restaurant Logo",
                            modifier = Modifier.size(40.dp)
                        )
                        Column (
                            Modifier.weight(2f)
                        ) {
                            Text(
                                text = restaurant.name,
                                Modifier.padding(16.dp, 0.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "Rating: ${restaurant.rating}",
                            Modifier.padding(16.dp, 0.dp)
                        )
                    }

                }
            }
        }
    }

}

@Composable
fun RestaurantDetailScreen(
    restaurantId: Int,
    modifier: Modifier = Modifier,
    viewModel: RestaurantDetailViewModel = viewModel(),
    onUpClick: () -> Unit = { }
) {
    val restaurant = viewModel.getRestaurant(restaurantId)

    Scaffold (
        topBar = {
            TopAppBar(
                titleText = restaurant.name,
                onUpClick = onUpClick
            )
        }
    ) {
            innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = restaurant.name,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp)
            )
            Text(
                text = restaurant.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(0.dp, 16.dp)
            )
            Text(
                text = restaurant.location,
                fontSize = 20.sp,
                modifier = Modifier.padding(0.dp, 16.dp)
            )
            Text(
                text = restaurant.phone,
                fontSize = 16.sp
            )
            Text(
                text = "Rating: ${restaurant.rating}"
            )

        }
    }
}

@Composable
fun HomeScreen(
    onRecipeListClick: (List<String>) -> Unit,
    onRestaurantListClick: (List<String>) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val selectedFilters by viewModel.selectedFilters.collectAsState()

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = "What are you feeling today?",
            modifier = Modifier
                .padding(0.dp, 20.dp)
                .border(1.dp, Color.Black)
                .padding(12.dp, 20.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp)
        ) {
            items(viewModel.foodFilters) { food ->
                Card(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = food in selectedFilters,
                            onCheckedChange = { viewModel.selectFilters(food) }
                        )
                        Text(
                            text = food
                        )
                    }
                }
            }
        }
        Row (
            modifier = Modifier
                .padding(16.dp, 24.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card (
                modifier = Modifier
                    .clickable(onClick = { onRestaurantListClick(selectedFilters) })
                    .padding(8.dp, 0.dp)
                    .weight(1f)
            ) {
                Row (
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(R.drawable.tray),
                        contentDescription = "Hand holding tray",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = "Eat Out",
                        modifier = Modifier.padding(8.dp, 0.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
            Card (
                modifier = Modifier.clickable(onClick = { onRecipeListClick(selectedFilters) })
                    .padding(8.dp, 0.dp)
                    .weight(1f)
            ) {
                Row (
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(R.drawable.home),
                        contentDescription = "House",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = "Stay Home",
                        modifier = Modifier.padding(8.dp, 0.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RecommenderPreview() {
//    FoodRecommenderTheme {
//        RestaurantDetailScreen(
//            1,
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun RestaurantPreview() {
//    FoodRecommenderTheme {
//        RestaurantListScreen()
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    FoodRecommenderTheme {
//        HomeScreen(
//            foodFilters = listOf("Asian", "Indian", "Italian", "American", "Chicken", "Pork", "Beef", "Thai", "Vietnamese", "Chinese", "Greek", "Japanese"),
//            onLocationClick = {}
//        )
//    }
//}