package test.simpleBank.commons.services;

import test.simpleBank.commons.entity.AccountMovement;
import test.simpleBank.commons.entity.ReportSearchCriteria;

import java.util.List;

public interface AccountMovementManagerService {
    void register(AccountMovement accountMovement) throws NotEnough, UndefinedType;

    int getAmount(ReportSearchCriteria criteria);

    List<AccountMovement> getElementByDate(ReportSearchCriteria criteria, int offset, int amount);
}