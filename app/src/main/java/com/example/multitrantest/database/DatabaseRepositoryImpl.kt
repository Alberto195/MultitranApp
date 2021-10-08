package com.example.multitrantest.database

import android.content.Context
import com.example.multitrantest.model.HistoryModel

class DatabaseRepositoryImpl(
    context: Context
) : DatabaseRepository {

    private val roomDao: RoomDao = RoomDataBase.getInstance(context).getRoomDao()

    override suspend fun insertHistory(search: HistoryModel) {
        roomDao.insertSearch(search)
    }

    override suspend fun deleteHistory(search: HistoryModel) {
        roomDao.deleteSearch(search)
    }

    override suspend fun getHistory(): List<HistoryModel> {
        return roomDao.getLastSearches()
    }
}