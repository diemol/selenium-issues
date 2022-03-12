#!/bin/bash

WDURL="http://localhost:4444/wd/hub";

CAPABILITIES='{"capabilities":{"firstMatch":[{"browserName":"MicrosoftEdge","ms:edgeChromium":true}]},"desiredCapabilities":{"tags":["mandarin.per.nicols.uk","PHP 7.4.28"],"browserName":"MicrosoftEdge","ms:edgeChromium":true,"browser":"edge","ignoreZoomSetting":false,"name":"Behat feature suite"}}'

RESPONSE=`curl -s  -H 'Content-Type: application/json; charset=utf-8' -d "${CAPABILITIES}" "${WDURL}/session"`
SESSIONID=`echo "${RESPONSE}" | python3 -c "import sys, json; print(json.load(sys.stdin)['value']['sessionId'])"`

echo $RESPONSE

echo "Deleting window:"
curl -X DELETE "${WDURL}/session/${SESSIONID}"
echo