package com.example.multitrantest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.multitrantest.model.HistoryModel

// Data Access Object
@Dao
interface RoomDao {
    @Query("SELECT * FROM history_list")
    suspend fun getLastSearches(): List<HistoryModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSearch(search: HistoryModel)

    @Delete
    suspend fun deleteSearch(search: HistoryModel)
}
