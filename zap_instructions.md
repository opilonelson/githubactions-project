# OWASP ZAP Quick Scan Instructions

Use the included `zap_scan.sh` to run a baseline scan using the official ZAP Docker image.
Example (on macOS/Windows Docker Desktop):
```sh
./zap_scan.sh http://host.docker.internal:8080
```
On Linux, replace the target with `http://localhost:8080` if `host.docker.internal` is not available:
```sh
./zap_scan.sh http://localhost:8080
```

The script runs `zap-baseline.py` and writes `zap_report.html` into the current directory.
You can also start the ZAP container interactively and use the desktop UI on port 8081 (see docker-compose.yml).
