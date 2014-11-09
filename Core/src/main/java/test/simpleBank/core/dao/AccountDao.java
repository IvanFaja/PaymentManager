package test.simpleBank.core.dao;

import test.simpleBank.core.dao.entity.AccountItem;
import test.simpleBank.commons.services.NotFound;

/**
 * Created by ifAJARD on 7/11/14.
 */
public interface AccountDao {
    long inset(AccountItem account);
    AccountItem find(long accountNumber) throws NotFound;

    int update(AccountItem account);
}
