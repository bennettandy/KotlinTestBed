package com.avsoftware.kotlinapp.ui.dashboard

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.avsoftware.kotlinapp.R
import timber.log.Timber

class DashboardFragment : Fragment() {

    lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("View model is initialised? " + this::viewModel.isInitialized)
        // Obtains and retains view model instance through config changes
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        Timber.d("Got ViewModel: ${viewModel.hashCode()}")

        Timber.d("View model is initialised? " + this::viewModel.isInitialized)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


}
