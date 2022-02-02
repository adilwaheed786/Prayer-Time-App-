package com.example.prayertime;
import android.app.Service ;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AndriodServices extends Service {
    private MediaPlayer player;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player=MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        player.setLooping(true);
        player.start();
        Toast.makeText(this, "Alaram Start", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        Toast.makeText(this, "Alaram Stop", Toast.LENGTH_SHORT).show();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
