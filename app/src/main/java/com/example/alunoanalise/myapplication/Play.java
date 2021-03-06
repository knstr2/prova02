package com.example.alunoanalise.myapplication;

/**
 * Created by alunoanalise on 15/06/2016.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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
    static boolean INICIO = false;

    Timer timer = new Timer();
    ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);

    private GeniusDAO geniusDAO;
    private Button btVerde, btVermelho, btAmarelo, btAzul, btIniciar;
    private TextView vtFase, vtVidas, vtPontos;
    private int fase = 1;
    private List<Genius> geniuses;
    private Genius geniusAtual;
    private List<Integer> sequenciaAtual = new LinkedList<>();
    private int vidas;
    private int score;
    private boolean busyBlinking;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        btVerde = (Button) findViewById(R.id.btVerde);
        btVermelho = (Button) findViewById(R.id.btVermelho);
        btAmarelo = (Button) findViewById(R.id.btAmarelo);
        btAzul = (Button) findViewById(R.id.btAzul);
        btIniciar = (Button) findViewById(R.id.btIniciar);
        vtFase = (TextView) findViewById(R.id.activity_play_tv_fase);
        vtVidas = (TextView) findViewById(R.id.activity_play_tv_vidas);
        vtPontos = (TextView) findViewById(R.id.activity_play_tv_score);

        geniusDAO = new GeniusDAO(Play.this);
        try {
            geniusDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        restartPhases();
        drawScreen();

        btVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (INICIO) {
                    addSequence(VERDE);
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Play.this).create();
                    alertDialog.setTitle("Inicie o jogo!");
                    alertDialog.setMessage("O jogo ainda não foi iniciado");
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
        btVermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (INICIO) {
                    addSequence(VERMELHO);
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Play.this).create();
                    alertDialog.setTitle("Inicie o jogo!");
                    alertDialog.setMessage("O jogo ainda não foi iniciado");
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
        btAmarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (INICIO) {
                    addSequence(AMARELHO);
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Play.this).create();
                    alertDialog.setTitle("Inicie o jogo!");
                    alertDialog.setMessage("O jogo ainda não foi iniciado");
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
        btAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (INICIO) {
                    addSequence(AZUL);
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Play.this).create();
                    alertDialog.setTitle("Inicie o jogo!");
                    alertDialog.setMessage("O jogo ainda não foi iniciado");
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
        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (INICIO) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Play.this).create();
                    alertDialog.setTitle("O jogo já iniciou!");
                    alertDialog.setMessage("O jogo já iniciou");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } else {
                    INICIO = true;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (!INICIO) {
                                cancel();
                            }

                            if (!busyBlinking) {
                                score--;
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    drawScreen();
                                }
                            });

                        }
                    }, 1000, 1000);
                    startPhase();
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
                intent = new Intent(Play.this, MainActivity.class);
                startActivity(intent);

                break;
        }

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
//        client.connect();
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
//        AppIndex.AppIndexApi.start(client, viewAction);
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
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }

    private void blinkButton(int i) {
        runOnUiThread(new BlinkThread(i));
    }

    void drawScreen() {
        vtFase.setText("FASE: " + fase);
        vtVidas.setText("VIDAS: " + vidas);
        vtPontos.setText("PONTOS: " + score);
    }

    private void startPhase() {

        drawScreen();

        // Set genius
        geniuses = geniusDAO.getAllGenius();
        for (Genius genius : geniuses) {
            if (genius.getFase() == fase) {
                geniusAtual = genius;
            }
        }

        // Clear sequence
        sequenciaAtual.clear();

        // Blink current sequence
        timer.schedule(new BlinkGeniusTask(geniusAtual), 300, 500);

    }

    /**
     *
     */
    private void restartPhases() {
        fase = 1;
        vidas = 3;
        INICIO = false;
        score = 1000;
    }

    /**
     * Start next phase or win
     */
    private void startNextPhase() {

        // Phases are over? You win
        fase++;
        if (geniusDAO.getAllGenius().size() < fase) {
            Intent intent = new Intent(Play.this, RankingInserir.class);
            Bundle b = new Bundle();
            b.putInt("pontos", score);
            intent.putExtras(b);
            restartPhases();
            startActivity(intent);

            // Start with this phase
        } else {
            drawScreen();
            startPhase();
        }
    }

    /**
     * Current genius' next sequence
     *
     * @return
     */

    private int getNextSequence() {

        int nextSequence = 0;

        switch (sequenciaAtual.size()) {
            case 0:
                nextSequence = geniusAtual.getSeq_1();
                break;
            case 1:
                nextSequence = geniusAtual.getSeq_2();
                break;
            case 2:
                nextSequence = geniusAtual.getSeq_3();
                break;
            case 3:
                nextSequence = geniusAtual.getSeq_4();
                break;
            case 4:
                nextSequence = geniusAtual.getSeq_5();
                break;
            case 5:
                nextSequence = geniusAtual.getSeq_6();
                break;
            case 6:
                nextSequence = geniusAtual.getSeq_7();
                break;
            case 7:
                nextSequence = geniusAtual.getSeq_8();
                break;
            default:
                // Do nothing
                break;
        }

        return nextSequence;
    }

    private void addSequence(int buttonindex) {
        if (vidas <= 0) {
            restartPhases();

        } else {
            // Do nothing while sequence is being shown
            if (busyBlinking) {
                return;
            }

            // Beep&blink
            blinkButton(buttonindex);

            if (buttonindex == getNextSequence()) {
                sequenciaAtual.add(buttonindex);
            } else {
                vidas--;
                score = score - 100;
                drawScreen();
                if (vidas == 0) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Play.this).create();
                    alertDialog.setTitle("Fim de jogo");
                    alertDialog.setMessage("Você não tem mais vidas!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Play.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                    alertDialog.show();

//                restartPhases();
//                startPhase();
                    return;
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Play.this).create();
                    alertDialog.setTitle("Sequência errada!");
                    alertDialog.setMessage("Você ainda tem " + vidas + " vidas!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    startPhase();
                                }
                            });
                    alertDialog.show();
                    return;
                }
            }

            if (sequenciaAtual.size() == 8) {

                // Let the user know
                AlertDialog alertDialog = new AlertDialog.Builder(Play.this).create();
                alertDialog.setTitle("Que bom!");
                alertDialog.setMessage("Voce ganhu a fase " + fase + "!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startNextPhase();
                            }
                        });
                alertDialog.show();
            }
        }
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

            busyBlinking = true;

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
                    blinkButton(-1);
                    busyBlinking = false;
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
            animation.setFillAfter(false);
            animation.setRepeatCount(3);

            btVerde.setVisibility(View.VISIBLE);
            btVermelho.setVisibility(View.VISIBLE);
            btAmarelo.setVisibility(View.VISIBLE);
            btAzul.setVisibility(View.VISIBLE);

            switch (buttonindex) {
                case VERDE:
                    toneG.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 100);
                    btVerde.startAnimation(animation);
//                    btVerde.setVisibility(View.INVISIBLE);
                    //                  btVerde.setVisibility(View.VISIBLE);
                    break;
                case VERMELHO:
                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);
                    btVermelho.startAnimation(animation);
//                    btVermelho.setVisibility(View.INVISIBLE);
                    //                  btVermelho.setVisibility(View.VISIBLE);
                    break;
                case AMARELHO:
                    toneG.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP, 100);
                    btAmarelo.startAnimation(animation);
//                    btAmarelo.setVisibility(View.INVISIBLE);
                    //                  btAmarelo.setVisibility(View.VISIBLE);
                    break;
                case AZUL:
                    toneG.startTone(ToneGenerator.TONE_CDMA_HIGH_L, 100);
                    btAzul.startAnimation(animation);
//                    btAzul.setVisibility(View.INVISIBLE);
                    //                  btAzul.setVisibility(View.VISIBLE);
                    break;
                default:
                    // do nothing
                    break;
            }

        }
    }

}
