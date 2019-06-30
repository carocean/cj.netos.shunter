package cj.netos.shunter.util;

import cj.ultimate.util.StringUtil;

public class LabelPathUtil {
	public static String getExtName(String fullName) {
		if(StringUtil.isEmpty(fullName)) {
			return null;
		}
		int pos=fullName.lastIndexOf("/");
		if(pos<1) {
			return null;
		}
		String shortName=fullName.substring(pos+1,fullName.length());
		pos=shortName.lastIndexOf(".");
		if(pos<1) {
			return null;
		}
		return shortName.substring(pos+1,shortName.length());
	}
}
