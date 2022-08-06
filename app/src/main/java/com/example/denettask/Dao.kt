package com.example.denettask

import androidx.room.*
import androidx.room.Dao


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNode(node: DBNode)

    @Update
    suspend fun updateNode(node: DBNode)

    @Delete
    suspend fun deleteNode(node: DBNode)

    @Query("DELETE FROM DBNode")
    suspend fun dropTable()

    @Query("SELECT * FROM DBNode WHERE name == :name")
    suspend fun getNodeByName(name: String): DBNode?

    @Query("SELECT * FROM DBNode WHERE id == :id")
    suspend fun getNodeById(id: Long): DBNode?

    @Query("SELECT * FROM DBNode WHERE parent == :parent")
    suspend fun getNodesByParent(parent: Long?): List<DBNode>

    @Query("SELECT * FROM DBNode WHERE parent IS NULL")
    suspend fun getRoot(): List<DBNode>
}
