package test.simpleBank.core.dao.mapper;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import test.simpleBank.core.dao.entity.AccountItem;
import test.simpleBank.core.dao.entity.UserItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ivan on 08/11/2014.
 */
public class AccountMapper implements RowMapper<AccountItem> {
    @Override
    public AccountItem mapRow(ResultSet resultSet, int i) throws SQLException {
        AccountItem accountItem = new AccountItem();
        accountItem.setBalance(resultSet.getInt("BALANCE"));
        accountItem.setUserId(resultSet.getLong("USER_ID"));
        accountItem.setId(resultSet.getLong("ID"));
        return accountItem;
    }
}
