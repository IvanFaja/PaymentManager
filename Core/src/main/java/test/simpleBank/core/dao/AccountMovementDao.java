package test.simpleBank.core.dao;

import test.simpleBank.commons.entity.AccountMovement;
import test.simpleBank.commons.entity.ReportSearchCriteria;

import java.util.List;

/**
 * Created by Ivan on 08/11/2014.
 */
public interface AccountMovementDao {
    int insert(AccountMovement accountMovement);

    int getAmount(ReportSearchCriteria criteria);

    List<AccountMovement> getAccountMovements(ReportSearchCriteria criteria, int offset, int amount);
}
