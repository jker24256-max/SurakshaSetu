package com.surakshasetu;

import android.app.*; import android.content.*; import android.os.*;

public class ProtectionService extends Service {
    private static final int ID = 7;
    private final Handler pulse = new Handler(Looper.getMainLooper());
    private final String[] tips = {"Never share an OTP", "Pause before urgent payments", "Verify callers independently", "Report fraud quickly to 1930"};
    private int tipIndex = 0;
    public static void start(Context c) { Intent i = new Intent(c, ProtectionService.class); if (Build.VERSION.SDK_INT >= 26) c.startForegroundService(i); else c.startService(i); }
    @Override public int onStartCommand(Intent intent, int flags, int id) { startForeground(ID, notification()); pulse.removeCallbacksAndMessages(null); pulse.postDelayed(new Runnable(){ public void run(){ tipIndex=(tipIndex+1)%tips.length; ((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).notify(ID, notification()); pulse.postDelayed(this, 60000); } },60000); return START_STICKY; }
    private Notification notification() { String channel = "protection_quiet_v2"; NotificationManager nm = getSystemService(NotificationManager.class); if (Build.VERSION.SDK_INT >= 26) { NotificationChannel c=new NotificationChannel(channel, "Quiet protection status", NotificationManager.IMPORTANCE_MIN); c.setDescription("Required silent status for background protection"); c.setSound(null,null); c.enableVibration(false); nm.createNotificationChannel(c); } int incidents=getSharedPreferences("pulse",MODE_PRIVATE).getInt("incidents",0); return new Notification.Builder(this, channel).setContentTitle("SurakshaSetu protection active").setContentText(tips[tipIndex]+"  •  "+incidents+" alerts handled").setSmallIcon(com.surakshasetu.R.drawable.ic_launcher).setOngoing(true).setOnlyAlertOnce(true).setShowWhen(false).setCategory(Notification.CATEGORY_SERVICE).build(); }
    @Override public void onDestroy(){pulse.removeCallbacksAndMessages(null);super.onDestroy();}
    @Override public android.os.IBinder onBind(Intent i) { return null; }
}
