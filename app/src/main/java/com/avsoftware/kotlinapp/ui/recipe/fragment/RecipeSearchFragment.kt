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
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.RecipeSearchFragmentBinding
import com.avsoftware.kotlinapp.ui.recipe.SearchActivityViewModel
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class RecipeSearchFragment: Fragment() {

    @Inject
    lateinit var mViewModel: SearchActivityViewModel

    private lateinit var mViewBinding: RecipeSearchFragmentBinding

    private val mDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.recipe_search_fragment, container, false)
        mViewBinding.setLifecycleOwner(this)

        return mViewBinding.root
    }

    private fun bindViewComponents(): View {

        mViewBinding.viewModel = mViewModel

        mViewBinding.recyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?

        // connect search
        mDisposable.add(mViewModel.connectSearch()
                .doOnError(Timber::e)
                .subscribe())

        // Live Data, pins observer to Activity lifecycle, cleans up self
        mViewModel.recipeClicked.observe(this, object: Observer<RecipeInfo?> {
            override fun onChanged(t: RecipeInfo?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        } )

        // Refactor into custom binder?
        mViewBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mViewModel.searchTrigger.accept(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mViewModel.queryText.accept(newText)
                return true
            }
        })

        return mViewBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.dispose()
    }
}