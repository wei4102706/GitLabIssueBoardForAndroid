package com.example.gitlabissueboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitlabissueboard.databinding.FragmentAllBinding
import com.example.gitlabissueboard.IssueViewModel
import com.example.gitlabissueboard.adapter.IssueListAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AllFragment : Fragment() {
    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel by sharedViewModel<IssueViewModel>()

    private var issueAdapter = IssueListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvAllIssues.layoutManager = linearLayoutManager
        binding.rvAllIssues.setHasFixedSize(true)
        binding.rvAllIssues.adapter = issueAdapter

        sharedViewModel.filterResults.asLiveData().observe(viewLifecycleOwner) { issueList ->
            issueAdapter.submitList(issueList.sortedByDescending { it.created_at })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}