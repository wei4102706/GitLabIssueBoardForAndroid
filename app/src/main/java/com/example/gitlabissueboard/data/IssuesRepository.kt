package com.example.testproject.data

import com.example.testproject.data.model.Issue
import kotlinx.coroutines.flow.Flow

interface IssuesRepository {
    val allIssues: Flow<List<Issue>>

    suspend fun updateIssue()

    suspend fun fetchFromRemote(): List<Issue>
}