package com.example.testproject.data.local

import com.example.testproject.data.DataSource
import com.example.testproject.data.model.Issue
import kotlinx.coroutines.flow.Flow

abstract class LocalDataSource : DataSource() {
    abstract val allIssues: Flow<List<Issue>>

    abstract suspend fun insertListIntoDB(issueList: List<Issue>)
}