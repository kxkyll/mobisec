package com.factory.kxkyllon.first;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;


public class OwnActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.factory.kxkyllon.first.MESSAGE";
    public final static String PERMISSION_MESSAGE = "com.factory.kxkyllon.first.PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_own, menu);
        return true;
    }

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
    /**
     * Called when the user clicks the Send button
     *  -reads the text from the view text field
     *  -shows the text to user
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the Clarify button
     *  -reads the text from the view text field check_permissions
     *  -shows the text to user
     */

    public void clarifyPermissions(View view) {
        Intent intent = new Intent(this, DisplayPermissionActivity.class);
        EditText editText = (EditText) findViewById(R.id.check_permissions);
        String message = editText.getText().toString();
        /* CHECK HERE THE RIGHTS*/
        message = hasRights (message);
        intent.putExtra(PERMISSION_MESSAGE, message);
        startActivity(intent);
    }

    private String hasRights (String message) {
        int p = this.checkCallingOrSelfPermission(message);
        if (p == 0) {
            return message +" Permission ok";
        }
        return message + " No Permission";
    }
}
