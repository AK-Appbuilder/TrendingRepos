package com.boonapps.repos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.boonapps.repos.getViewModelFactory
import com.boonapps.repos.viewmodel.RepoViewModel

class RepoFragment: Fragment() {

    private val viewModel by viewModels<RepoViewModel>{ getViewModelFactory() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}