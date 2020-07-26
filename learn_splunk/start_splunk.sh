#!/bin/bash
#
# to start the splunk service
#
_password=${1:-Admin123}
echo "$_password"

docker run -d --rm -p 8000:8000 -e 'SPLUNK_START_ARGS=--accept-license' -e "SPLUNK_PASSWORD=$_password" --name=splunk  splunk/splunk:latest
