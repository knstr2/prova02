package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

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
            DatabaseHelper.COL_NOME
//            DatabaseHelper.COL_EMAIL,
//            DatabaseHelper.COL_TELEFONE
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
        Genius pessoa = new Genius();
        pessoa.setId(cursor.getLong(0));
        pessoa.setNome(cursor.getString(1));
        pessoa.setEmail(cursor.getString(2));
        pessoa.setTelefone(cursor.getString(3));


        return pessoa;

    }

    public Genius criaGenius(String nome, String email, String telefone) {
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.COL_NOME, nome);
//        valores.put(DatabaseHelper.COL_EMAIL, email);
//        valores.put(DatabaseHelper.COL_TELEFONE, telefone);

        long insertId = database.insert(DatabaseHelper.TABELA_GENIUS, null, valores);

        Cursor cursor = database.query(DatabaseHelper.TABELA_GENIUS,
                colunas_tabela,
                DatabaseHelper.COL_ID + " = " + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Genius novoGenius = cursorToGenius(cursor);
        cursor.close();

        return novoGenius;
    }

    public void deletarGenius(Genius pessoa) {
        database.delete(DatabaseHelper.TABELA_GENIUS,
                DatabaseHelper.COL_NOME + " = '" + pessoa.getNome() + "' AND "
//                        DatabaseHelper.COL_EMAIL + " = '" + pessoa.getEmail() + "' AND " +
//                        DatabaseHelper.COL_TELEFONE + " = '" + pessoa.getTelefone() + "'"
                , null);
    }

    public void deletarNomeGenius(Genius pessoa) {
        database.delete(DatabaseHelper.TABELA_GENIUS,
                DatabaseHelper.COL_NOME + " = '" + pessoa.getNome() + "'", null);
    }

    public void atualizarGenius(Genius pessoa) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COL_NOME,pessoa.getNome());
        database.update(DatabaseHelper.TABELA_GENIUS,cv,DatabaseHelper.COL_ID + " = " + pessoa.getId(),null);
    }

    public List<Genius> getAllGenius() {
        List<Genius> pessoas = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABELA_GENIUS, colunas_tabela, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Genius pessoa = cursorToGenius(cursor);
            pessoas.add(pessoa);
            cursor.moveToNext();

        }
        cursor.close();
        return pessoas;

    }

    public String getData() {

        Cursor cursor = database.query(DatabaseHelper.TABELA_GENIUS, colunas_tabela, null, null, null, null, null);

        String resultado = "";

        int iid = cursor.getColumnIndex(DatabaseHelper.COL_ID);
        int iinome = cursor.getColumnIndex(DatabaseHelper.COL_NOME);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            resultado = resultado + cursor.getString(iid) + " " + cursor.getString(iinome)  + "\n";

        }

        return resultado;
    }

    public String[] getDataNome() {

        Cursor cursor = database.query(DatabaseHelper.TABELA_GENIUS, colunas_tabela, null, null, null, null, null);

        String[] resultado = new String[cursor.getCount()];

        int iinome = cursor.getColumnIndex(DatabaseHelper.COL_NOME);

        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            resultado[i] = cursor.getString(iinome);

            i++;
        }

        return resultado;
    }

}