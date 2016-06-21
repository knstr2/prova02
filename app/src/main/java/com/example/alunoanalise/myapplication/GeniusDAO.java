package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alunoanalise on 12/05/2016.
 */
public class GeniusDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] colunas_tabela = {DatabaseHelper.COL_ID,
            DatabaseHelper.COL_SEQ_1,
            DatabaseHelper.COL_SEQ_2,
            DatabaseHelper.COL_SEQ_3,
            DatabaseHelper.COL_SEQ_4,
            DatabaseHelper.COL_SEQ_5,
            DatabaseHelper.COL_SEQ_6,
            DatabaseHelper.COL_SEQ_7,
            DatabaseHelper.COL_SEQ_8
    };

    public GeniusDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private Genius cursorToGenius(Cursor cursor) {
        Genius genius = new Genius();
        genius.setId(cursor.getLong(0));
        genius.setSeq_1(cursor.getInt(1));
        genius.setSeq_2(cursor.getInt(2));
        genius.setSeq_3(cursor.getInt(3));
        genius.setSeq_4(cursor.getInt(4));
        genius.setSeq_5(cursor.getInt(5));
        genius.setSeq_6(cursor.getInt(6));
        genius.setSeq_7(cursor.getInt(7));
        genius.setSeq_8(cursor.getInt(8));

        return genius;

    }

    public List<Genius> getAllGenius() {
        List<Genius> geniuss = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABELA_GENIUS, colunas_tabela, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Genius genius = cursorToGenius(cursor);
            geniuss.add(genius);
            cursor.moveToNext();

        }
        cursor.close();
        return geniuss;

    }

    public int getData()[] {

        Cursor cursor = database.query(DatabaseHelper.TABELA_GENIUS, colunas_tabela, null, null, null, null, null);

        int[] resultado = new int[cursor.getCount()];

        int iid = cursor.getColumnIndex(DatabaseHelper.COL_ID);
        int iiseq_1 = cursor.getColumnIndex(DatabaseHelper.COL_SEQ_1);
        int iiseq_2 = cursor.getColumnIndex(DatabaseHelper.COL_SEQ_2);
        int iiseq_3 = cursor.getColumnIndex(DatabaseHelper.COL_SEQ_3);
        int iiseq_4 = cursor.getColumnIndex(DatabaseHelper.COL_SEQ_4);
        int iiseq_5 = cursor.getColumnIndex(DatabaseHelper.COL_SEQ_5);
        int iiseq_6 = cursor.getColumnIndex(DatabaseHelper.COL_SEQ_6);
        int iiseq_7 = cursor.getColumnIndex(DatabaseHelper.COL_SEQ_7);
        int iiseq_8 = cursor.getColumnIndex(DatabaseHelper.COL_SEQ_8);

        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            resultado[i] = cursor.getInt(iid);
            resultado[i] = cursor.getInt(iiseq_1);
            resultado[i] = cursor.getInt(iiseq_2);
            resultado[i] = cursor.getInt(iiseq_3);
            resultado[i] = cursor.getInt(iiseq_4);
            resultado[i] = cursor.getInt(iiseq_5);
            resultado[i] = cursor.getInt(iiseq_6);
            resultado[i] = cursor.getInt(iiseq_7);
            resultado[i] = cursor.getInt(iiseq_8);

            i++;

        }

        return resultado;
    }

}