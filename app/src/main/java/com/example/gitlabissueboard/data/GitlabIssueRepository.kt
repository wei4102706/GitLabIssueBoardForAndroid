package com.example.testproject.data

import androidx.annotation.VisibleForTesting
import com.example.testproject.data.local.LocalDataSource
import com.example.testproject.data.model.Issue
import com.example.testproject.data.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GitlabIssueRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : IssuesRepository {
    override val allIssues = localDataSource.allIssues

    override suspend fun updateIssue() {
        withContext(ioDispatcher) {
            val issueList = fetchFromRemote()
            localDataSource.insertListIntoDB(issueList)
        }
    }

    @VisibleForTesting
    override suspend fun fetchFromRemote(): List<Issue> {
        return remoteDataSource.getIssuesFromRemote()
    }
}
