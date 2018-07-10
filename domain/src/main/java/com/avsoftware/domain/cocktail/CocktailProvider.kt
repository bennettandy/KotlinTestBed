package com.avsoftware.domain.cocktail

import android.os.Parcelable
import io.reactivex.Single
import kotlinx.android.parcel.Parcelize

abstract class CocktailProvider {
    abstract fun searchCocktails(searchString: String?): Single<List<Cocktail>>
}

@Parcelize
data class Cocktail ( val name: String ) : Parcelable