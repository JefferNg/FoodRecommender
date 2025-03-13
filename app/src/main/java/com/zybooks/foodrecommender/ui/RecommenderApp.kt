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
import com.zybooks.foodrecommender.R
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
    data object RecipeList

    @Serializable
    data object RestaurantList
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
                foodFilters = listOf("Asian", "Indian", "Italian", "American", "Chicken", "Pork", "Beef", "Thai", "Vietnamese", "Chinese", "Greek", "Japanese"),
                onRecipeListClick = { navController.navigate(Routes.RecipeList) },
                onRestaurantListClick = { navController.navigate(Routes.RestaurantList) }
            )
        }
        composable<Routes.RecipeList> {
            RecipeListScreen(
                RecipeDataSource().loadRecipes(),
                onRecipeClick = {},
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.RestaurantList> {
            RestaurantListScreen(
                RestaurantDataSource().loadRestaurants(),
                onRestaurantClick = {},
                onUpClick = {
                    navController.navigateUp()
                }
            )
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAppBar(
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit = { }
) {
    CenterAlignedTopAppBar(
        title = { Text("Recommended Recipes") },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantAppBar(
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit = { }
) {
    CenterAlignedTopAppBar(
        title = { Text("Recommended Restaurants") },
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
    recipeList: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            RecipeAppBar(
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
            items(recipeList) { recipe ->
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
fun RestaurantListScreen(
    restaurantList: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit,
    modifier: Modifier = Modifier,
    onUpClick: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            RestaurantAppBar(
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
            items(restaurantList) { restaurant ->
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
fun HomeScreen(
    foodFilters: List<String>,
    onRecipeListClick: (List<String>) -> Unit,
    onRestaurantListClick: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
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
            items(foodFilters) { food ->
                Card(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = { Unit }
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
                    .clickable(onClick = { onRestaurantListClick(foodFilters) })
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
                modifier = Modifier.clickable(onClick = { onRecipeListClick(foodFilters) })
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
//        RecipeListScreen()
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