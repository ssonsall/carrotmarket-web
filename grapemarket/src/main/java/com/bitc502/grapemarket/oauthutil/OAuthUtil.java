package com.bitc502.grapemarket.oauthutil;

import java.math.BigInteger;
import java.security.SecureRandom;

public class OAuthUtil {
    public static String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
    
    public static boolean checkStateCode(String callBackState, String myState) {
    	if( !callBackState.equals( myState ) ) {
    	    return false; //401 unauthorized
    	} else {
    	    return true; //200 success
    	}
    }
}
