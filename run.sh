#!/usr/bin/env bash
set -e
sh build.sh
docker run -p 8080:8080 --env dataingest_url=http://host.docker.internal:5000/api --env CLIENT_SECRET="4ad3c8ae-2359-44c1-af6a-59c3ce50e3f6" --env CLIENT_ID="APP-ENQTIY7Z904S6O1W" vui
