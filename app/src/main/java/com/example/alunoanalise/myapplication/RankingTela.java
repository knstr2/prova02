package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.List;

public class RankingTela extends AppCompatActivity {

    String[] VetorLista;
    private ListView lvRanking;
    private RankingDAO rankingDAO;
    private List lista;
    private Ranking ranking = new Ranking();
//    public List<Ranking> listaString = new ArrayList<Ranking>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        lvRanking = (ListView) findViewById(R.id.lvRanking);
        rankingDAO = new RankingDAO(RankingTela.this);
        try {
            rankingDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lista = rankingDAO.getAllRanking();

        VetorLista = new String[lista.size()];


        for (int i = 0; i < lista.size(); i++) {
            Ranking ranking = (Ranking) lista.get(i);
            VetorLista[i] = i+1 + ": " + ranking.getNome()+ ": " + ranking.getPontos();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RankingTela.this, android.R.layout.simple_list_item_1, VetorLista);
        lvRanking.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voltar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;

        switch(id) {
            case R.id.mvoltar:
                intent = new Intent(RankingTela.this, MainActivity.class);
                startActivity(intent);

                break;
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
