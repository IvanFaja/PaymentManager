package test.simpleBank.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.simpleBank.commons.entity.Account;
import test.simpleBank.commons.services.AccountCreationService;
import test.simpleBank.commons.services.NotFound;
import test.simpleBank.core.dao.AccountDao;
import test.simpleBank.core.dao.UserDao;
import test.simpleBank.core.dao.entity.AccountItem;
import test.simpleBank.core.dao.entity.UserItem;

/**
 * Created by ifAJARD on 6/11/14.
 */
@Service("accountsManagementService")
public class AccountsManagementServiceImpl implements AccountCreationService {
    @Autowired
    private AccountDao dao;
    @Autowired
    private UserDao userDao;

    public long create(Account account) throws NotFound {
        long accountNumber = -1;

        UserItem user = userDao.getByUserHash(new Integer(account.getUser().hashCode()).toString());
        AccountItem accountItem = new AccountItem(account.getBalance(),user.getId());
        accountNumber = dao.inset(accountItem);
        return accountNumber;
    }

    public Account getAccountInformation(long accountNumber) {
        Account account = null;
        try {
            AccountItem accountItem = dao.find(accountNumber);
            account = new Account(accountItem.getBalance(),"",accountItem.getId());
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        return account;
    }

}
