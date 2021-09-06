package com.rockex6.app.foodsslots.db

import androidx.annotation.WorkerThread

class ListRepository(private val ListDao: Dao.ListDao) {

    @WorkerThread
    suspend fun insertItem(entity: Table.ListName) {
//        ListDao.insertNewList(entity)
    }
}