////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Proj: apc-status
/// File: StatusFieldType.java
///
/// ENUM of ApcUpsD result types
///
/// First Release: May 2015 by Fulvio Mondini (https://github.com/fmondini)
///
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.danisoft.apc;

public enum StatusFieldType {

	INT,		// Integer
	DBL,		// Double
	DAT,		// Date or DateTime
	STR,		// String
	INT_STR,	// Integer + <space> + String -> Splitted at the first space and used as Integer
	DBL_STR;	// Double + <space> + String -> Splitted at the first space and used as Double
}
