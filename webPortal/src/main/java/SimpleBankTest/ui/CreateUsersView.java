package SimpleBankTest.ui;

import SimpleBankTest.BeanFactory;
import SimpleBankTest.ui.validators.PasswordValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.springframework.stereotype.Component;
import test.simpleBank.commons.entity.User;
import test.simpleBank.commons.services.NotFound;
import test.simpleBank.commons.services.UserCreationService;

@Component(value = "MainView")
public class CreateUsersView extends CustomComponent implements Button.ClickListener {
    private final TextField newUser;
    private final PasswordField password;
    private final Button createUser;

    Label text = new Label();
    private String username;
    private UserCreationService createUserService;


    public CreateUsersView() {
        createUserService = BeanFactory.getInstance().getUserManagementService();

        setSizeFull();
        newUser = new TextField("New User: ");
        newUser.setWidth("300px");
        newUser.setRequired(true);
        newUser.setInputPrompt("new user (eg. joe@email.com)");
        newUser.addValidator(new EmailValidator(
                "Username must be an email address"));
        newUser.setInvalidAllowed(false);

        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.addValidator(new PasswordValidator());
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");

        createUser = new Button("Create", this);

        Button delete = new Button("Delete", new Button.ClickListener() {


            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!newUser.isValid() || !password.isValid()) {
                    text.setValue("Invalid user or password");
                    return;
                }
                String newUserString = newUser.getValue();
                String passwordString = password.getValue();
                try {
                    createUserService.delete(new User(newUserString, passwordString));
                    Notification.show("User deleted ", Notification.Type.ASSISTIVE_NOTIFICATION);
                } catch (NotFound notFound) {
                    Notification.show("User cold no be deleted ", Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        VerticalLayout fields = new VerticalLayout(text, newUser, password, createUser, delete);
        fields.setCaption("Create new users");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        if (!newUser.isValid() || !password.isValid()) {
            text.setValue("Invalid user or password");
            return;
        }
        String newUserString = newUser.getValue();
        String passwordString = password.getValue();
        boolean ok = createUserService.create(new User(newUserString, passwordString));
        if (ok) {
            Notification.show("User Created ", Notification.Type.ASSISTIVE_NOTIFICATION);
        } else {
            Notification.show("User cold no be created ", Notification.Type.ERROR_MESSAGE);
        }
    }
}