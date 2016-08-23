package cacheutils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

public class CookieTool {
	private String seperater = "\t";
	private List<String[]> cookDetails = new ArrayList<String[]>();
	private CookieStore cookieStore = new BasicCookieStore();

	private String cookieTxtPath = "";

	public CookieTool(String cookieTxtPath) {
		this.cookieTxtPath =  System.getProperty("user.dir") + "\\" + cookieTxtPath;;
	}

	private void readCookie() {
		// List<String []> cookDetails = new ArrayList<String []>();
		for (String aCookLine : ReadFromFile.readFileByLines(cookieTxtPath)) {
			cookDetails.add(aCookLine.split(seperater));
		}
	}

	/*
	 * .zhaopin.com TRUE / FALSE 1500186084
	 * Hm_lvt_38ba284938d5eddca645bb5e02a02006 1468592710,1468627314,1468650084
	 * 
	 */
	private void addCookie() {
		for (String[] acookline : cookDetails) {
			String value = "";
			if (acookline.length >= 7) {
				//System.out.println(acookline[5] + " " + acookline.length);
				value = acookline[6];
			}
			String name = acookline[5];
			String domian = acookline[0];
			String path = acookline[2];
			BasicClientCookie acookie = new BasicClientCookie(name, value);
			acookie.setDomain(domian);
			acookie.setPath(path);
			cookieStore.addCookie(acookie);

			// System.out.println("BasicClientCookie :" +"
			// domian="+acookie.getDomain() + " name="+acookie.getName() + "
			// value="+acookie.getValue() + " path="+acookie.getPath() );
		}
	}

	public String getSeperater() {
		return seperater;
	}

	public void setSeperater(String seperater) {
		this.seperater = seperater;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public String getCookieTxtPath() {
		return cookieTxtPath;
	}

	public void setCookieTxtPath(String cookieTxtPath) {
		this.cookieTxtPath = cookieTxtPath;
	}

	public CookieStore generateCookies() {
		readCookie();
		addCookie();
		return cookieStore;
	}
}
