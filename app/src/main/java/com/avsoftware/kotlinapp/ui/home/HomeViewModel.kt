package com.avsoftware.kotlinapp.ui.home

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val text: ObservableField<String> = ObservableField("test")

}
