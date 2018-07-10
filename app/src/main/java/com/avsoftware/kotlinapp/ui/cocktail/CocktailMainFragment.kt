package com.avsoftware.kotlinapp.ui.cocktail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avsoftware.data.cocktail.CocktailApi
import com.avsoftware.data.cocktail.CocktailArray
import com.avsoftware.data.cocktail.CocktailInfo
import com.avsoftware.kotlinapp.KotlinApp
import com.avsoftware.kotlinapp.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CocktailMainFragment : Fragment() {

    @Inject lateinit var api: CocktailApi

    val cocktailSingle by lazy { api.randomCocktail() }

    val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KotlinApp.graph.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cocktail_main, container, false)
    }

    override fun onResume() {
        super.onResume()

        disposable.add(
                cocktailSingle
                        .doOnError { t: Throwable ->
                            Timber.e("Failed", t)
                        }
                        .map { t -> t.drinks.first()  }
                        .doOnSuccess { t: CocktailInfo ->
                            Timber.d("Cocktail: $t")
                        }
                        .subscribeOn(Schedulers.io())
                        .subscribe { t1, t2 ->  }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
