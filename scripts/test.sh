#!/usr/bin/env bash
set -euo pipefail
JUNIT_JAR="lib/junit-platform-console-standalone-1.12.2.jar"
if [[ ! -f "$JUNIT_JAR" ]]; then
  echo "JAR JUnit manquant: lancez ./scripts/setup-junit.sh" >&2; exit 1
fi
./scripts/build.sh
mkdir -p build/test-classes build/test-results
find src/test/java -name '*.java' -print0 | xargs -0 javac -encoding UTF-8 -cp "build/classes:$JUNIT_JAR" -d build/test-classes
java -jar "$JUNIT_JAR" execute --class-path "build/classes:build/test-classes" --scan-class-path --reports-dir build/test-results --fail-if-no-tests
