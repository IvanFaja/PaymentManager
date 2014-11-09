package SimpleBankTest.ui;

import SimpleBankTest.BeanFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import org.springframework.stereotype.Component;
import test.simpleBank.commons.entity.Account;
import test.simpleBank.commons.services.AccountCreationService;
import test.simpleBank.commons.services.NotFound;


public class AccountsManangmentView extends VerticalLayout {

    private Account account;
    private AccountCreationService service;
    private final FormLayout form;

    public AccountsManangmentView() {
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
        service = BeanFactory.getInstance().getAccountCreationService();
        account = new Account();
        Panel panelForm = new Panel("Create account");
        form = new FormLayout();
        panelForm.setContent(form);
        addComponent(panelForm);
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
        final BeanFieldGroup<Account> binder = new BeanFieldGroup<Account>(Account.class);
        binder.setItemDataSource(account);
        form.addComponent(binder.buildAndBind("Name", "user"));
        form.addComponent(binder.buildAndBind("Opening balance", "balance"));

        binder.setBuffered(true);

        form.addComponent(new Button("Ok", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    binder.commit();
                    long accountNumber = service.create(account);
                    HorizontalLayout accountNumberLayout = new HorizontalLayout();
                    accountNumberLayout.addComponents(new Label("Account Number:"),new Label(Long.toString(accountNumber)));
                    addComponent(accountNumberLayout);
                    Notification.show("Succeed", Notification.Type.ASSISTIVE_NOTIFICATION);
                } catch (FieldGroup.CommitException e) {
                    Notification.show("Could not be done", Notification.Type.ERROR_MESSAGE);
                } catch (NotFound notFound) {
                    Notification.show("User not found",Notification.Type.ERROR_MESSAGE);
                }
            }
        }));



    }
}