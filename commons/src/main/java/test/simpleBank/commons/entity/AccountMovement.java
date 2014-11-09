package test.simpleBank.commons.entity;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ivan on 08/11/2014.
 */
public class AccountMovement implements Serializable{
    public enum TransactionType {CREDIT("Credit"),DEBIT("Debit");
        private String text;
        TransactionType(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
        public static TransactionType fromString(String text) {
            if (text != null) {
                for (TransactionType b : TransactionType.values()) {
                    if (text.equalsIgnoreCase(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }
    };


    private Date date = new Date();
    @Min(0)
    private int amount;
    private long accountNumber;

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    private TransactionType type;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
