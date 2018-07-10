package com.avsoftware.data.cocktail

interface CocktailApi {
    fun randomCocktail(): CocktailInfo
    fun cocktailByName( name: String ): CocktailInfo
    fun cocktailByIngredient( ingredient: String): CocktailInfo
}