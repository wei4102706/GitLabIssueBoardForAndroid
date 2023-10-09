package com.example.testproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testproject.data.model.Issue
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalIssueDao {
    @Query("SELECT * FROM issues")
    fun getAllIssues(): Flow<List<Issue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIssues(list: List<Issue>)

    // The following are no longer needed
    @Query("SELECT * FROM issues WHERE state=:state")
    fun getIssues(state: String): List<Issue>
}