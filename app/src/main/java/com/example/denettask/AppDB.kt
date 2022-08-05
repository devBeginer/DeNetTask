package com.example.denettask

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DBNode::class], version = 1, exportSchema = false
)
abstract class AppDB: RoomDatabase() {
    abstract fun dao(): Dao
}