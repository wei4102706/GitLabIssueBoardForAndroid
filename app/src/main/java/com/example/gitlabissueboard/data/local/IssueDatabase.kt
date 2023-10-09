package com.example.testproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testproject.data.model.Issue

@Database(entities = [Issue::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class IssueDatabase : RoomDatabase() {
    abstract fun issueDao(): LocalIssueDao

    companion object {
        @Volatile
        var INSTANCE: IssueDatabase? = null

        fun getDatabase(context: Context): IssueDatabase {
            val temInstance = INSTANCE
            if (temInstance != null) {
                return temInstance
            }
            synchronized(this) {
                val issueDatabaseInstance = Room
                    .databaseBuilder(context, IssueDatabase::class.java, "notes")
                    .build()
                INSTANCE = issueDatabaseInstance
                return issueDatabaseInstance
            }
        }
    }
}