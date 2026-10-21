package com.surakshasetu;

import java.util.Locale;
import java.util.regex.Pattern;

public final class ThreatDetector {
    private ThreatDetector() {}
    private static final String[] TERMS = {"urgent", "verify", "kyc", "blocked", "suspended", "lottery", "prize", "refund", "cashback", "loan", "investment", "otp", "pin", "password", "customer care", "click", "upi", "रिफंड", "केवाईसी", "इनाम"};
    private static final Pattern URL = Pattern.compile("(https?://|www\\.|bit\\.ly|tinyurl\\.com|t\\.co)", Pattern.CASE_INSENSITIVE);
    public static int score(String input) {
        if (input == null) return 0; String s = input.toLowerCase(Locale.ROOT); int score = 0;
        for (String term : TERMS) if (s.contains(term)) score += 12;
        if (URL.matcher(s).find()) score += 25;
        if (s.matches(".*\\b\\d{4,8}\\b.*") && (s.contains("otp") || s.contains("pin"))) score += 25;
        if (s.contains("http://")) score += 15;
        return Math.min(score, 100);
    }
    public static String reason(String input) {
        int score = score(input); if (score >= 70) return "High risk: urgency, payment/credential language, or a suspicious link detected.";
        if (score >= 35) return "Possible scam: verify the sender independently before responding.";
        return "No obvious scam indicators found. Stay cautious with unexpected requests.";
    }
}
