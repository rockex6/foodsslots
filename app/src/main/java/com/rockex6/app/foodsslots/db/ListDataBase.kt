package com.rockex6.app.foodsslots.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Table.ListName::class, Table.ListContent::class], version = 1)
abstract class ListDataBase : RoomDatabase() {
    abstract fun getListDao(): Dao.ListDao
    abstract fun getListContentDao(): Dao.ListContentDao


    companion object {
        private var INSTANCE: ListDataBase? = null

        //更新資料庫版本
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("DROP TABLE ListContent")
//                database.execSQL(
//                    "ALTER TABLE ListContent" +
//                            "DROP COLUMN id;"
//                )
            }
        }

        fun getDataBase(context: Context): ListDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ListDataBase::class.java,
                    ListDataBase::class.java.simpleName
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}