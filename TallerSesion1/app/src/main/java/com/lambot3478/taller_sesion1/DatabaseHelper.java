package com.lambot3478.taller_sesion1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    private static final String TABLE_NAME = "usuarios";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "nombre";
    private static final String COL_3 = "edad";
    private static final String COL_4 = "altura";
    private static final String COL_5 = "peso";
    private static final String COL_6 = "sexo";
    private static final String COL_7 = "sangre";
    private static final String COL_8 = "pronombre";

    public DatabaseHelper(Context context)
    {

        super(context, TABLE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT, " + COL_3 + " INT, "
                + COL_4 + " DOUBLE, " + COL_5 + " DOUBLE, " + COL_6 + " TEXT, " + COL_7 + " TEXT, " + COL_8 + " TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String nombre, int edad, double altura, double peso, String sexo, String sangre, String pronombre)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, edad);
        contentValues.put(COL_4, altura);
        contentValues.put(COL_5, peso);
        contentValues.put(COL_6, sexo);
        contentValues.put(COL_7, sangre);
        contentValues.put(COL_8, pronombre);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;

        return true;

    }

    public Cursor getData()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor data = db.rawQuery(query, null);

        return data;

    }

    public Cursor getUserData(int id)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + " = '" + id + "'";

        Cursor data = db.rawQuery(query, null);
        return data;

    }

    public void updateData(int id, String nombre, int edad, String sangre, String oldName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL_2 + " = '" + nombre + "', " + COL_3 + " = '" + edad + "', " + COL_7 + " = '" + sangre + "' WHERE "
                + COL_1 + " = '" + id + "' AND " + COL_2 + " = '" + oldName + "'";

        db.execSQL(query);

    }

    public void deleteData(int id, String nombre, int edad, String sangre)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_1 + " = '" + id + "' AND " + COL_2 + " = '" + nombre + "'";

        db.execSQL(query);

    }

}
