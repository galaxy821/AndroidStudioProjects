/*
package com.sanghyun.galaxy821.study_20180720_chapter5_3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AddressData extends Service {
    public AddressData() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getStringExtra("START") == null){
            return Service.START_STICKY;
        }else{
            processIntentCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processIntentCommand(Intent intent){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
*/