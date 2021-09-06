package com.rockex6.app.foodsslots.db;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DaoListContentDao_Impl implements Dao.ListContentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Table.ListContent> __insertionAdapterOfListContent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteContent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteContentWhenListDelete;

  public DaoListContentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfListContent = new EntityInsertionAdapter<Table.ListContent>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `ListContent` (`id`,`list_id`,`content_name`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Table.ListContent value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getList_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getList_id());
        }
        if (value.getContent_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getContent_name());
        }
      }
    };
    this.__preparedStmtOfDeleteContent = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE From ListContent WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteContentWhenListDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE From ListContent WHERE list_id=?";
        return _query;
      }
    };
  }

  @Override
  public void insertNewItem(final Table.ListContent newItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfListContent.insert(newItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteContent(final String id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteContent.acquire();
    int _argIndex = 1;
    if (id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, id);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteContent.release(_stmt);
    }
  }

  @Override
  public void deleteContentWhenListDelete(final String id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteContentWhenListDelete.acquire();
    int _argIndex = 1;
    if (id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, id);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteContentWhenListDelete.release(_stmt);
    }
  }

  @Override
  public List<Table.ListContent> getListContent(final String id) {
    final String _sql = "SELECT *  From ListContent WHERE list_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfListId = CursorUtil.getColumnIndexOrThrow(_cursor, "list_id");
      final int _cursorIndexOfContentName = CursorUtil.getColumnIndexOrThrow(_cursor, "content_name");
      final List<Table.ListContent> _result = new ArrayList<Table.ListContent>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Table.ListContent _item;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpList_id;
        if (_cursor.isNull(_cursorIndexOfListId)) {
          _tmpList_id = null;
        } else {
          _tmpList_id = _cursor.getString(_cursorIndexOfListId);
        }
        final String _tmpContent_name;
        if (_cursor.isNull(_cursorIndexOfContentName)) {
          _tmpContent_name = null;
        } else {
          _tmpContent_name = _cursor.getString(_cursorIndexOfContentName);
        }
        _item = new Table.ListContent(_tmpId,_tmpList_id,_tmpContent_name);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
