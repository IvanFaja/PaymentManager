package test.simpleBank.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import test.simpleBank.commons.services.NotFound;
import test.simpleBank.core.dao.AccountDao;
import test.simpleBank.core.dao.entity.AccountItem;
import test.simpleBank.core.dao.mapper.AccountMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ifAJARD on 7/11/14.
 */
public class AccountDaoImpl implements AccountDao{
    private JdbcTemplate db;
    private SimpleJdbcInsert dbinsert;

    public long inset(AccountItem account) {
        Map parameters = new HashMap();
        parameters.put("user_id", account.getUserId());
        parameters.put("balance", account.getBalance());
        Number accountNumber = dbinsert.executeAndReturnKey(parameters);
        return accountNumber.longValue();
    }

    public AccountItem find(long accountNumber) throws NotFound {
        List<AccountItem> list = db.query("SELECT * FROM ACCOUNTS WHERE ID=? ",new AccountMapper(),accountNumber);
        if(list.size()==0){
            throw new NotFound();
        }
        return list.get(0);
    }

    @Override
    public int update(AccountItem account) {
        int columnsAffected= db.update("UPDATE ACCOUNTS SET BALANCE=? WHERE ID =?",account.getBalance(),account.getId());
        return columnsAffected;

    }

    public void setDb(JdbcTemplate db) {
        this.db = db;
        dbinsert = new SimpleJdbcInsert(db.getDataSource()).withTableName("accounts").usingGeneratedKeyColumns("id");
    }


}
