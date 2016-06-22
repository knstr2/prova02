package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by alunoanalise on 08/06/2016.
 */
public class Play extends AppCompatActivity {

    static final int NIVEL_FACIL = 7; //8 - 14 - 20 - 31
    static private final int VERDE = 1;
    static private final int VERMELHO = 2;
    static private final int AMARELHO = 3;
    static private final int AZUL = 4;
    static boolean BOTAO_VERDE = false;
    static boolean BOTAO_VERMELHO = false;
    static boolean BOTAO_AMARELO = false;
    static boolean BOTAO_AZUL = false;
    static boolean INICIO = false;
    int[] sequencia;
    Timer timer = new Timer();
    private GeniusDAO geniusDAO;
    private Button btVerde, btVermelho, btAmarelo, btAzul, btIniciar;
    private int fase = 0;
    private List<Genius> geniuses;
    private Genius geniusAtual;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        btVerde = (Button) findViewById(R.id.btVerde);
        btVermelho = (Button) findViewById(R.id.btVermelho);
        btAmarelo = (Button) findViewById(R.id.btAmarelo);
        btAzul = (Button) findViewById(R.id.btAzul);
        btIniciar = (Button) findViewById(R.id.btIniciar);

        geniusDAO = new GeniusDAO(Play.this);
        try {
            geniusDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Inicializar fase em 1
        fase = 1;
        geniuses = geniusDAO.getAllGenius();
        for (Genius genius : geniuses) {
            if (genius.getFase() == 1) {
                geniusAtual = genius;
            }
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
        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int teste;
                if (!INICIO) {
                    INICIO = true;
//                    sequencia = geniusDAO.getData();
//                    for (int tupla : sequencia) {
//                        teste = tupla;
//                    }

                    // Blink current sequence
                    timer.schedule(new BlinkGeniusTask(geniusAtual), 300, 500);

                } else {
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public int gerarAleatorio() {
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Play Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alunoanalise.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Play Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.alunoanalise.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private void blinkButton(int i) {
        runOnUiThread(new BlinkThread(i));
    }

    /**
     * Blink a phase's sequence
     */
    private class BlinkGeniusTask extends TimerTask {

        private Genius genius;
        private int currentSequence = 0;

        public BlinkGeniusTask(Genius genius) {
            super();
            this.genius = genius;
        }

        @Override
        public void run() {

            // starts as 0
            currentSequence++;

            switch (currentSequence) {
                case 1:
                    blinkButton(genius.getSeq_1());
                    break;
                case 2:
                    blinkButton(genius.getSeq_2());
                    break;
                case 3:
                    blinkButton(genius.getSeq_3());
                    break;
                case 4:
                    blinkButton(genius.getSeq_4());
                    break;
                case 5:
                    blinkButton(genius.getSeq_5());
                    break;
                case 6:
                    blinkButton(genius.getSeq_6());
                    break;
                case 7:
                    blinkButton(genius.getSeq_7());
                    break;
                case 8:
                    blinkButton(genius.getSeq_8());
                    break;
                default:
                    cancel();
                    break;
            }
        }
    }

    /**
     * Blink on ui thread
     */

    private class BlinkThread implements Runnable {

        private int buttonindex;

        public BlinkThread(int i) {
            buttonindex = i;
        }

        @Override
        public void run() {

            final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
            animation.setDuration(50); // duration - half a second
//            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
//            animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
//            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
            //animation.setRepeatMode();
            animation.setFillAfter(false);

            switch (buttonindex) {
                case VERDE:
                    btVerde.startAnimation(animation);
                    break;
                case VERMELHO:
                    btVermelho.startAnimation(animation);
                    break;
                case AMARELHO:
                    btAmarelo.startAnimation(animation);
                    break;
                case AZUL:
                    btAzul.startAnimation(animation);
                    break;
                default:
                    // do nothing
                    break;
            }

        }
    }

}
