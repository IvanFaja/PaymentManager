package test.simpleBank.core.dao.mapper;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import test.simpleBank.commons.entity.AccountMovement;
import test.simpleBank.core.dao.entity.AccountItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ivan on 08/11/2014.
 */
public class AccountMovementMapper implements RowMapper<AccountMovement> {
    @Override
    public AccountMovement mapRow(ResultSet resultSet, int i) throws SQLException {
        AccountMovement accountItem = new AccountMovement();
        accountItem.setType(AccountMovement.TransactionType.values()[resultSet.getInt("MOV_TYPE")]);
        accountItem.setAmount(resultSet.getInt("AMOUNT"));
        accountItem.setDate(resultSet.getDate("MOV_DATE"));
        return accountItem;
    }
}
