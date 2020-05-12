package com.example.labsheet5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String BROADCAST_ACTION = "GO";
    Receiver LocalListener;
    public TextView msgs;
    public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgs=findViewById(R.id.bro_msg_tv);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalListener=new Receiver();
        IntentFilter intentFilter=new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(LocalListener,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(LocalListener);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn){
            BackgroundService.startAction(this.getApplicationContext());
        }
    }

    class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String curretTex=msgs.getText().toString();
            String message=intent.getStringExtra("value");
            curretTex+="\nReceived "+message;

            msgs.setText(curretTex);
        }
    }
}
