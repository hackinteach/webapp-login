package io.muic.cs.ooc.webapp.login.services;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class UpdateService extends Service{

    public void updateUser(String username,Map<String,String> newProfile) {
        System.out.println("update "+username);
        for(String key : newProfile.keySet()){
            String val = newProfile.get(key);
            if(!StringUtils.isBlank(val))
            {
                MySQL.updateInfo(username,key,newProfile.get(key));
            }
        }
    }

}
