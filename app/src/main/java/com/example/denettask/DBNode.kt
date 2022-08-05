package com.example.denettask

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = DBNode::class,
        parentColumns = ["id"],
        childColumns = ["parent"],
        onDelete = CASCADE
    )]
)
data class DBNode(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,

    var parent: Long?,
)