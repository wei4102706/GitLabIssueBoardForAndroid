package com.example.testproject.data.local

import com.example.testproject.data.model.Issue
import kotlinx.coroutines.flow.Flow

class DBDataSource(private val dao: LocalIssueDao) : LocalDataSource() {

    override val allIssues: Flow<List<Issue>> = dao.getAllIssues()

    // insert  data into db
    override suspend fun insertListIntoDB(issueList: List<Issue>) {
        dao.insertIssues(issueList)
    }
}