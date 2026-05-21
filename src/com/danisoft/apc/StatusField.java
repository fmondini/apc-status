////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Proj: apc-status
/// File: StatusField.java
///
/// ENUM of ApcUpsD returned variables with Type and Description
/// 
/// NOTE: If you find a keyword not included here, please let me know
///       with an example of your output and I'll add it.
///
/// First Release: May 2015 by Fulvio Mondini (https://github.com/fmondini)
///
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.danisoft.apc;

public enum StatusField {

	APC			(StatusFieldType.STR, "Header record indicating the STATUS format revision level, the number of records that follow the APC statement, and the number of bytes that follow the record."),
	DATE		(StatusFieldType.DAT, "The date and time that the information was last obtained from the UPS."),
	HOSTNAME	(StatusFieldType.STR, "The name of the machine that collected the UPS data."),
	UPSNAME		(StatusFieldType.STR, "The name of the UPS as stored in the EEPROM or in the UPSNAME directive in the configuration file."),
	VERSION		(StatusFieldType.STR, "The apcupsd release number, build date, and platform."),
	CABLE		(StatusFieldType.STR, "The cable as specified in the configuration file (UPSCABLE)."),
	DRIVER		(StatusFieldType.STR, "The Driver type"),
	MODEL		(StatusFieldType.STR, "The UPS model as derived from information from the UPS."),
	UPSMODE		(StatusFieldType.STR, "The mode in which apcupsd is operating as specified in the configuration file (UPSMODE)"),
	STARTTIME	(StatusFieldType.DAT, "The time/date that apcupsd was started."),
	STATUS		(StatusFieldType.STR, "The current status of the UPS (ONLINE, ONBATT, etc.)"),
	LINEV		(StatusFieldType.DBL_STR, "The current line voltage as returned by the UPS."),
	LOADPCT		(StatusFieldType.DBL_STR, "The percentage of load capacity as estimated by the UPS."),
	BCHARGE		(StatusFieldType.DBL_STR, "The percentage charge on the batteries."),
	TIMELEFT	(StatusFieldType.DBL_STR, "The remaining runtime left on batteries as estimated by the UPS."),
	MBATTCHG	(StatusFieldType.INT_STR, "If the battery charge percentage (BCHARGE) drops below this value, apcupsd will shutdown your system. Value is set in the configuration file (BATTERYLEVEL)"),
	MINTIMEL	(StatusFieldType.INT_STR, "apcupsd will shutdown your system if the remaining runtime equals or is below this point. Value is set in the configuration file (MINUTES)"),
	MAXTIME		(StatusFieldType.INT_STR, "apcupsd will shutdown your system if the time on batteries exceeds this value. A value of zero disables the feature. Value is set in the configuration file (TIMEOUT)"),
	MAXLINEV	(StatusFieldType.STR, "The maximum line voltage since the UPS was started, as reported by the UPS"),
	MINLINEV	(StatusFieldType.STR, "The minimum line voltage since the UPS was started, as returned by the UPS"),
	OUTPUTV		(StatusFieldType.STR, "The voltage the UPS is supplying to your equipment"),
	SENSE		(StatusFieldType.STR, "The sensitivity level of the UPS to line voltage fluctuations."),
	DWAKE		(StatusFieldType.STR, "The amount of time the UPS will wait before restoring power to your equipment after a power off condition when the power is restored."),
	DSHUTD		(StatusFieldType.STR, "The grace delay that the UPS gives after receiving a power down command from apcupsd before it powers off your equipment."),
	DLOWBATT	(StatusFieldType.STR, "The remaining runtime below which the UPS sends the low battery signal. At this point apcupsd will force an immediate emergency shutdown."),
	LOTRANS		(StatusFieldType.DBL_STR, "The line voltage below which the UPS will switch to batteries."),
	HITRANS		(StatusFieldType.DBL_STR, "The line voltage above which the UPS will switch to batteries."),
	RETPCT		(StatusFieldType.STR, "The percentage charge that the batteries must have after a power off condition before the UPS will restore power to your equipment."),
	ITEMP		(StatusFieldType.STR, "Internal UPS temperature as supplied by the UPS."),
	ALARMDEL	(StatusFieldType.STR, "The delay period for the UPS alarm."),
	BATTV		(StatusFieldType.DBL_STR, "Battery voltage as supplied by the UPS."),
	LINEFREQ	(StatusFieldType.STR, "Line frequency in hertz as given by the UPS."),
	LASTXFER	(StatusFieldType.STR, "The reason for the last transfer to batteries."),
	NUMXFERS	(StatusFieldType.INT_STR, "The number of transfers to batteries since apcupsd startup."),
	XONBATT		(StatusFieldType.DAT, "Time and date of last transfer to batteries, or N/A."),
	TONBATT		(StatusFieldType.INT_STR, "Time in seconds currently on batteries, or 0."),
	CUMONBATT	(StatusFieldType.INT_STR, "Total (cumulative) time on batteries in seconds since apcupsd startup."),
	XOFFBATT	(StatusFieldType.DAT, "Time and date of last transfer from batteries, or N/A."),
	SELFTEST	(StatusFieldType.STR, "The results of the last self test, and may have the following values: OK: self test indicates good battery - BT: self test failed due to insufficient battery capacity - NG: self test failed due to overload - NO: No results (i.e. no self test performed in the last 5 minutes)"),
	STESTI		(StatusFieldType.STR, "The interval in hours between automatic self tests."),
	STATFLAG	(StatusFieldType.STR, "Status flag. English version is given by STATUS."),
	DIPSW		(StatusFieldType.STR, "The current dip switch settings on UPSes that have them."),
	REG1		(StatusFieldType.STR, "The value from the UPS fault register 1."),
	REG2		(StatusFieldType.STR, "The value from the UPS fault register 2."),
	REG3		(StatusFieldType.STR, "The value from the UPS fault register 3."),
	MANDATE		(StatusFieldType.STR, "The date the UPS was manufactured."),
	SERIALNO	(StatusFieldType.STR, "The UPS serial number."),
	BATTDATE	(StatusFieldType.STR, "The date that batteries were last replaced."),
	NOMOUTV		(StatusFieldType.STR, "The output voltage that the UPS will attempt to supply when on battery power."),
	NOMINV		(StatusFieldType.INT_STR, "The input voltage that the UPS is configured to expect."),
	NOMBATTV	(StatusFieldType.DBL_STR, "The nominal battery voltage."),
	NOMPOWER	(StatusFieldType.INT_STR, "The maximum power in Watts that the UPS is designed to supply."),
	HUMIDITY	(StatusFieldType.STR, "The humidity as measured by the UPS."),
	AMBTEMP		(StatusFieldType.STR, "The ambient temperature as measured by the UPS."),
	EXTBATTS	(StatusFieldType.STR, "The number of external batteries as defined by the user. A correct number here helps the UPS compute the remaining runtime more accurately."),
	BADBATTS	(StatusFieldType.STR, "The number of bad battery packs."),
	FIRMWARE	(StatusFieldType.STR, "The firmware revision number as reported by the UPS."),
	APCMODEL	(StatusFieldType.STR, "The old APC model identification code."),
	LASTSTEST	(StatusFieldType.DAT, "last Test."),
	END_APC		(StatusFieldType.DAT, "The time and date that the STATUS record was written."),
	UNKNOWN		(StatusFieldType.STR, "[UNKNOWN]");

	private final StatusFieldType	_Type;
	private final String			_Desc;

	// GETTERS
	public String			getKeyw() { return(this.toString().replace(" ", "_").toUpperCase()); }
	public StatusFieldType	getType() { return(this._Type); }
	public String			getDesc() { return(this._Desc); }

	/**
	 * Constructor
	 */
	StatusField(StatusFieldType type, String desc) {
		this._Type = type;
		this._Desc = desc;
    }

	/**
	 * <b>Get StatusField by Keyword</b><br>
	 * If the key contains a space will be substituted by an underscore<br>
	 * Example: The key <tt><b>END APC</b></tt> become <tt><b>END_APC</b><tt>
	 */
	public static StatusField getByKeyw(String keyw) {

		StatusField rc = UNKNOWN;

		for (StatusField X : StatusField.values())
			if (X.toString().toUpperCase().equals(keyw.replace(" ", "_").toUpperCase()))
				rc = X;

		return(rc);
	}

}
