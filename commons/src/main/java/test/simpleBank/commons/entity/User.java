package test.simpleBank.commons.entity;

import java.io.Serializable;

/**
 * Created by Maria on 02/11/2014.
 */
public class User implements Serializable{
    private String userHash;
    private String passwordHash;

    public User() {
    }

    public User(String user, String password) {
        //TODO: use better hash function such as md5 or sha256
        this.userHash = Integer.toString(user.hashCode());
        this.passwordHash = Integer.toString(password.hashCode());
    }

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
