# SurakshaSetu Test Cases

## Detector cases

| ID | Input | Expected |
|---|---|---|
| T01 | `URGENT KYC blocked. Click http://example.com and share OTP` | High risk, score >= 70 |
| T02 | `Your refund is ready. Pay the UPI fee immediately` | Medium/high risk |
| T03 | `Reminder: team meeting at 4 PM` | Low risk, score 0 |
| T04 | Hindi message containing `केवाईसी`, `ओटीपी`, and a link | Risk detected |
| T05 | Empty input | No crash; no analysis result |

## User-flow cases

| ID | Flow | Expected |
|---|---|---|
| U01 | First launch | Onboarding explains permissions and local analysis |
| U02 | Notification access disabled | User sees setup guidance |
| U03 | Risky interaction | ScamPause explains signals and offers three choices |
| U04 | Recovery Mode | User can call 1930, save evidence, and return home |
| U05 | Save evidence | Capsule appears in Evidence History |
| U06 | Share evidence | Android share sheet opens only after user action |
| U07 | Restart phone | Foreground protection service can restart |
| U08 | Toggle settings | Settings persist after closing and reopening the app |

## Manual device test matrix

Test on at least Android 10+ devices from Pixel, Samsung, Xiaomi/Redmi, and OnePlus. Verify permission prompts, battery optimization behavior, SMS delivery, notification access, call-state behavior, accessibility, and dark/light system themes.

