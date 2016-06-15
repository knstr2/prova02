package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DBNAME = "DBGenius";
    static final String TABELA_GENIUS = "TabGenius";
    static final String COL_SEQ_1 = "seq_1";
    static final String COL_SEQ_2 = "seq_2";
    static final String COL_SEQ_3 = "seq_3";
    static final String COL_SEQ_4 = "seq_4";
    static final String COL_SEQ_5 = "seq_5";
    static final String COL_SEQ_6 = "seq_6";
    static final String COL_SEQ_7 = "seq_7";
    static final String COL_SEQ_8 = "seq_8";
    static final String TABELA_RANKING = "TabRanking";
    static final String COL_ID = "id";
    static final String COL_NOME = "nome";
    static final String COL_PONTOS = "pontos";
    static final int DBVERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL = "CREATE TABLE "+
                TABELA_GENIUS + " ("+
                COL_ID + " INTEGER PRIMARY KEY, " +
                COL_SEQ_1 + " INTEGER, " +
                COL_SEQ_2 + " INTEGER, " +
                COL_SEQ_3 + " INTEGER, " +
                COL_SEQ_4 + " INTEGER, " +
                COL_SEQ_5 + " INTEGER, " +
                COL_SEQ_6 + " INTEGER, " +
                COL_SEQ_7 + " INTEGER, " +
                COL_SEQ_8 + " INTEGER);";
        db.execSQL(SQL);

        String SQLInsert = " INSERT INTO " +
                TABELA_GENIUS + " ("+
                COL_SEQ_1 + ", " +
                COL_SEQ_2 + ", " +
                COL_SEQ_3 + ", " +
                COL_SEQ_4 + ", " +
                COL_SEQ_5 + ", " +
                COL_SEQ_6 + ", " +
                COL_SEQ_7 + ", " +
                COL_SEQ_8 + ")" +
                "VALUES (1,2,3,4,1,2,3,4);";
        SQLInsert += " INSERT INTO " +
                TABELA_GENIUS + " ("+
                COL_SEQ_1 + ", " +
                COL_SEQ_2 + ", " +
                COL_SEQ_3 + ", " +
                COL_SEQ_4 + ", " +
                COL_SEQ_5 + ", " +
                COL_SEQ_6 + ", " +
                COL_SEQ_7 + ", " +
                COL_SEQ_8 + ")" +
                "VALUES (1,2,3,4,1,2,3,4);";
        db.execSQL(SQLInsert);

        String SQL2 = "CREATE TABLE "+
                TABELA_RANKING + " ("+
                COL_ID + " INTEGER PRIMARY KEY, " +
                COL_NOME + " TEXT, "+
                COL_PONTOS + " INTEGER);";
        db.execSQL(SQL2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String SQL = "DROP TABLE IF EXISTS " + TABELA_GENIUS;
        db.execSQL(SQL);

        String SQL2 = "DROP TABLE IF EXISTS " + TABELA_RANKING;
        db.execSQL(SQL2);

    }
}
