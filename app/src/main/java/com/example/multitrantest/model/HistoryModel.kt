package com.example.multitrantest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "history_list", indices = [Index("id")])
data class HistoryModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val word: String = "",
    @ColumnInfo
    val lan1: String? = "",
    @ColumnInfo
    val lan2: String? = "",
) : Serializable