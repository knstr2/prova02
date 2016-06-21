package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.sql.SQLException;
import java.util.Random;

/**
 * Created by alunoanalise on 08/06/2016.
 */
public class Play extends AppCompatActivity {

    static final int NIVEL_FACIL = 7; //8 - 14 - 20 - 31
    static boolean BOTAO_VERDE = false;
    static boolean BOTAO_VERMELHO = false;
    static boolean BOTAO_AMARELO = false;
    static boolean BOTAO_AZUL = false;
    static boolean INICIO = false;
    int[] sequencia;
    private GeniusDAO geniusDAO;
    private Button btVerde, btVermelho, btAmarelo, btAzul, btIniciar;
    private int etapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        btVerde = (Button)findViewById(R.id.btVerde);
        btVermelho = (Button)findViewById(R.id.btVermelho);
        btAmarelo = (Button)findViewById(R.id.btAmarelo);
        btAzul = (Button)findViewById(R.id.btAzul);
        btIniciar = (Button)findViewById(R.id.btIniciar);

        etapa = 0;

        geniusDAO = new GeniusDAO(Play.this);
        try {
            geniusDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        btVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        btVermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btAmarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btIniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int teste;
                if(!INICIO){
                    INICIO = true;
                    sequencia = geniusDAO.getData();
                    for (int tupla : sequencia) {
                        teste = tupla;
                    }
                    
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(Play.this).create();
                    alertDialog.setTitle("Alerta!");
                    alertDialog.setMessage("O jogo já iniciou!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }



            }
        });

    }

    public int gerarAleatorio(){
        Random rand = new Random();
        int n = rand.nextInt(3);
        return n;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
