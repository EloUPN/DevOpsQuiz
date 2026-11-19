#!/usr/bin/env bash
set -euo pipefail
mkdir -p lib
curl -fL --retry 3 -o lib/junit-platform-console-standalone-1.12.2.jar \
  https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-console-standalone/1.12.2/junit-platform-console-standalone-1.12.2.jar
