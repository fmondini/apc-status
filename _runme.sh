#!/bin/bash

## ----------------------------------------------------------------------------------------------------
##
## Proj: apc-status
## File: _runme.sh
##
## Bash script example file to start ApcStatus java App
##
## First Release: May 2015 by Fulvio Mondini (https://github.com/fmondini)
##
## ----------------------------------------------------------------------------------------------------

APCUPSD_HOST_FQDN="host.fqdn.here"

java -jar ApcStatus.jar "${APCUPSD_HOST_FQDN}"
