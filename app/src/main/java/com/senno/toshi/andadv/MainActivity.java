/**
 * Author: Kimitoshi Senno
 * Date: 	Sep 01, 2014
 * For:	CS311D Homework 1
 * File:	AndAdv/app/src/main/java/com/senno/toshi/andadv/MainActivity.java
 * Purpose:Main Activity for the application
 *
 * Revised: Sep 06, 2014
 * For: Homework 2
 * Change: Add button to create Notification
 */

package com.senno.toshi.andadv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button btnMsg;
    private EditText eTxt;

    private Intent i;
    public static String BSTRING="toshi.AndAdv.msg1";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting UIs
        btnMsg=(Button)findViewById(R.id.btnMsg);
        btnMsg.setOnClickListener(this);
        eTxt=(EditText) findViewById(R.id.eTxt);

        // Broadcasting message
        i = new Intent();
        i.setAction(BSTRING);
    }

    public void onClick(View v)
    {
        SpannableStringBuilder sb = (SpannableStringBuilder) eTxt.getText();
        String inputMsg = sb.toString();

        i.putExtra("kMsg1", inputMsg);
        sendBroadcast(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
