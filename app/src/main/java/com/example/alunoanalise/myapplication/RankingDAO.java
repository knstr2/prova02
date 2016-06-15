package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankingDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] colunas_tabela = {DatabaseHelper.COL_ID,
            DatabaseHelper.COL_NOME,
            DatabaseHelper.COL_PONTOS};

    public RankingDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private Ranking cursorToRanking(Cursor cursor) {
        Ranking ranking = new Ranking();
        ranking.setId(cursor.getLong(0));
        ranking.setNome(cursor.getString(1));
        ranking.setPontos(cursor.getInt(2));

        return ranking;

    }

    public Ranking criaRanking(String nome, int pontos) {
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.COL_NOME, nome);
        valores.put(DatabaseHelper.COL_PONTOS, pontos);

        long insertId = database.insert(DatabaseHelper.TABELA_RANKING, null, valores);

        Cursor cursor = database.query(DatabaseHelper.TABELA_RANKING,
                colunas_tabela,
                DatabaseHelper.COL_ID + " = " + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Ranking novoRanking = cursorToRanking(cursor);
        cursor.close();

        return novoRanking;
    }

    public void deletarRanking(Ranking ranking) {
        database.delete(DatabaseHelper.TABELA_RANKING,
                DatabaseHelper.COL_NOME + " = '" + ranking.getNome() + "' AND " +
                        DatabaseHelper.COL_PONTOS + " = '" + ranking.getPontos() + "'", null);
    }

    public List<Ranking> getAllRanking() {
        List<Ranking> rankings = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABELA_RANKING, colunas_tabela, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ranking ranking = cursorToRanking(cursor);
            rankings.add(ranking);
            cursor.moveToNext();

        }
        cursor.close();
        return rankings;

    }

    public String getData() {

        Cursor cursor = database.query(DatabaseHelper.TABELA_RANKING, colunas_tabela, null, null, null, null, null);

        String resultado = "";

        int iid = cursor.getColumnIndex(DatabaseHelper.COL_ID);
        int iinome = cursor.getColumnIndex(DatabaseHelper.COL_NOME);
        int iipontos = cursor.getColumnIndex(DatabaseHelper.COL_PONTOS);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            resultado = resultado + cursor.getString(iid) + " " + cursor.getString(iinome) + " " + cursor.getString(iipontos) + "\n";

        }

        return resultado;
    }

}