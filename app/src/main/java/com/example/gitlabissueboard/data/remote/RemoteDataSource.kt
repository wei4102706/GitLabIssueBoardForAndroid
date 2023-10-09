package com.example.testproject.data.remote

import com.example.testproject.data.model.Issue

abstract class RemoteDataSource {
    abstract suspend fun getIssuesFromRemote(): List<Issue>
}