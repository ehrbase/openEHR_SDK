package org.ehrbase.openehr.sdk.response.dto.util;

public interface StringSupport {
    public static String systemNeutralString(String str) {
    	return str.replace("\n", "").replace("\r", "");
    }
}
