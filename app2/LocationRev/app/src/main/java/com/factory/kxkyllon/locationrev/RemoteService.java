package com.factory.kxkyllon.locationrev;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

/**
 * Created by kxkyllon on 8.2.2015.
 */
public class RemoteService extends Service {

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage (Message msg) {

            Bundle data = msg.getData();
            String dataString = data.getString("MyString");
            Toast.makeText(getApplicationContext(), dataString, Toast.LENGTH_SHORT).show();
        }
    }

    final Messenger myMessenger = new Messenger(new IncomingHandler());

    @Override
    public IBinder onBind(Intent intent) {

        return myMessenger.getBinder();
    }
}
