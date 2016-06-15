package com.example.alunoanalise.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btJogar, btSobre, btRank;
    private Button btRankingInserir;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btJogar = (Button) findViewById(R.id.btJogar);
        btSobre = (Button) findViewById(R.id.btSobre);
        btRank = (Button) findViewById(R.id.btRank);
        btRankingInserir = (Button) findViewById(R.id.btRankingInserir);

        btJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Play.class);
                startActivity(intent);
            }
        });
        btSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Aluno.class);
                startActivity(intent);
            }
        });
        btRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, RankingTela.class);
                startActivity(intent);
            }
        });
        btRankingInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, RankingInserir.class);
                startActivity(intent);
            }
        });




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

        switch (id) {
            case R.id.mranking:
                intent = new Intent(MainActivity.this, RankingTela.class);
                startActivity(intent);

                break;
            case R.id.maluno:
                intent = new Intent(MainActivity.this, Aluno.class);
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
