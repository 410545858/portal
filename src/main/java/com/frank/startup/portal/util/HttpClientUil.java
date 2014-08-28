/**
 * 
 */
package com.frank.startup.portal.util;

import com.sun.jersey.api.client.Client;

/**
 * @author frankwong
 *
 */
public class HttpClientUil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://gitsea.com";
		Client client;
		if (url.startsWith("https")) {
			client = Client.create(ClientHelper.configureClient());
		} else {
			client = Client.create();
		}

	}

}
