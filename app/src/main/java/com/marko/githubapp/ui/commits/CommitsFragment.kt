package com.marko.githubapp.ui.commits

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.marko.githubapp.databinding.CommitsFragmentBinding
import com.marko.githubapp.domain.Repo
import com.marko.githubapp.domain.commit.Commit
import com.marko.githubapp.ui.repos.ReposFragment
import com.marko.githubapp.util.BaseFragment
import com.marko.githubapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommitsFragment : BaseFragment() {

    @Inject
    lateinit var commitsAdapter: CommitsAdapter

    private var _binding: CommitsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CommitsViewModel by viewModels()
    private lateinit var repo: Repo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CommitsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObserver()
    }

    private fun initObserver() {
        viewModel.commitsLiveData.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Error -> handleError()
                DataState.Loading -> binding.loader.isVisible = true
                is DataState.Success -> updateUi(dataState.data)
            }
        }
    }

    private fun handleError() {
        binding.loader.isVisible = false
        showErrorDialog()
    }

    private fun updateUi(commits: List<Commit>) {
        binding.loader.isVisible = false
        commitsAdapter.submitList(commits)
    }

    private fun initUi() {
        repo = requireArguments().getParcelable(ReposFragment.REPO_KEY)!!
        binding.repoName.text = repo.name
        binding.commitsRecView.adapter = commitsAdapter
        viewModel.fetchCommitsForRepo(repo.name)

        commitsAdapter.setCommitUrlClickListener { commitUrl ->
            openUrl(commitUrl)
        }
    }

    private fun openUrl(commitUrl: String) =
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(commitUrl)))

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}