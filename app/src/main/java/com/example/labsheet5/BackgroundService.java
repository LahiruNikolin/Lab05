package com.example.labsheet5;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

public class BackgroundService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BackgroundService(String name) {
        super(name);
    }

    public static void startAction(Context cxt){
        Intent intent=new Intent(cxt,BackgroundService.class);

        cxt.startActivity(intent);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if(intent!=null){
            for(int i=0;i<5;i++){
                Intent localBroadInt=new Intent(MainActivity.BROADCAST_ACTION);
                localBroadInt.putExtra("value","Broadcast "+(i+1));
                try {
                    Thread.sleep(100);

                }
                catch (InterruptedException e){
                    e.printStackTrace();

                }
                sendBroadcast(localBroadInt);
            }
        }

    }
}
