package com.example.testproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "issues")
data class Issue(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val state: String,
    val created_at: Date
)