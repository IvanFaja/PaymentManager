package SimpleBankTest.ui;

import SimpleBankTest.BeanFactory;
import SimpleBankTest.ui.models.AccountMovementLoader;
import SimpleBankTest.ui.models.LazyContainer;
import SimpleBankTest.ui.models.LazyDataLoader;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import test.simpleBank.commons.entity.Account;
import test.simpleBank.commons.entity.AccountMovement;
import test.simpleBank.commons.entity.ReportSearchCriteria;

/**
 * Created by Ivan on 08/11/2014.
 */
public class AccountMovementsReportView extends VerticalLayout {

    private final FormLayout form;
    private final ReportSearchCriteria reportSearchCriteria;
    private final AccountMovementLoader lazyLoader;

    public AccountMovementsReportView() {
        lazyLoader = new AccountMovementLoader(BeanFactory.getInstance().getAccountMovementsManagerService());
        reportSearchCriteria = new ReportSearchCriteria();
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
        Panel panelForm = new Panel("Account Report");
        form = new FormLayout();
        panelForm.setContent(form);
        addComponent(panelForm);
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
        final BeanFieldGroup<ReportSearchCriteria> binder = new BeanFieldGroup<ReportSearchCriteria>(ReportSearchCriteria.class);
        binder.setItemDataSource(reportSearchCriteria);
        form.addComponent(binder.buildAndBind("User", "user"));
        form.addComponent(binder.buildAndBind("Account number", "accountNumber"));
        form.addComponent(binder.buildAndBind("From", "startDate"));
        form.addComponent(binder.buildAndBind("To", "endDate"));
        binder.setBuffered(true);
        final Table report = new Table();
        final LazyContainer container = new LazyContainer(lazyLoader,new AccountMovement());
        report.setContainerDataSource(container);
        form.addComponent(new Button("Accept", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();
                    lazyLoader.setCriteria(reportSearchCriteria);
                    container.resetCache();
                    report.refreshRowCache();
                    Notification.show("Fields have been updated");
                } catch (FieldGroup.CommitException e) {
                    Notification.show("Some fields are wrong", Notification.Type.ERROR_MESSAGE);
                }
            }
        }));
        report.setVisibleColumns(new String[]{"date","type","amount"});
        report.setPageLength(9);
        addComponent(report);

    }
}
