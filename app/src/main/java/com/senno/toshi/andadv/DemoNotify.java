/**
 * Author: Kimitoshi Senno
 * Date: 	Sep 06, 2014
 * For:	CS311D Homework 2
 * File:	AndAdv/app/src/main/java/com/senno/toshi/andadv/DemoNotify.java
 * Purpose: Create and sending notification
 */

package com.senno.toshi.andadv;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DemoNotify extends Activity implements View.OnClickListener
{
    private static final int NOTIFY_ME_ID = 1337;
    private NotificationManager mgr = null;

    private Button btnNoti;

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_notification);

        btnNoti = (Button) findViewById(R.id.btnNoti);
        btnNoti.setOnClickListener(this);

        // Create NotificationManager via service
        mgr=(NotificationManager) getSystemService((NOTIFICATION_SERVICE));
    }

    public void onClick(View v)
    {
        notifyMe(v);
    }

    public void notifyMe(View v)
    {
        /* A pending intent is a token that you give to another application
        (e.g., notification manager, alarm manager or other 3rd party applications),
        which allows this other application to use the permissions of your application
        to execute a predefined piece of code.
        To perform an activity via a pending intent,
        you receive the activity via PendingIntent.getActivity().*/

        PendingIntent pi = PendingIntent.getActivity(
                getBaseContext(),
                0,
                new Intent(getBaseContext(), DemoReceiver.class),
                0);

        // build notification
        Notification n;
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN)
        {
            // For old version
            n = new Notification();
            n.setLatestEventInfo(
                    getBaseContext(),
                    "Notification Title",
                    "This is the notification message",
                    pi);
            n.icon = R.drawable.ic_launcher;
            n.tickerText = "Notification for ICS or below";
            n.when = System.currentTimeMillis();
            n.flags |= Notification.FLAG_AUTO_CANCEL;
        }
        else
        {
            // For v5.x or above
            Notification.Builder nb = new Notification.Builder(getBaseContext())
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setAutoCancel(true)
                    .setTicker("Notification for Jellybean or above")
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("Notification Title")
                    .setContentText("This is the notification message")
                    .setContentIntent(pi);

            n = nb.build();
        }

        mgr.notify(NOTIFY_ME_ID, n);

        clearNotification(v);
    }

    public void clearNotification(View v)
    {
        mgr.cancel(NOTIFY_ME_ID);
    }
}
