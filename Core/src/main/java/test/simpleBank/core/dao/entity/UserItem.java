package test.simpleBank.core.dao.entity;

/**
 * Created by ifAJARD on 7/11/14.
 */
public class UserItem {
    private String passwordHash;
    private String userHash;
    private long id;

    public UserItem() {
    }
    public UserItem( String userHash ,String passwordHash) {
        this.passwordHash = passwordHash;
        this.userHash = userHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    public String getUserHash() {
        return userHash;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
