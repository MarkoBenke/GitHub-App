package com.marko.githubapp.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.marko.githubapp.databinding.ReposFragmentBinding
import com.marko.githubapp.domain.Repo
import com.marko.githubapp.util.BaseFragment
import com.marko.githubapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReposFragment : BaseFragment() {

    @Inject
    lateinit var reposAdapter: ReposAdapter

    private var _binding: ReposFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReposViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ReposFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObserver()
    }

    private fun initObserver() {
        viewModel.reposLiveData.observe(viewLifecycleOwner) { dataState ->
            when(dataState) {
                DataState.Loading -> binding.loader.isVisible = true
                is DataState.Error -> handleError()
                is DataState.Success -> updateUi(dataState.data)
            }
        }
    }

    private fun handleError() {
        binding.loader.isVisible = false
        showErrorDialog()
    }

    private fun updateUi(repos: List<Repo>) {
        binding.loader.isVisible = false
        reposAdapter.submitList(repos)
    }

    private fun initUi() {
        binding.reposRecView.adapter = reposAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}