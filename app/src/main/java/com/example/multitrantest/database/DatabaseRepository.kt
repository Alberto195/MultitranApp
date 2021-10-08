package com.example.multitrantest.database

import androidx.lifecycle.LiveData
import com.example.multitrantest.model.HistoryModel

interface DatabaseRepository {

    suspend fun insertHistory(search: HistoryModel)

    suspend fun deleteHistory(search: HistoryModel)

    suspend fun getHistory(): List<HistoryModel>
}
