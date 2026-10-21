package com.surakshasetu;
import android.content.*; import android.provider.Telephony; import android.telephony.SmsMessage;
public class SmsReceiver extends BroadcastReceiver {
 public void onReceive(Context c, Intent i) { if (!Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(i.getAction())) return; for (SmsMessage m : Telephony.Sms.Intents.getMessagesFromIntent(i)) { String body=m.getMessageBody(); int s=ThreatDetector.score(body); if(s>=35) Alert.show(c,"Suspicious message detected",ThreatDetector.reason(body)); } }
}
