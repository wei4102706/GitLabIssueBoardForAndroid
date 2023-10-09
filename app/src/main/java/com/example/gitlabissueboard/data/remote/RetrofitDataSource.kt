package com.example.testproject.data.remote

import com.example.testproject.data.model.Issue

private const val PRIVATE_TOKEN = "glpat-5VcymFoPybNyWx3n4Uhc"
private const val DEFAULT_AMOUNT = 50

class RetrofitDataSource : RemoteDataSource() {
    override suspend fun getIssuesFromRemote(): List<Issue> {
        return GitLabApi.retrofitService.getIssues(PRIVATE_TOKEN, DEFAULT_AMOUNT)
    }
}