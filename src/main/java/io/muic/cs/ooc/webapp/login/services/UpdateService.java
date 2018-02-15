package io.muic.cs.ooc.webapp.login.services;

import io.muic.cs.ooc.webapp.login.database.MySQL;
import io.muic.cs.ooc.webapp.login.model.User;

public class UserService extends Service{

    private MySQL mySQL = new MySQL();

    public User getUser(String username){
        return MySQL.getUserbyUsername(username);
    }

}
