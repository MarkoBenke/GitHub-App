package com.marko.githubapp.ui.repos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marko.githubapp.R
import com.marko.githubapp.databinding.RepoItemBinding
import com.marko.githubapp.domain.Repo
import com.marko.githubapp.util.handleVisibilityByInput
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ReposAdapter @Inject constructor(
    @ActivityContext val context: Context,
) : ListAdapter<Repo, ReposAdapter.ReposViewHolder>(RepoDiffCallback()) {

    inner class ReposViewHolder(val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        return ReposViewHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        val currentRepo = getItem(position)

        with(holder.binding) {
            title.text = currentRepo.name
            description.handleVisibilityByInput(currentRepo.description)
            language.handleVisibilityByInput(currentRepo.language)
            openIssues.text =
                context.getString(R.string.open_issues_count, currentRepo.openIssuesCount)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { click ->
                click(currentRepo)
            }
        }
    }

    var onItemClickListener: ((Repo) -> Unit)? = null

    fun setItemClickListener(listener: ((Repo) -> Unit)?) {
        onItemClickListener = listener
    }
}

class RepoDiffCallback : DiffUtil.ItemCallback<Repo>() {

    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}