# Vulnerable Spring Boot (Gradle) App — OWASP ZAP Demo

This small Spring Boot application is intentionally insecure and designed **only** for security testing / training (e.g., OWASP ZAP demos).
Do **not** deploy this to production or expose it to untrusted networks.

## Vulnerabilities included (intentional)
- SQL Injection via `/users?name=...` and `/addUser` (string concatenation with user input).
- Reflected XSS via `/xss?input=...`.
- Exposed actuator endpoints (all endpoints enabled).
- H2 console enabled at `/h2-console`.
- No authentication on endpoints.
- Outdated Spring Boot version specified (on purpose for demo).

## Run
Requirements: Java 8+ and Gradle CLI installed.

1. From project root:
   ```
   gradle bootRun
   ```

2. App runs on http://localhost:8080
   - H2 console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:testdb`, user `sa`, empty password)
   - Users endpoint: http://localhost:8080/users
   - XSS endpoint: http://localhost:8080/xss?input=<script>alert(1)</script>

## Notes
This project is provided as-is for testing and learning how scanners like OWASP ZAP detect web vulnerabilities.
