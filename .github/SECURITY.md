# Security policy

## Supported versions

Only the latest release on the `main` branch receives fixes.

## Reporting a vulnerability

Report it privately through [GitHub private vulnerability reporting](https://github.com/Joako601/market-backend/security/advisories/new). Do not open a public issue.

Include the affected endpoint or file, the steps to reproduce and the impact you expect.

This is a personal project maintained in spare time. Reports are handled on a best-effort basis, with no guaranteed response time. Confirmed issues are fixed in a new release and disclosed in a GitHub security advisory.

## Known limitations

These are design limits, not vulnerabilities. Reports about them will be closed:

- The API has no authentication or authorization. Anyone who can reach the port can read, create and delete data. Run it locally or on a trusted network.
- CORS allows every origin (`*`).
- The `clientes` table stores personal data (name, phone, address, email). The sample data in `db/market.sql` is fictional. Do not load real customer data into a deployment without adding authentication first.
