/**
 * Created by toshi on 2014/08/30.
 * Date: 	Sep 01, 2014
 * For:	CS311D Homework 1
 * File:	AndAdv/app/src/main/java/com/senno/toshi/andadv/DemoReceiver.java
 * Purpose:Receiver to receive intent
 */

package com.senno.toshi.andadv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DemoReceiver extends BroadcastReceiver
{

    private static String TAG = "Received Message: ";

    @Override
    public  void onReceive(Context c, Intent i)
    {
        String action = i.getAction();

        if(action != null &&
           action.equals("toshi.AndAdv.msg1"))
        {
            String msg = i.getStringExtra("kMsg1");
            Log.d(TAG, msg);

        }
    }
}
