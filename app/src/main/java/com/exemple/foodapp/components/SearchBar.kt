package com.exemple.foodapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.exemple.foodapp.components.theme.Yellow
import com.exemple.foodapp.components.ui.recipe_list.FoodCategory


@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
  query: String,
  onQueryChanged: (String) -> Unit,
  onExecuteSearch: () -> Unit,
  categories: List<FoodCategory>,
  selectedCategory: FoodCategory?,
  onSelectedCategoryChanged: (String) -> Unit,
) {
  val keyboardController = LocalSoftwareKeyboardController.current
  Surface(
    modifier = Modifier
      .fillMaxWidth(),
    elevation = 8.dp,
  ) {
    Column {
      Row(
        modifier = Modifier
          .fillMaxWidth()
      ) {
        TextField(
          modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            ,
          value = query,

          onValueChange = { onQueryChanged(it) },
          label = { Text(text = "Search",color = Yellow)
            },


          keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
          ),
          keyboardActions = KeyboardActions(
            onDone = {
              onExecuteSearch()
               keyboardController?.hideSoftwareKeyboard()
            },
          ),
          leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },

          colors = TextFieldDefaults.textFieldColors(backgroundColor = colors.surface),
        )
      }
      val scrollState = rememberLazyListState()
      LazyRow(
        modifier = Modifier
          .padding(start = 8.dp, bottom = 8.dp),
        state = scrollState,
      ) {
        items(categories) {
          FoodCategoryChip(
            category = it.value,
            isSelected = selectedCategory == it,
            onSelectedCategoryChanged = {
              onSelectedCategoryChanged(it)
            },
            onExecuteSearch = {
              onExecuteSearch()
            },
          )
        }
      }
    }
  }
}