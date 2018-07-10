package com.avsoftware.domain.cocktail

import io.reactivex.Single

abstract class CocktailProvider {

    abstract fun searchCocktails(searchString: String?): Single<List<Any>>
}