package com.avsoftware.domain.recipe

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeInfo(
        val title: String = "",
        val imageUrl: String = "",
        val ingredients: List<String> = emptyList()
        ): Parcelable