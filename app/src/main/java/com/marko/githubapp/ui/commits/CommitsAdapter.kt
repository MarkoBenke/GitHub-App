package com.marko.githubapp.ui.commits

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marko.githubapp.R
import com.marko.githubapp.databinding.CommitItemBinding
import com.marko.githubapp.domain.commit.Commit
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

/**
 * Adapter class for list of commits
 *
 * @param context context
 */
class CommitsAdapter @Inject constructor(
    @ActivityContext val context: Context,
) : ListAdapter<Commit, CommitsAdapter.CommitsViewHolder>(CommitsDiffCallback()) {

    inner class CommitsViewHolder(val binding: CommitItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder {
        return CommitsViewHolder(
            CommitItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommitsViewHolder, position: Int) {
        val currentCommit = getItem(position)

        with(holder.binding) {
            authorName.text = currentCommit.commitData.authorName
            date.text = currentCommit.commitData.commitDate
            commitMessage.text = currentCommit.commitData.message
            sha.text = context.getString(R.string.sha, currentCommit.sha)
            numOfComments.text =
                context.getString(R.string.comments_number, currentCommit.commitData.commentCount)
            verifiedIcon.setImageResource(
                if (currentCommit.commitData.isVerified)
                    R.drawable.ic_verified else R.drawable.ic_not_verified
            )
            openCommit.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(currentCommit.commitUrl)
                }
            }
        }
    }

    var onItemClickListener: ((String) -> Unit)? = null

    fun setCommitUrlClickListener(listener: ((String) -> Unit)?) {
        onItemClickListener = listener
    }
}

class CommitsDiffCallback : DiffUtil.ItemCallback<Commit>() {

    override fun areItemsTheSame(oldItem: Commit, newItem: Commit): Boolean {
        return oldItem.sha == newItem.sha
    }

    override fun areContentsTheSame(oldItem: Commit, newItem: Commit): Boolean {
        return oldItem == newItem
    }
}