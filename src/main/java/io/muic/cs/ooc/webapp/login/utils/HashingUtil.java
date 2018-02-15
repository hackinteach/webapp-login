package io.muic.cs.ooc.webapp.login.utils;

import org.mindrot.jbcrypt.BCrypt;

public final class HashingUtil {

    public static final String hashPassword(String plainPwd){
        return BCrypt.hashpw(plainPwd,BCrypt.gensalt(12));
    }

    public static final boolean verifyPassword(String hash, String plain){
        return BCrypt.checkpw(plain,hash);
    }


}
