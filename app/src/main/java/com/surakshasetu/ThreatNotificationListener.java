package com.surakshasetu;
import android.service.notification.NotificationListenerService; import android.service.notification.StatusBarNotification;
public class ThreatNotificationListener extends NotificationListenerService { @Override public void onNotificationPosted(StatusBarNotification n) { if(n.getNotification()==null||n.getNotification().extras==null)return; CharSequence t=n.getNotification().extras.getCharSequence("android.text"); if(t!=null && ThreatDetector.score(t.toString())>=35) Alert.show(this,"Potential scam in notification",ThreatDetector.reason(t.toString())); } }
