package com.boonapps.repos.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.boonapps.repos.models.Repo


@Database(entities = [Repo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}