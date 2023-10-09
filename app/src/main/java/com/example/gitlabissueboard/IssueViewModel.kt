package com.example.testproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testproject.data.IssuesRepository
import com.example.testproject.data.model.Issue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IssueViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: IssuesRepository
) : ViewModel() {
    private val allIssues = repository.allIssues
    private val userInputQuery = MutableStateFlow("")

    val filterResults: Flow<List<Issue>> = allIssues.combine(userInputQuery) { a, b ->
        if (b.isNotEmpty()) {
            a.filter { it.title.contains(b, ignoreCase = true) }
        } else {
            a
        }
    }

    val openedIssues = filterResults.map { it.filter { it.state == "opened" } }
    val closedIssues = filterResults.map { it.filter { it.state == "closed" } }

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                repository.updateIssue()
            }
        }
    }

    fun filterIssueByTitle(query: String) {
        userInputQuery.value = query
    }
}