@Echo OFF

REM ----------------------------------------------------------------------------------------------------
REM
REM Proj: apc-status
REM File: _runme.cmd
REM
REM Windog command example file to start ApcStatus java App
REM
REM First Release: May 2015 by Fulvio Mondini (https://github.com/fmondini)
REM
REM ----------------------------------------------------------------------------------------------------

Set APCUPSD_HOST_FQDN=host.fqdn.here

java -jar ApcStatus.jar %APCUPSD_HOST_FQDN%
