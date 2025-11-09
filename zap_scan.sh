#!/usr/bin/env sh
# Simple script to run OWASP ZAP baseline scan against http://host.docker.internal:8080 (or http://localhost:8080 on Linux)
TARGET=${1:-http://host.docker.internal:8080}

echo "Starting an OWASP ZAP baseline scan against $TARGET"
docker run --rm -v $(pwd):/zap/wrk/:rw -t owasp/zap2docker-stable zap-baseline.py -t "$TARGET" -r zap_report.html
echo "Scan complete. Report saved to ./zap_report.html (in the current directory)"
