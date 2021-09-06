package com.rockex6.app.foodsslots.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ListDataBase_Impl extends ListDataBase {
  private volatile Dao.ListDao _listDao;

  private volatile Dao.ListContentDao _listContentDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ListName` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ListContent` (`id` TEXT NOT NULL, `list_id` TEXT NOT NULL, `content_name` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '92652ca98b35f30596162d588bb41ee5')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ListName`");
        _db.execSQL("DROP TABLE IF EXISTS `ListContent`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsListName = new HashMap<String, TableInfo.Column>(2);
        _columnsListName.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListName.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysListName = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesListName = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoListName = new TableInfo("ListName", _columnsListName, _foreignKeysListName, _indicesListName);
        final TableInfo _existingListName = TableInfo.read(_db, "ListName");
        if (! _infoListName.equals(_existingListName)) {
          return new RoomOpenHelper.ValidationResult(false, "ListName(com.rockex6.app.foodsslots.db.Table.ListName).\n"
                  + " Expected:\n" + _infoListName + "\n"
                  + " Found:\n" + _existingListName);
        }
        final HashMap<String, TableInfo.Column> _columnsListContent = new HashMap<String, TableInfo.Column>(3);
        _columnsListContent.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListContent.put("list_id", new TableInfo.Column("list_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListContent.put("content_name", new TableInfo.Column("content_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysListContent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesListContent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoListContent = new TableInfo("ListContent", _columnsListContent, _foreignKeysListContent, _indicesListContent);
        final TableInfo _existingListContent = TableInfo.read(_db, "ListContent");
        if (! _infoListContent.equals(_existingListContent)) {
          return new RoomOpenHelper.ValidationResult(false, "ListContent(com.rockex6.app.foodsslots.db.Table.ListContent).\n"
                  + " Expected:\n" + _infoListContent + "\n"
                  + " Found:\n" + _existingListContent);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "92652ca98b35f30596162d588bb41ee5", "b005ba8566fcc8d48f38b137edda5843");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "ListName","ListContent");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `ListName`");
      _db.execSQL("DELETE FROM `ListContent`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(Dao.ListDao.class, DaoListDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(Dao.ListContentDao.class, DaoListContentDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Dao.ListDao getListDao() {
    if (_listDao != null) {
      return _listDao;
    } else {
      synchronized(this) {
        if(_listDao == null) {
          _listDao = new DaoListDao_Impl(this);
        }
        return _listDao;
      }
    }
  }

  @Override
  public Dao.ListContentDao getListContentDao() {
    if (_listContentDao != null) {
      return _listContentDao;
    } else {
      synchronized(this) {
        if(_listContentDao == null) {
          _listContentDao = new DaoListContentDao_Impl(this);
        }
        return _listContentDao;
      }
    }
  }
}
