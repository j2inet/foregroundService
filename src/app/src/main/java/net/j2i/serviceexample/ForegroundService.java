package net.j2i.serviceexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {
    public static final String SERVICE_CHANNEL = "ServiceChannel";

    public ForegroundService() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Resources resources = getResources();
        NotificationChannel serviceChannel = new NotificationChannel(
                SERVICE_CHANNEL, "Foreground service channel",
                NotificationManager.IMPORTANCE_LOW);
        serviceChannel.setLightColor(Color.BLUE);
        serviceChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendintIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, SERVICE_CHANNEL)
                .setContentTitle(resources.getString(R.string.service_title))
                .setContentText(resources.getString(R.string.service_description))
                .setSmallIcon(R.drawable.ic_service_notification)
                .setContentIntent(pendintIntent)
                .build();
        startForeground(1, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
