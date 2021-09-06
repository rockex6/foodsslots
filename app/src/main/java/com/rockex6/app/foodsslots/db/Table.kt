package com.rockex6.app.foodsslots.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

class Table {

    @Entity
    data class ListName(
        @PrimaryKey val id: String,
        @ColumnInfo var name: String
    ) {
        constructor(name: String) : this(UUID.randomUUID().toString(), name)
    }

    @Entity(
//        foreignKeys = [
//            ForeignKey(
//                entity = ListName::class,
//                parentColumns = arrayOf("id"),
//                childColumns = arrayOf("list_id"),
//                onDelete = ForeignKey.CASCADE
//            )
//        ]
    )
    data class ListContent(
        @PrimaryKey val id: String = UUID.randomUUID().toString(),
        @ColumnInfo var list_id: String,
        @ColumnInfo var content_name: String
    ) {
        constructor(list_id: String, content_name: String) : this(
            UUID.randomUUID().toString(),
            list_id,
            content_name
        )
    }
}