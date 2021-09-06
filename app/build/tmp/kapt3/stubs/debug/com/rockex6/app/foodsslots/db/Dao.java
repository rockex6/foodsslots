package com.rockex6.app.foodsslots.db;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0005"}, d2 = {"Lcom/rockex6/app/foodsslots/db/Dao;", "", "()V", "ListContentDao", "ListDao", "app_debug"})
public final class Dao {
    
    public Dao() {
        super();
    }
    
    @androidx.room.Dao
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/rockex6/app/foodsslots/db/Dao$ListDao;", "", "deleteList", "", "id", "", "getList", "", "Lcom/rockex6/app/foodsslots/db/Table$ListName;", "insertNewList", "list", "app_debug"})
    public static abstract interface ListDao {
        
        @org.jetbrains.annotations.NotNull
        @androidx.room.Query(value = "SELECT * From ListName")
        public abstract java.util.List<com.rockex6.app.foodsslots.db.Table.ListName> getList();
        
        @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
        public abstract void insertNewList(@org.jetbrains.annotations.NotNull
        com.rockex6.app.foodsslots.db.Table.ListName list);
        
        @androidx.room.Query(value = "DELETE From ListName WHERE id=:id")
        public abstract void deleteList(@org.jetbrains.annotations.NotNull
        java.lang.String id);
    }
    
    @androidx.room.Dao
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\tH\'\u00a8\u0006\f"}, d2 = {"Lcom/rockex6/app/foodsslots/db/Dao$ListContentDao;", "", "deleteContent", "", "id", "", "deleteContentWhenListDelete", "getListContent", "", "Lcom/rockex6/app/foodsslots/db/Table$ListContent;", "insertNewItem", "newItem", "app_debug"})
    public static abstract interface ListContentDao {
        
        @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
        public abstract void insertNewItem(@org.jetbrains.annotations.NotNull
        com.rockex6.app.foodsslots.db.Table.ListContent newItem);
        
        @org.jetbrains.annotations.NotNull
        @androidx.room.Query(value = "SELECT *  From ListContent WHERE list_id = :id")
        public abstract java.util.List<com.rockex6.app.foodsslots.db.Table.ListContent> getListContent(@org.jetbrains.annotations.NotNull
        java.lang.String id);
        
        @androidx.room.Query(value = "DELETE From ListContent WHERE id = :id")
        public abstract void deleteContent(@org.jetbrains.annotations.NotNull
        java.lang.String id);
        
        @androidx.room.Query(value = "DELETE From ListContent WHERE list_id=:id")
        public abstract void deleteContentWhenListDelete(@org.jetbrains.annotations.NotNull
        java.lang.String id);
    }
}