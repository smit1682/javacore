package smit.motadata.sms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Methods {

    public  Boolean validMail(String email){
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z]*[.][a-z]+");
        Matcher emailMatcher = emailPattern.matcher(email);
        return  emailMatcher.matches();
    }

}
