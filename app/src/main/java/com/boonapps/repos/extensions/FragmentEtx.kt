package com.boonapps.repos.extensions


import androidx.fragment.app.Fragment
import com.boonapps.repos.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory(this)
}