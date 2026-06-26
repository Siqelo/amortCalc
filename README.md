# Amortisation Calculator - No Database Version

This version removes PostgreSQL and JPA persistence. The backend is stateless: it calculates the amortisation schedule and returns the response without saving calculation history.

## Included features

1. **Invoker propagation**
   - Frontend captures an email address.
   - Frontend sends:
     - `X-Invoker-Id: amort-capacitor-app`
     - `X-Invoker-Username: <email address>`
   - Backend reads those headers and creates an `InvokerContext`.

2. **Automated request ID**
   - `RequestIdFilter` checks for `X-Request-Id`.
   - If missing, it generates a UUID.
   - The same request ID is returned in the response header.

3. **No PostgreSQL**
   - Removed `spring-boot-starter-data-jpa`.
   - Removed PostgreSQL driver dependency.
   - Removed datasource configuration.
   - No calculation history is saved.

4. **Capacitor container**
   - Mobile project lives in `mobile-app`.
   - The web app lives in `mobile-app/www/index.html`.
   - Add Android/iOS platforms with Capacitor commands.

## Run backend

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8080
```

## Test API with curl

```bash
curl -X POST http://localhost:8080/api/amortisation \
  -H "Content-Type: application/json" \
  -H "X-Invoker-Id: amort-capacitor-app" \
  -H "X-Invoker-Username: user@example.com" \
  -d '{
    "principal": 70000,
    "annualRate": 10.5,
    "periodMonths": 48,
    "inceptionDate": "2026-06-25"
  }'
```

## Capacitor setup

From the `mobile-app` folder:

```bash
npm install
npx cap add android
npx cap add ios
npx cap sync
```

Open Android:

```bash
npx cap open android
```

Open iOS, macOS required:

```bash
npx cap open ios
```

## Important API URL for mobile

Inside `mobile-app/www/index.html`, update this line before testing on mobile:

```javascript
const API_URL = '/api/amortisation';
```

Use one of these values:

```javascript
// Android emulator
const API_URL = 'http://10.0.2.2:8080/api/amortisation';

// Real Android/iPhone on same Wi-Fi
const API_URL = 'http://YOUR_PC_WIFI_IP:8080/api/amortisation';

// Deployed backend
const API_URL = 'https://your-backend-url.com/api/amortisation';
```
