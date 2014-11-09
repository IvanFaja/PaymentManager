package test.simpleBank.commons.services;

import test.simpleBank.commons.entity.Account;

/**
 * Created by ifAJARD on 6/11/14.
 */
public interface AccountCreationService {
    long create(Account account) throws NotFound;
    Account getAccountInformation(long accountNumber);
}
