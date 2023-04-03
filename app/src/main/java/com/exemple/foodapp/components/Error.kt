package com.exemple.foodapp.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exemple.foodapp.components.theme.Yellow

@Composable
fun NotFound(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier.align(Alignment.Center)){
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                text = "¯\\_(ツ)_/¯",
                style = TextStyle(fontSize = 55.sp),
                color = Yellow
            )
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Recipes not found",
                color = Yellow,
                style = TextStyle(fontSize = 30.sp),


                )
        }

    }
}
@Composable
fun loadingRecipes(){
    Box(
        modifier = Modifier.fillMaxSize().background(Yellow)
    ){
        Column(modifier = Modifier.align(Alignment.Center)){
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Recipes search \n please wait !",
                color = Color.White,
                style = TextStyle(fontSize = 30.sp),
                )
        }

    }
}
@Composable
fun NoConnection(
    isNetworkAvailable: Boolean,
){
    if(!isNetworkAvailable){
        Column(modifier = Modifier.fillMaxWidth().background(Color.Gray)){
            Text(
                "No network connection",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 20.sp, color = Color.Red)
            )
        }
    }
}