package genmicro.tools

import java.util.regex.Matcher
import java.util.regex.Pattern

class Utils {
    static String escapeRegex(String inStr) {
    	Pattern  GRAB_SP_CHARS = Pattern.compile('([\\\\*+\\[\\](){}\\$.?\\^|])');   	
    	
        Matcher match = GRAB_SP_CHARS.matcher(inStr);
        return match.replaceAll('\\\\$1');
    }  		
	
	static String replaceAllFromMap(String text, Map replacer){
		String result = text
		
		replacer.each { key, value ->
			result = result.replaceAll(escapeRegex(key), value) 			
		}
		
		return result
	}
}
