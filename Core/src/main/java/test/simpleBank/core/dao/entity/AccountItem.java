package test.simpleBank.core.dao.entity;

/**
 * Created by ifAJARD on 7/11/14.
 */
public class AccountItem {

    private long userId;
    private int balance;
    private long id;

    public AccountItem() {
    }

    public AccountItem(int balance, long userId) {
        this.balance = balance;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
