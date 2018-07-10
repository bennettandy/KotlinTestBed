package com.avsoftware.data.cocktail

import java.util.*

// Local Data class for API binding
// translate to Domain Class before passing to UI
data class CocktailInfo(
        val idDrink: Int,
        val strDrink: String,
        val strVideo: String,
        val strCategory: String,
        val strIBA: String,
        val strAlcoholic: String,
        val strGlass: String,
        val strInstructions: String,
        val strDrinkThumb: String,
        
        // Ingredients
        val strIngredient1: String,
        val strIngredient2: String,
        val strIngredient3: String,
        val strIngredient4: String,
        val strIngredient5: String,
        val strIngredient6: String,
        val strIngredient7: String,
        val strIngredient8: String,
        val strIngredient9: String,
        val strIngredient10: String,
        val strIngredient11: String,
        val strIngredient12: String,
        val strIngredient13: String,
        val strIngredient14: String,
        val strIngredient15: String,
        
        // Measure
        val strMeasure1: String,
        val strMeasure2: String,
        val strMeasure3: String,
        val strMeasure4: String,
        val strMeasure5: String,
        val strMeasure6: String,
        val strMeasure7: String,
        val strMeasure8: String,
        val strMeasure9: String,
        val strMeasure10: String,
        val strMeasure11: String,
        val strMeasure12: String,
        val strMeasure13: String,
        val strMeasure14: String,
        val strMeasure15: String,

        val dateModified: Date
) {
}

/*
{
  "drinks": [
    {
      "idDrink": "14602",
      "strDrink": "Tequila Surprise",
      "strVideo": null,
      "strCategory": "Shot",
      "strIBA": null,
      "strAlcoholic": "Alcoholic",
      "strGlass": "Shot glass",
      "strInstructions": "Fill shot glass with Tequila. Add drops of Tobasco sauce.",
      "strDrinkThumb": "https://www.thecocktaildb.com/images/media/drink/8189p51504735581.jpg",
      "strIngredient1": "Tequila",
      "strIngredient2": "Tabasco sauce",
      "strIngredient3": "",
      "strIngredient4": "",
      "strIngredient5": "",
      "strIngredient6": "",
      "strIngredient7": "",
      "strIngredient8": "",
      "strIngredient9": "",
      "strIngredient10": "",
      "strIngredient11": "",
      "strIngredient12": "",
      "strIngredient13": "",
      "strIngredient14": "",
      "strIngredient15": "",
      "strMeasure1": "full glass ",
      "strMeasure2": "About 8 drops ",
      "strMeasure3": " ",
      "strMeasure4": " ",
      "strMeasure5": " ",
      "strMeasure6": " ",
      "strMeasure7": " ",
      "strMeasure8": "",
      "strMeasure9": "",
      "strMeasure10": "",
      "strMeasure11": "",
      "strMeasure12": "",
      "strMeasure13": "",
      "strMeasure14": "",
      "strMeasure15": "",
      "dateModified": "2017-09-06 23:06:21"
    }
  ]
}
 */