package com.marko.githubapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.RequestManager
import com.marko.githubapp.databinding.UserFragmentBinding
import com.marko.githubapp.domain.User
import com.marko.githubapp.util.BaseFragment
import com.marko.githubapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment: BaseFragment() {

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
    }

    private fun initObserver() {
        viewModel.userLiveData.observe(viewLifecycleOwner) { dataState ->
            when(dataState) {
                is DataState.Error -> {
                    binding.loader.isVisible = false
                    showErrorDialog()
                }
                DataState.Loading -> binding.loader.isVisible = true
                is DataState.Success -> {
                    binding.loader.isVisible = false
                    updateUi(dataState.data)
                }
            }
        }
    }

    private fun updateUi(user: User) {
        binding.userName.text = user.name
        binding.userBio.text = user.bio
        binding.followersCount.text = user.followers.toString()
        binding.followingCount.text = user.following.toString()
        binding.userCompany.text = user.company
        glide.load(user.imageUrl).into(binding.profileImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}