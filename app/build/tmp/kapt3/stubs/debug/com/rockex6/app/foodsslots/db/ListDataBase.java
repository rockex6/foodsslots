package com.rockex6.app.foodsslots.db;

import java.lang.System;

@androidx.room.Database(entities = {com.rockex6.app.foodsslots.db.Table.ListName.class, com.rockex6.app.foodsslots.db.Table.ListContent.class}, version = 1)
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/rockex6/app/foodsslots/db/ListDataBase;", "Landroidx/room/RoomDatabase;", "()V", "getListContentDao", "Lcom/rockex6/app/foodsslots/db/Dao$ListContentDao;", "getListDao", "Lcom/rockex6/app/foodsslots/db/Dao$ListDao;", "Companion", "app_debug"})
public abstract class ListDataBase extends androidx.room.RoomDatabase {
    private static com.rockex6.app.foodsslots.db.ListDataBase INSTANCE;
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @org.jetbrains.annotations.NotNull
    public static final com.rockex6.app.foodsslots.db.ListDataBase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull
    public abstract com.rockex6.app.foodsslots.db.Dao.ListDao getListDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.rockex6.app.foodsslots.db.Dao.ListContentDao getListContentDao();
    
    public ListDataBase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/rockex6/app/foodsslots/db/ListDataBase$Companion;", "", "()V", "INSTANCE", "Lcom/rockex6/app/foodsslots/db/ListDataBase;", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getDataBase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull
        public final com.rockex6.app.foodsslots.db.ListDataBase getDataBase(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}