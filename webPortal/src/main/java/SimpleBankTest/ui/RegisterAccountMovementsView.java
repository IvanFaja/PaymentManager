package SimpleBankTest.ui;

import SimpleBankTest.BeanFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import test.simpleBank.commons.entity.AccountMovement;
import test.simpleBank.commons.services.AccountMovementManagerService;
import test.simpleBank.commons.services.NotEnough;
import test.simpleBank.commons.services.UndefinedType;

/**
 * Created by Ivan on 08/11/2014.
 */
public class RegisterAccountMovementsView extends VerticalLayout {
    private final AccountMovement accountMovement;
    private final FormLayout form;
    private final AccountMovementManagerService service;

    public RegisterAccountMovementsView() {
        service = BeanFactory.getInstance().getAccountMovementsManagerService();
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
        accountMovement = new AccountMovement();
        Panel panelForm = new Panel("Create account");
        form = new FormLayout();
        panelForm.setContent(form);
        addComponent(panelForm);
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
        final BeanFieldGroup<AccountMovement> binder = new BeanFieldGroup<AccountMovement>(AccountMovement.class);
        binder.setItemDataSource(accountMovement);
        form.addComponent(binder.buildAndBind("Amount", "amount"));
        form.addComponent(binder.buildAndBind("Account number", "accountNumber"));
        final ComboBox type = new ComboBox();
        type.setNewItemsAllowed(false);
        type.setTextInputAllowed(false);
        type.setNullSelectionAllowed(false);
        type.addItem("Credit");
        type.addItem("Debit");
        form.addComponent(type);
        binder.setBuffered(true);
        form.addComponent(new Button("Accept",new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                accountMovement.setType(AccountMovement.TransactionType.fromString(type.getValue().toString()));
                try {
                    binder.commit();
                    service.register(accountMovement);
                    Notification.show("Succeed ");
                }catch (FieldGroup.CommitException e) {
                    Notification.show("Could not be done",Notification.Type.ERROR_MESSAGE);
                } catch (NotEnough notFound) {
                    Notification.show("The account has not enough left ", Notification.Type.ERROR_MESSAGE);
                } catch (UndefinedType undefinedType) {
                    Notification.show("The unexpected error ", Notification.Type.ERROR_MESSAGE);

                }
            }
        }));

    }

}
