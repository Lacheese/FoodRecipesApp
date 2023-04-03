package com.exemple.foodapp.components.ui.recipe_list

import com.exemple.foodapp.components.ui.recipe_list.FoodCategory.*


enum class FoodCategory(val value: String){
    ERROR("error"),
    CHICKEN("Chicken"),
    SPICY("Spicy"),
    COOKIES("Cookies"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    COFFEE("Coffee"),
    CHOCOLATE("Chocolate"),
    PIZZA("Pizza"),
    ITALIAN("Italian"),
    PORK("Pork")
}

fun getAllFoodCategories(): List<FoodCategory>{
    return listOf(
        ERROR, CHICKEN, SPICY, COOKIES, DESSERT, VEGETARIAN, COFFEE, CHOCOLATE, PIZZA, ITALIAN, PORK)
}

fun getFoodCategory(value: String): FoodCategory? {
    val map = values().associateBy(FoodCategory::value)
    return map[value]
}