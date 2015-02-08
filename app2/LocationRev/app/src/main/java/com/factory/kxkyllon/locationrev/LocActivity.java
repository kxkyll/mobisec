package com.factory.kxkyllon.locationrev;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Message;
import android.os.RemoteException;
import android.widget.EditText;


public class LocActivity extends Activity {

    Messenger myService = null;
    boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);

        if (savedInstanceState == null) {
            Fragment phfragment = new PlaceHolderFragment();
            //getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
            getFragmentManager().beginTransaction().add(3, phfragment).commit();
        }
        Intent intent = new Intent("com.factory.kxkyllon.locationrev.RemoteService");
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    public static class PlaceHolderFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            EditText v = new EditText(getActivity());
            v.setText("Jei new Fragment!");
            return v;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loc, menu);
        return true;
    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {

            myService = new Messenger(service);
            isBound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName className) {
            myService = null;
            isBound = false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        if (!isBound) {
            return;
        }

        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("MyString", "Message Received");
        msg.setData(bundle);

        try {
            myService.send(msg);
        } catch (RemoteException e ){
            e.printStackTrace();
        }
    }
}
