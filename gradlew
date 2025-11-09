#!/usr/bin/env sh
# Gradle wrapper shim: if gradle wrapper files exist, use them; otherwise try to run 'gradle wrapper' if gradle is installed.
if [ -f "./gradlew" ] && [ -x "./gradlew" ] && [ "$0" != "./gradlew" ]; then
  exec ./gradlew "$@"
fi

if [ -f "gradle/wrapper/gradle-wrapper.jar" ]; then
  java -jar gradle/wrapper/gradle-wrapper.jar "$@"
  exit $?
fi

if command -v gradle >/dev/null 2>&1; then
  echo "Running 'gradle wrapper' to generate wrapper files..."
  gradle wrapper
  exec ./gradlew "$@"
else
  echo "Gradle wrapper not present and 'gradle' not found in PATH."
  echo "Install Gradle or generate the wrapper by running 'gradle wrapper' on a machine with gradle."
  exit 1
fi
