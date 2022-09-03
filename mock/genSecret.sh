#!/usr/bin/env bash

# Due to security requirement, No password can be stored in git repo. Please generate it with current bash.
# Refer [DX-514](https://jira.eurekacloud.io/browse/DX-514).
# If you encounter any issue for current bash, just turn to joseph.cen@sap.com for help.

java -jar template-util-0.0.1.jar

# Known issue:
# 1) Maven connection refused
#   Solution:
#     Delete ~/.m2/settings.xml


