package SimpleBankTest.ui;

import SimpleBankTest.BeanFactory;
import SimpleBankTest.ui.validators.PasswordValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import test.simpleBank.commons.entity.User;
import test.simpleBank.commons.services.LoginService;


public class LoginView extends Panel implements View,
        Button.ClickListener {
    public static final String NAME = "Login";
    private final TextField user;
    private final PasswordField password;
    private final Button loginButton;
    private LoginService loginService;

    public LoginView() {
        this.setStyleName(Reindeer.PANEL_LIGHT);
        loginService = BeanFactory.getInstance().geUserLoginValidator();
        setSizeFull();
        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequired(true);
        user.setInputPrompt("Your username (eg. joe@email.com)");
        user.addValidator(new EmailValidator(
                "Username must be an email address"));
        user.setInvalidAllowed(false);

        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.addValidator(new PasswordValidator());
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");

        loginButton = new Button("Login", this);

        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        fields.setCaption("Please login to access the application");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
//        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setContent(viewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        user.focus();
    }


    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (!user.isValid() || !password.isValid()) {
            return;
        }

        String username = user.getValue();
        String password = this.password.getValue();

        boolean isValid = loginService.validate(new User(username,password));

        if (isValid) {

            // Store the current user in the service session
            getSession().setAttribute("user", username);

            // Navigate to main view
            getUI().getNavigator().navigateTo(MainView.NAME);//

        } else {

            // Wrong password clear the password field and refocuses it
            this.password.setValue(null);
            this.password.focus();

        }
    }
}
