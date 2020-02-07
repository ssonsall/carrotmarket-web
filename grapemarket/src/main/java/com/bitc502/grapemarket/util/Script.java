package com.bitc502.grapemarket.util;

public class Script {
	public static String back(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('" + msg + "');");
		sb.append("</script>");
		return sb.toString();
	}

	public static String href(String location) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("location.href='" + location + "';");
		sb.append("</script>");
		return sb.toString();
	}

	public static String hrefAndAlert(String location, String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("location.href='" + location + "';");
		sb.append("alert('" + msg + "');");
		sb.append("</script>");
		return sb.toString();
	}

	public static String closePopup(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("window.close();");
		sb.append("alert('" + msg + "');");
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String AndroidClosePopup() {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("window.close();");
		sb.append("</script>");
		return sb.toString();
	}

}
