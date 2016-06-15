package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */

import android.support.v7.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Random;


public class RankingInserir extends AppCompatActivity {

    private Button btConfirma;
    private TextView tvPontos;
    private EditText etInserirRanking;
    private RankingDAO rankingDAO;
    int delay = 0;

    private Ranking ranking = new Ranking();
//    public List<Ranking> listaString = new ArrayList<Ranking>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_inserir);
        btConfirma = (Button) findViewById(R.id.btConfirma);
        tvPontos = (TextView) findViewById(R.id.tvPontos);
        etInserirRanking = (EditText) findViewById(R.id.etInserirRanking);

//        final int pontos = Integer.valueOf(tvPontos.getText().toString());
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        final int pontos = getRandomNumber();
        tvPontos.setText(Integer.toString(pontos));

        rankingDAO = new RankingDAO(RankingInserir.this);
        try {
            rankingDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        btConfirma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String nome = etInserirRanking.getText().toString();
                if (nome.isEmpty()) {
                    alertDialog.setTitle("Alerta!");
                    alertDialog.setMessage("Preencher o nome!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(delay);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            rankingDAO.criaRanking(nome, pontos);
                        }
                    }).start();
                    Toast.makeText(RankingInserir.this, "Registro inserido!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public int getRandomNumber(){
        Random rand = new Random();
        int n = rand.nextInt(1000); // Gives n such that 0 <= n < 20
        return n;
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

        switch (id) {
            case R.id.mvoltar:
                intent = new Intent(RankingInserir.this, MainActivity.class);
                startActivity(intent);

                break;
        }
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
