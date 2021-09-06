package com.rockex6.app.foodsslots.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

class Dao {
    @Dao
    interface ListDao {
        @Query("SELECT * From ListName")
        fun getList(): List<Table.ListName>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insertNewList(list: Table.ListName)

        @Query("DELETE From ListName WHERE id=:id")
        fun deleteList(id: String)
//        @Delete
//        fun deleteList(id: String)
    }

    @Dao
    interface ListContentDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insertNewItem(newItem: Table.ListContent)


        @Query("SELECT *  From ListContent WHERE list_id = :id")
        fun getListContent(id: String): List<Table.ListContent>

        //單個刪除
        @Query("DELETE From ListContent WHERE id = :id")
        fun deleteContent(id: String)

        //表單被刪除時
        @Query("DELETE From ListContent WHERE list_id=:id")
        fun deleteContentWhenListDelete(id: String)
    }
}