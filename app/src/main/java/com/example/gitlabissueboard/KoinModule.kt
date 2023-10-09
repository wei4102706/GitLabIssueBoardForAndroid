package com.example.testproject

import com.example.testproject.data.GitlabIssueRepository
import com.example.testproject.data.IssuesRepository
import com.example.testproject.data.local.DBDataSource
import com.example.testproject.data.local.IssueDatabase
import com.example.testproject.data.local.LocalDataSource
import com.example.testproject.data.remote.RemoteDataSource
import com.example.testproject.data.remote.RetrofitDataSource
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val myModule = module {
    factory {
        DBDataSource(
            IssueDatabase.getDatabase(androidContext()).issueDao()
        ) as LocalDataSource
    }

    factory { RetrofitDataSource() as RemoteDataSource }

    factory { GitlabIssueRepository(get(), get(), get(named("IODispatcher"))) as IssuesRepository }

    single(named("IODispatcher")) {
        Dispatchers.IO
    }

    viewModel {
        IssueViewModel(get(named("IODispatcher")), get())
    }
}