package com.example.gitlabissueboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gitlabissueboard.data.model.Issue
import com.example.gitlabissueboard.databinding.ViewIssueItemBinding

class IssueListAdapter :
    ListAdapter<Issue, IssueListAdapter.IssueViewHolder>(object : DiffUtil.ItemCallback<Issue>() {
        override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
            return oldItem == newItem
        }
    }) {
    class IssueViewHolder(private val binding: ViewIssueItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(issue: Issue) {
            binding.tvFragmentIssueTitle.text = issue.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        return IssueViewHolder(
            ViewIssueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}