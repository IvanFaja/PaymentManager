package test.simpleBank.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import test.simpleBank.commons.entity.AccountMovement;
import test.simpleBank.commons.entity.ReportSearchCriteria;
import test.simpleBank.core.dao.AccountMovementDao;
import test.simpleBank.core.dao.mapper.AccountMovementMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 08/11/2014.
 */
@Component("accountMovementDao")
public class AccountMovementDaoImpl implements AccountMovementDao {
    @Autowired
    private JdbcTemplate db;

    @Override
    public int insert(AccountMovement accountMovement) {
        int id = db.update("INSERT INTO MOVEMENTS (AMOUNT, MOV_TYPE,MOV_DATE,ACCOUNT_ID) VALUES(?,?,?,?)",
                accountMovement.getAmount(), accountMovement.getType().ordinal(),accountMovement.getDate(),accountMovement.getAccountNumber());
        return id;
    }

    @Override
    public int getAmount(ReportSearchCriteria criteria) {
        if(null == criteria){
            return 0;
        }
        Number amount = db.queryForObject("SELECT COUNT(*) FROM USERS INNER JOIN ACCOUNTS ON USERS.ID = ACCOUNTS.USER_ID \n" +
                "INNER JOIN MOVEMENTS ON MOVEMENTS.ACCOUNT_ID=ACCOUNTS.ID " +
                "WHERE MOV_DATE BETWEEN ? AND ? AND USERS.USER_HASH = ? AND ACCOUNTS.ID = ? ",
                new Object[]{criteria.getStartDate(),criteria.getEndDate(),criteria.getUserHash(),criteria.getAccountNumber()},Number.class);
        return amount.intValue();
    }

    @Override
    public List<AccountMovement> getAccountMovements(ReportSearchCriteria criteria, int offset, int amount) {
        if(null == criteria){
            return new ArrayList<AccountMovement>();
        }
        List<AccountMovement> movements = db.query("SELECT * FROM USERS INNER JOIN ACCOUNTS ON USERS.ID = ACCOUNTS.USER_ID \n" +
                "INNER JOIN MOVEMENTS ON MOVEMENTS.ACCOUNT_ID=ACCOUNTS.ID " +
                "WHERE MOV_DATE BETWEEN ? AND ? AND USERS.USER_HASH = ? AND ACCOUNTS.ID = ? LIMIT ? OFFSET ? ",new AccountMovementMapper(),
                criteria.getStartDate(),criteria.getEndDate(),criteria.getUserHash(),criteria.getAccountNumber(),amount,offset);
        return movements;
    }

}
