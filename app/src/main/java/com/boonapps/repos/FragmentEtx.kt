package com.boonapps.repos


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.boonapps.repos.api.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory(this)
}