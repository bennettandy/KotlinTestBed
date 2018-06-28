package com.avsoftware.kotlinapp.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private lateinit var viewBinding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewBinding = HomeFragmentBinding.inflate(inflater)

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel


        viewBinding.recipeBtn.setOnClickListener { _ ->
            val nav = NavHostFragment.findNavController(this)
            nav.navigate(R.id.recipeSearchFragment)
        }
    }

}
