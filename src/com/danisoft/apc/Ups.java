////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Proj: apc-status
/// File: Ups.java
///
/// Class to retrieve and store keys and values of ApcUpsD results
///
/// First Release: May 2015 by Fulvio Mondini (https://github.com/fmondini)
///
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.danisoft.apc;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * UPS Data Class
 */
public class Ups {

	private String			_SrvName;
	private int				_SrvPort;
	private Vector<String>	_RawData;
	private Vector<ApcData>	_VecData;

	// GETTERS
	public String			getSrvName() { return this._SrvName; }
	public int				getSrvPort() { return this._SrvPort; }
	public Vector<String>	getRawData() { return this._RawData; }
	public Vector<ApcData>	getVecData() { return this._VecData; }

	// SETTERS
	public void setSrvName(String s)			{ this._SrvName = s; }
	public void setSrvPort(int i)				{ this._SrvPort = i; }
	public void setRawData(Vector<String> v)	{ this._RawData = v; }
	public void setVecData(Vector<ApcData> v)	{ this._VecData = v; }

	/**
	 * Constructor
	 */
	public Ups(String srvName, int srvPort) {
		super();

		setSrvName(srvName);
		setSrvPort(srvPort);
		setRawData(new Vector<String>());
		setVecData(new Vector<ApcData>());
	}

	/**
	 * Return data in JSON format
	 */
	public JSONObject toJson() throws Exception {

		setVecData(_get_ups_data());

		JSONObject jRoot = new JSONObject();
		JSONObject jData = new JSONObject();

		for (ApcData apcData : getVecData()) {
			
			StatusField statusField = StatusField.getByKeyw(apcData.getKey());

			if (!statusField.equals(StatusField.UNKNOWN)) {

				// Known keyword, format value

				if (statusField.getType().equals(StatusFieldType.INT)) {
					jData.put(statusField.getKeyw().toLowerCase(), Integer.parseInt(apcData.getVal()));
				} else if (statusField.getType().equals(StatusFieldType.DBL)) {
					jData.put(statusField.getKeyw().toLowerCase(), Double.parseDouble(apcData.getVal()));
				} else if (statusField.getType().equals(StatusFieldType.DAT)) {
					jData.put(statusField.getKeyw().toLowerCase(), _normalize_datetime(apcData.getVal()));
				} else if (statusField.getType().equals(StatusFieldType.STR)) {
					jData.put(statusField.getKeyw().toLowerCase(), apcData.getVal());
				} else if (statusField.getType().equals(StatusFieldType.INT_STR)) {
					jData.put(statusField.getKeyw().toLowerCase(), Integer.parseInt(apcData.getVal().split(" ")[0]));
				} else if (statusField.getType().equals(StatusFieldType.DBL_STR)) {
					jData.put(statusField.getKeyw().toLowerCase(), Double.parseDouble(apcData.getVal().split(" ")[0]));
				} else
					throw new Exception(
						"Bad StatusFieldType '" + statusField.getType().toString() + "' for keyword '" + statusField.getKeyw() + "'"
					);

			} else {

				// Unknown keyword, can't process

				jData.put(
					apcData.getKey().toLowerCase(),
					"[ERROR] The ApcUpsD keyword '" + apcData.getKey() + "' found in raw data is unknown (not found in StatusField enum)"
				);
			}
		}

		jRoot.put("data", jData);

		// Raw Data

		JSONArray jaRaw = new JSONArray();

		for (String rawData : getRawData())
			jaRaw.put(rawData);

		jRoot.put("data_raw", jaRaw);

		return(jRoot);
	}

	/**
	 * Get UPS data from ApcUpsD
	 * @return Vector of ApcUpsD data
	 */
	private Vector<ApcData> _get_ups_data() throws Exception {

		Vector<ApcData> vecData = new Vector<ApcData>();

		Socket socket = new Socket(getSrvName(), getSrvPort());

		// Send command

		byte[] CmdStatus = { 0x00, 0x06, 's', 't', 'a', 't', 'u', 's' };
		OutputStream socketOutputStream = socket.getOutputStream();
		socketOutputStream.write(CmdStatus);

		// Get response

		int dataLen;
		String apcLine;

		InputStream socketInputStream = socket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(socketInputStream);

		// Read loop

		while (true) {

			dataLen = dataInputStream.read() * 256 + dataInputStream.read();

			if (dataLen == 0)
	        	break;

			apcLine = "";

			for (int i = 0; i < dataLen; i++)
				apcLine += (char) dataInputStream.read();

			apcLine = apcLine.replace("\n", "").trim();

			getRawData().add(apcLine);

			vecData.add(
				new ApcData(
					apcLine.split(": ")[0].trim(),	// Key
					apcLine.split(": ")[1].trim()	// Value
				)
			);
	    }

		// Done

		dataInputStream.close();
		socketOutputStream.close();
		socketInputStream.close();
		socket.close();

		return(vecData);
	}

	/**
	 * Normalize a date/time field from ApcUpsD
	 * @param apcDateTime Date/Time returned by ApcUpsD
	 * @return Date/Time in <tt>yyyy-MM-dd HH:mm:ss</tt> format
	 */
	private static String _normalize_datetime(String apcDateTime) {

		final String INP_DATETIME_MASK = "yyyy-MM-dd HH:mm:ss Z";
		final String OUT_DATETIME_MASK = "yyyy-MM-dd HH:mm:ss";

		final Date DEFAULT_DATETIME = Timestamp.valueOf("1900-01-01 00:00:00"); // Default on error

		// Convert to Date

		Date tmpDate = DEFAULT_DATETIME;

		try {
			tmpDate = new SimpleDateFormat(INP_DATETIME_MASK).parse(apcDateTime);
		} catch (Exception e) { }

		// Done

		return(new SimpleDateFormat(OUT_DATETIME_MASK, Locale.ROOT).format(tmpDate));
	}

}
