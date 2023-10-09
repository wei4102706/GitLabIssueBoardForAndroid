package com.example.gitlabissueboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitlabissueboard.IssueViewModel
import com.example.gitlabissueboard.adapter.IssueListAdapter
import com.example.gitlabissueboard.databinding.FragmentOpenBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OpenFragment : Fragment() {
    private var _binding: FragmentOpenBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel by sharedViewModel<IssueViewModel>()

    private var issueAdapter: IssueListAdapter = IssueListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOpenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvOpenIssues.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOpenIssues.setHasFixedSize(true)
        binding.rvOpenIssues.adapter = issueAdapter

        sharedViewModel.openedIssues.asLiveData().observe(viewLifecycleOwner) { issueList ->
            issueAdapter.submitList(issueList.sortedByDescending { it.created_at })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}