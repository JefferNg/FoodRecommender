package com.zybooks.foodrecommender.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zybooks.foodrecommender.R
import com.zybooks.foodrecommender.ui.theme.FoodRecommenderTheme

@Composable
fun RecommenderApp(modifier: Modifier = Modifier) {
    ListScreen()
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

@Composable
fun ListScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            RecipeAppBar()
        }
    ) {
        innerPadding ->
        LazyColumn (
            modifier = Modifier.padding(innerPadding)
        ) {
            items(10) {
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
                            text = "Recipe",
                            Modifier.padding(16.dp, 0.dp)
                        )
                        Text(
                            text = "Description",
                            Modifier.padding(16.dp, 0.dp)
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

@Preview(showBackground = true)
@Composable
fun RecommenderPreview() {
    FoodRecommenderTheme {
        ListScreen()
    }
}

