#!/usr/bin/env bash
set -euo pipefail
mkdir -p build/classes
find src/main/java -name '*.java' -print0 | xargs -0 javac -encoding UTF-8 -d build/classes
java -cp build/classes fr.miage.devopsquiz.Main --healthcheck
