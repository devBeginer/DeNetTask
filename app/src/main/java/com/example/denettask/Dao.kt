package com.example.denettask

import androidx.room.*
import androidx.room.Dao


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(node: DBNode)

    @Update
    suspend fun updateUser(node: DBNode)

    @Delete
    suspend fun deleteUser(node: DBNode)

    @Query("SELECT * FROM DBNode WHERE name == :name")
    suspend fun getNodeByName(name: String): DBNode?

    @Query("SELECT * FROM DBNode WHERE id == :id")
    suspend fun getNodeById(id: Long): DBNode?
}
