package com.avsoftware.data.cocktail

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET

class CocktailApiImpl(val retrofit: Retrofit) : CocktailApi {

    val  api: ICocktailRetrofit = retrofit.create(ICocktailRetrofit::class.java)

    override fun randomCocktail(): Single<CocktailInfo> {
        return api.getRandomCocktail()
    }

    override fun cocktailByName(name: String): CocktailInfo {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cocktailByIngredient(ingredient: String): CocktailInfo {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

interface ICocktailRetrofit {

    @GET("random.php")
    fun getRandomCocktail(): Single<CocktailInfo>

}