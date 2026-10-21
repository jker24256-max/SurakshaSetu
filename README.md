# SurakshaSetu

SurakshaSetu is a privacy-first Android safety layer for digital-fraud prevention. Its ScamPause experience detects a sequence of risk signals across calls, SMS, notifications, links, and payment language, then helps a user pause before sharing an OTP or sending money.

## Why it matters

Caller-ID tools identify individual numbers, but scams often unfold as a journey: an unknown call, urgent message, suspicious link, and payment request. SurakshaSetu explains that journey in plain language and gives the user a safe next step.

## Features

- ScamPause intervention flow with simple, non-technical guidance
- SMS, notification, and incoming-call protection services
- On-device risk scoring and Scam DNA explanations
- Quiet Safety Pulse background protection service
- Encrypted local Evidence History using Android Keystore AES-GCM
- Recovery Mode with bank, evidence, and 1930 reporting guidance
- Trusted-contact sharing flow
- First-run onboarding and persistent protection settings

## Build and install

Open the project in Android Studio, or use Android SDK 35 and Gradle 8.x:

```bash
./gradlew assembleDebug
```

Install the generated APK from `app/build/outputs/apk/debug/` on an Android test device. Review onboarding before granting permissions. Notification access is required for notification protection; SMS and phone permissions are used for their respective layers.

## Architecture

- `MainActivity`: ScamPause UI, onboarding, settings, recovery, and evidence history
- `ThreatDetector`: local rule-based risk scoring
- `SmsReceiver`: SMS signal intake
- `ThreatNotificationListener`: notification signal intake
- `CallReceiver`: incoming-call signal intake
- `ProtectionService`: quiet foreground protection service
- `SecureStore`: Android Keystore-backed encrypted evidence storage

## Privacy and safety

Core analysis is performed locally in this prototype. Evidence capsules are encrypted on-device and are not uploaded automatically. The app does not move money, request banking credentials, or guarantee detection of every scam. Users should independently verify warnings and contact their bank or 1930 after suspected fraud.

## Hack for Social Cause

Theme: Digital Safety & Cyber Fraud Awareness. The project targets elderly users, rural and semi-urban communities, first-time digital-payment users, and people with limited cybersecurity awareness.

## AI-use disclosure

AI tools assisted with brainstorming, code drafting, debugging, UI refinement, and documentation. The team remains responsible for the concept, design decisions, integration, testing, and final submission.

## License

MIT License. See `LICENSE`.
