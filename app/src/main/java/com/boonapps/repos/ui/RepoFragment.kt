package com.boonapps.repos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.boonapps.repos.databinding.FragmentReposBinding
import com.boonapps.repos.getViewModelFactory
import com.boonapps.repos.viewmodel.RepoViewModel

class RepoFragment : Fragment() {

    private val viewModel by viewModels<RepoViewModel> { getViewModelFactory() }

    private lateinit var binding: FragmentReposBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReposBinding.inflate(inflater, container, false).apply {
            adapter = ReposAdapter()
            viewmodel = viewModel
            lifecycleOwner = this@RepoFragment.viewLifecycleOwner
        }

        viewModel.loadRepos()

        return binding.root
    }
}