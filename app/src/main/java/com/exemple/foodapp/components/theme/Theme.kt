package com.exemple.foodapp.components.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.exemple.foodapp.components.NoConnection
import com.exemple.foodapp.components.ProgressBar

private val LightThemeColors = lightColors(
  primary = Yellow,
  secondary = Color.White,
)

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppTheme(
  isNetworkAvailable: Boolean,
  displayProgressBar: Boolean,
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colors =  LightThemeColors
  ){
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(color = Grey)
    ){
      Column{
        NoConnection(isNetworkAvailable = isNetworkAvailable)
        content()
      }
      ProgressBar(isDisplayed = displayProgressBar, 0.3f)
    }
  }
}












































