#!/usr/bin/env bash

set -e

cmd="$@"

/opt/bin/start-xvfb.sh >/dev/null 2>&1 &

exec ${cmd}