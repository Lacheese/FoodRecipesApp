package com.exemple.foodapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exemple.foodapp.database.Recipes
import com.google.accompanist.coil.CoilImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun RecipeView(
    recipes: Recipes,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            CoilImage(
                data = recipes.featuredImage,
                contentDescription = recipes.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ){
                    Text(
                        text = recipes.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start)
                            .padding(bottom = 4.dp),
                        style = TextStyle(fontSize = 25.sp, fontFamily = FontFamily.Serif)

                        )
                }
                val updated = recipes.dateUpdated
                Text(
                    text = "Updated $updated"
                    ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp ,bottom = 30.dp),
                    style = TextStyle(fontStyle = FontStyle.Italic)
                    )
                for(ingredient in recipes.ingredients){
                    Text(
                        text = "- $ingredient",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 3.dp)

                    )
                }
            }
        }
    }
}