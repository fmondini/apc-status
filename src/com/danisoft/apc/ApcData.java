////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Proj: apc-status
/// File: ApcData.java
///
/// Class to map key and value for an ApcUpsD result
///
/// First Release: May 2015 by Fulvio Mondini (https://github.com/fmondini)
///
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.danisoft.apc;

/**
 * Received data from APC
 */
public class ApcData {

	private String _Key;
	private String _Val;

	// GETTERS
	public String getKey() { return this._Key; }
	public String getVal() { return this._Val; }

	// SETTERS
	public void setKey(String s) { this._Key = s; }
	public void setVal(String s) { this._Val = s; }

	/**
	 * Constructor
	 */
	public ApcData(String key, String val) {
		this._Key = key;
		this._Val = val;
	}

}
