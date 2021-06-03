package com.marko.githubapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.marko.githubapp.R
import com.marko.githubapp.databinding.UserFragmentBinding
import com.marko.githubapp.domain.User
import com.marko.githubapp.util.BaseFragment
import com.marko.githubapp.util.DataState
import com.marko.githubapp.util.handleVisibilityByInput
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : BaseFragment() {

    @Inject
    lateinit var glide: RequestManager

    private var _binding: UserFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initReposClickListener()
    }

    private fun initReposClickListener() {
        binding.repoButton.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_reposFragment)
        }
    }

    private fun initObserver() {
        viewModel.userLiveData.observe(viewLifecycleOwner) { dataState ->
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

    private fun updateUi(user: User) {
        with(binding) {
            loader.isVisible = false
            userName.text = user.name
            userBio.handleVisibilityByInput(user.bio)
            userCompany.handleVisibilityByInput(user.company)
            followersCount.text = user.followers.toString()
            followingCount.text = user.following.toString()
            glide.load(user.imageUrl).into(profileImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}