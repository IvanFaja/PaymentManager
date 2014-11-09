package test.simpleBank.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.simpleBank.commons.entity.AccountMovement;
import test.simpleBank.commons.entity.ReportSearchCriteria;
import test.simpleBank.commons.services.AccountMovementManagerService;
import test.simpleBank.commons.services.NotEnough;
import test.simpleBank.commons.services.NotFound;
import test.simpleBank.commons.services.UndefinedType;
import test.simpleBank.core.dao.AccountDao;
import test.simpleBank.core.dao.AccountMovementDao;
import test.simpleBank.core.dao.entity.AccountItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 08/11/2014.
 */
@Service("accountMovementManagerService")
public class AccountMovementManagerServiceImpl implements AccountMovementManagerService {
    @Autowired
    private AccountMovementDao dao;
    @Autowired
    private AccountDao accountDao;

    @Override
    public void register(AccountMovement accountMovement) throws NotEnough, UndefinedType {
        try {
            AccountItem account = accountDao.find(accountMovement.getAccountNumber());
            switch (accountMovement.getType()) {
                case CREDIT:
                    if (account.getBalance() - accountMovement.getAmount() < 0) {
                        throw new NotEnough();
                    }
                    account.setBalance(account.getBalance() - accountMovement.getAmount());
                    break;
                case DEBIT:
                    account.setBalance(account.getBalance() + accountMovement.getAmount());
                    break;
                default:
                    throw new UndefinedType();
            }

            accountDao.update(account);
            dao.insert(accountMovement);
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
    }

    @Override
    public int getAmount(ReportSearchCriteria criteria) {
        return dao.getAmount(criteria);
    }

    @Override
    public List<AccountMovement> getElementByDate(ReportSearchCriteria criteria, int offset, int amount) {
        return dao.getAccountMovements(criteria,offset,amount);
    }
}
