package io.muic.cs.ooc.webapp.login.utils;

import java.util.Map;

public final class MyUtils {

    public static boolean validateString(String str){
        for(char c : str.toCharArray()){
            if(c == ';' || c == '"'|| c == '\'' || c == '`'){
                System.out.println("validate string : "+str+" false");
                return false;
            }
        }
        return true;
    }
}
