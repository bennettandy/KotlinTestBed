package com.avsoftware.kotlinapp.ui.recipe.fragment

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.kotlinapp.KotlinApp
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.RecipeSearchFragmentBinding
import com.avsoftware.kotlinapp.di.RecipeSubComponent
import com.avsoftware.kotlinapp.di.RecipeSubModule
import com.avsoftware.kotlinapp.ui.recipe.RecipeSearchViewModel
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class RecipeSearchFragment: Fragment() {

    @Inject
    lateinit var mViewModelRecipe: RecipeSearchViewModel

    private lateinit var mViewBinding: RecipeSearchFragmentBinding

    private val mDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //KotlinApp.graph.inject(this)

        val recipeComponent = KotlinApp.graph.add(RecipeSubModule(activity ))
        recipeComponent.inject(this)

        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.recipe_search_fragment, container, false)
        mViewBinding.setLifecycleOwner(this)

        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewBinding.viewModel = mViewModelRecipe

        mViewBinding.recyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?

        // connect search
        mDisposable.add(mViewModelRecipe.connectSearch()
                .doOnError(Timber::e)
                .subscribe())

        mDisposable.add(mViewModelRecipe.recipeClicked
                .doOnNext{ t: RecipeInfo? -> if (t != null) {
                    val nav = NavHostFragment.findNavController(this@RecipeSearchFragment)
                    val args = Bundle()
                    args.putParcelable("recipe", t)
                    nav.navigate(R.id.recipeDetailsFragment, args)
                } }
                .subscribe()
        )

        // Refactor into custom binder?
        mViewBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mViewModelRecipe.searchTrigger.accept(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mViewModelRecipe.queryText.accept(newText)
                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.dispose()
    }
}