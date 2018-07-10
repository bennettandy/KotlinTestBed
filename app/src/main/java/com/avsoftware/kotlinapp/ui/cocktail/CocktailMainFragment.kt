package com.avsoftware.kotlinapp.ui.cocktail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avsoftware.data.cocktail.CocktailApi
import com.avsoftware.kotlinapp.KotlinApp
import com.avsoftware.kotlinapp.R
import javax.inject.Inject

class CocktailMainFragment : Fragment() {

    @Inject lateinit var api: CocktailApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KotlinApp.graph.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cocktail_main, container, false)
    }

}
