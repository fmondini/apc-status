////////////////////////////////////////////////////////////////////////////////////////////////////
///
/// Proj: apc-status
/// File: GetStatus.java
///
/// Get Status from APC via NIS protocol and output a JSON file
///
/// First Release: May 2015 by Fulvio Mondini (https://github.com/fmondini)
///
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.danisoft.apc;

import org.json.JSONObject;

/**
 * Get Status from APC via NIS protocol
 */
public class GetStatus {

	private static final String APPNAME = "ApcStatus";
	private static final String VERSION = "0.9.0.rc";
	private static final String RELEASE = "2026-05-21";

	/**
	 * MAIN LINE
	 */
	public static void main(String[] args) {

		JSONObject jRoot = new JSONObject();

		try {

			if (args.length != 1)
				throw new Exception("Syntax: " + GetStatus.class.getSimpleName() + " <RemoteHostFqdn>");

			String host = args[0];
			Ups ups = new Ups(host, 3551);

			// App
			JSONObject jApp = new JSONObject();
			jApp.put("name", APPNAME);
			jApp.put("vers", VERSION);
			jApp.put("date", RELEASE);
			jRoot.put("app", jApp);

			// Server
			JSONObject jSrv = new JSONObject();
			jSrv.put("name", ups.getSrvName());
			jSrv.put("port", ups.getSrvPort());
			jRoot.put("srv", jSrv);

			// Ups
			jRoot.put("ups", ups.toJson());

			// Root
			jRoot.put("rc", true);

		} catch (Exception ee) {

			jRoot.put("rc", false);
			jRoot.put("error", ee.toString());
		}

		System.out.println(jRoot.toString(3));
	}

}
