package com.avsoftware.data.cocktail

import io.reactivex.Single

interface CocktailApi {
    fun randomCocktail(): Single<CocktailInfo>
    fun cocktailByName( name: String ): CocktailInfo
    fun cocktailByIngredient( ingredient: String): CocktailInfo
}