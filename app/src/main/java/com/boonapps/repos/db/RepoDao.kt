package com.boonapps.repos.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.boonapps.repos.models.Repo

@Dao
interface RepoDao {

    @Query("SELECT * FROM repo")
    fun getAll(): List<Repo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<Repo>)
}