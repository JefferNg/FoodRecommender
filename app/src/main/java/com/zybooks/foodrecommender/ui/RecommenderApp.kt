package com.zybooks.foodrecommender.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.zybooks.foodrecommender.R
import com.zybooks.foodrecommender.ui.theme.FoodRecommenderTheme

@Composable
fun RecommenderApp(modifier: Modifier = Modifier) {
    RecipeListScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text("Recommended Recipes") },
        navigationIcon = {
            IconButton(onClick = { Unit }) {
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
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text("Recommended Restaurants") },
        navigationIcon = {
            IconButton(onClick = { Unit }) {
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
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            RecipeAppBar()
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
            items(viewModel.recipeList) { recipe ->
            Card (
                modifier = modifier
                    .clickable(onClick = { Unit })
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
    modifier: Modifier = Modifier,
    viewModel: RestaurantViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            RestaurantAppBar()
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
            items(viewModel.restaurantList) { restaurant ->
                Card (
                    modifier = modifier
                        .clickable(onClick = { Unit })
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
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
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
            items(12) {
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
                            text = "Food filter"
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
                    .clickable(onClick = { Unit })
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
                modifier = Modifier.clickable(onClick = { Unit })
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

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FoodRecommenderTheme {
        HomeScreen()
    }
}