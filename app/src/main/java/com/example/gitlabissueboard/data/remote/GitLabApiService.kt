package com.example.testproject.data.remote

import com.example.testproject.data.model.Issue
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://gitlab.kdanmobile.com/"

private val gson = GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    .create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface GitLabApiService {
    @GET("api/v4/groups/8/issues")
    suspend fun getIssues(
        @Query("private_token") token: String,
        @Query("per_page") amount: Int
    ): List<Issue>
}

object GitLabApi {
    val retrofitService: GitLabApiService by lazy {
        retrofit.create(GitLabApiService::class.java)
    }
}
