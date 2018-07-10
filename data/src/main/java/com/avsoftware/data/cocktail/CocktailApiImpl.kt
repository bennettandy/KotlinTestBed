package com.avsoftware.data.cocktail

import retrofit2.Retrofit

class CocktailApiImpl(val retrofit: Retrofit) : CocktailApi {
    override fun randomCocktail(): CocktailInfo {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cocktailByName(name: String): CocktailInfo {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cocktailByIngredient(ingredient: String): CocktailInfo {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}