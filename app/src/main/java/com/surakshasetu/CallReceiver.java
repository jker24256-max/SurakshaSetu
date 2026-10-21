package com.surakshasetu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/** Best-effort call protection for numbers reported by the phone system. */
public class CallReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
        if (!TelephonyManager.ACTION_PHONE_STATE_CHANGED.equals(intent.getAction())) return;
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (!TelephonyManager.EXTRA_STATE_RINGING.equals(state)) return;
        String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        if (number == null) return;
        if (isSuspicious(number)) Alert.show(context, "Unknown caller", "This caller is not verified. Never share OTPs, PINs, passwords, or remote-access codes.");
    }
    private boolean isSuspicious(String number) {
        String digits = number.replaceAll("[^0-9]", "");
        return digits.length() < 10 || digits.length() > 13;
    }
}
