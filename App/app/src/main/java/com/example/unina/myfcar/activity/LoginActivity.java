package com.example.unina.myfcar.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unina.myfcar.R;
import com.example.unina.myfcar.account.GestoreAccount;
import com.example.unina.myfcar.iclient.IGestoreAccount;

import java.io.IOException;

import business.server.iserver.IServerGestoreAccount;
import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class LoginActivity extends AppCompatActivity {

    private boolean accesso=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = (Button) findViewById(R.id.login);
//        while(!accesso) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Conn().execute();

                }
            });
//        }
    }

    class Conn extends AsyncTask<Void, Void, MainActivity> {

        @Override
        protected MainActivity doInBackground(Void... params) {
            Intent i = new Intent(getApplicationContext(),AccountActivity.class);
            EditText email = (EditText) findViewById(R.id.email_insert);
            EditText pass = (EditText) findViewById(R.id.password_insert);
            Looper.prepare();
            try {
                CallHandler callHandler = new CallHandler();
                Client client = new Client("192.168.1.62", 4456, callHandler);
                IServerGestoreAccount iservera = (IServerGestoreAccount) client.getGlobal(IServerGestoreAccount.class);

                IGestoreAccount gestorea = GestoreAccount.getInstance();
                String s = gestorea.login(email.getText().toString(),pass.getText().toString(),iservera);

                    if(s.equals("ok")) {
                        accesso = true;
                        Bundle data = new Bundle();
                        data.putString("email", email.getText().toString());
//                        data.putString("pass", pass.getText().toString());

                        i.putExtras(data);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(LoginActivity.this,s,Toast.LENGTH_SHORT).show();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            Looper.loop();
            return null;
        }

    }

    @Override
    protected void onResume(){
        super.onResume();

        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Conn().execute();

            }
        });
    }
}


//  TEST
//                Intent i = new Intent(getApplicationContext(),AccountActivity.class);
//
//                EditText email = (EditText) findViewById(R.id.email_insert);
//                EditText pass = (EditText) findViewById(R.id.password_insert);
//
//                CallHandler ch = new CallHandler();
//                try {
//                    String ip = new String("192.168.1.62");
//                    Client clienta = new Client(ip,4456, ch);
////                    Client clienta = new Client("localhost",4456, ch);
//                    IServerGestoreAccount iservera;
//                    iservera = (IServerGestoreAccount) clienta.getGlobal(IServerGestoreAccount.class);
//                    IGestoreAccount gestorea = GestoreAccount.getInstance();
//                    String s = gestorea.login(email.getText().toString(),pass.getText().toString(),iservera);
//
//                    if(s.equals("ok")) {
//                        Bundle data = new Bundle();
//                        data.putString("email", email.getText().toString());
//                        data.putString("pass", pass.getText().toString());
//
//                        i.putExtras(data);
//                        startActivity(i);
//                    }
//                    else
//                        Toast.makeText(LoginActivity.this,s,Toast.LENGTH_SHORT).show();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//
//