package SimpleBankTest.ui.models;

import test.simpleBank.commons.entity.AccountMovement;
import test.simpleBank.commons.entity.ReportSearchCriteria;
import test.simpleBank.commons.services.AccountMovementManagerService;

import java.util.List;

/**
 * Created by Ivan on 08/11/2014.
 */
public class AccountMovementLoader implements LazyDataLoader<AccountMovement> {
    private final AccountMovementManagerService service;
    private ReportSearchCriteria criteria;
    private int size;
    public AccountMovementLoader(AccountMovementManagerService service) {
        this.service = service;
        size = service.getAmount(criteria);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List get(int offset, int amount) {
        return service.getElementByDate(criteria,offset,amount);
    }

    public void setCriteria(ReportSearchCriteria criteria) {
        size = service.getAmount(criteria);
        this.criteria = criteria;
    }
}
