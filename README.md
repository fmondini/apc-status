# ApcStatus
This simple application reads [ApcUpsD](https://sourceforge.net/projects/apcupsd/) output from a remote server and formats it into JSON.

### What it does
This is a Java 8+ application for querying a remote APC.
The data is read from the remote server on port 3551 and formatted to produce JSON output.

*NOTE: The remote server to which the UPS is connected must have port 3551 accessible from the network from which you are connecting.*

### Syntax

    java -jar ApcStatus.jar server.fqdn.tld

where `server.fqdn.tld` is the server [FQDN](https://en.wikipedia.org/wiki/Fully_qualified_domain_name) to which the UPS is connected.

### Returned Data

| | | | | |
|--|--|--|--|--|
|**rc**|`JSONObject`|**true**: Data Received|**false**: Error| |
|**app**|`JSONObject`|This App info| |
|**srv**|`JSONObject`|Remote  info| |
|**ups**|`JSONObject`|UPS info| |
| | |`JSONObject`|**data**|Normalized data|
| | |`JSONArray`|**data_raw**|Array of raw data from remote|
|**error**|`String`|Error message|(optional)|*NOTE: This field only exists in case of error*|

### Example output

	{
		"rc": true,
		"app": {
			"date": "2026-05-21",
			"vers": "0.9.0.rc",
			"name": "ApcStatus"
		},
		"srv": {
			"port": 3551,
			"name": "host.name.here"
		},
		"ups": {
			"data": {
				"apc": "001,038,0967",
				"date": "2026-05-21 17:05:44",
				"status": "ONLINE",
				"cable": "USB Cable",
				"driver": "USB UPS Driver",
				"version": "3.14.14 (31 May 2016) mingw",
				... etc ...
				"end_apc": "2026-05-21 17:05:44"
			},
			"data_raw": [
				"APC      : 001,038,0967",
				"DATE     : 2026-05-21 16:05:44 +0100",
				"STATUS   : ONLINE",
				"CABLE    : USB Cable",
				"DRIVER   : USB UPS Driver",
				"VERSION  : 3.14.14 (31 May 2016) mingw",
				... etc ...
				"END APC  : 2026-05-21 16:05:44 +0100"
			]
		}
	}

*NOTE: Integer and Double fields are converted to their corresponding JSON values ​​by removing the String part.*

### Example output (on error)

	{
		"rc": false,
		"app": {
			"date": "2026-05-21",
			"vers": "0.9.0.rc",
			"name": "ApcStatus"
		},
		"srv": {
			"port": 3551,
			"name": "host.fqdn.here"
		},
		"error": "java.net.UnknownHostException: host.fqdn.here"
	}

### History
|Date|Version|Description|
|--|--|--|
|2026-05-21|0.9.0.rc|First version, working but still needs to be thoroughly tested|

## Disclaimer
Please be aware that any information you may find here may be inaccurate, misleading, dangerous, addictive, unethical, or illegal.

None of the authors, contributors, vandals, administrators, or anyone else connected with this pages, in any way whatsoever, can be responsible for your use of the informations contained in or linked from these web pages.

**Neither Danisoft Srl nor Fulvio Mondini do not take any responsibility** and we are not liable for any damage caused through use of code, examples, products or services given through this website, be it indirect, special, incidental or consequential damages, including but not limited to damages for loss of business, loss of profits, interruption or the like.

If you need more information take a look at this [Terms and Conditions](https://danisoft.software/home/copyright.jsp) page (in italian).
