package com.example.rishabhr.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "products.db";

    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_QUERY = "CREATE TABLE " + Product.TABLE_NAME + " ( " + Product._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Product.NAME + " TEXT, " + Product.COST + " DECIMAL(7, 2) );";
    public static final String DELETE_QUERY = "DROP TABLE IF EXISTS " + Product.TABLE_NAME;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_QUERY);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long add(String name, double cost) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Product.NAME, name);
        values.put(Product.COST, cost);

        return db.insert(Product.TABLE_NAME, null, values);
    }

    public long delete(long id) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = Product._ID + " = ?";
        String[] selectionArgs = { Long.toString(id) };
        return db.delete(Product.TABLE_NAME, selection, selectionArgs);
    }

    public void setList(Context context, ListView lvList) {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Product> products = new ArrayList<Product>();
        String[] projection = { Product._ID, Product.NAME, Product.COST };
        Cursor cursor = db.query(Product.TABLE_NAME, projection, null, null,null, null, null);

        String[] fromColumns = {Product._ID, Product.NAME, Product.COST};
        int[] toViews = {R.id.tvID, R.id.tvName, R.id.tvCost};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context, R.layout.layout_list, cursor, fromColumns, toViews, 0);
        lvList.setAdapter(adapter);
    }
}
