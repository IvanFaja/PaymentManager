package SimpleBankTest.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by ifAJARD on 7/11/14.
 */
public class MainView extends VerticalLayout implements View {
    public static final String NAME = "";
    private String username;

    public MainView() {
        TabSheet tap = new TabSheet();
        tap.addTab( new UsersManagmentView(),"Manage Users");
        tap.addTab( new AccountsManangmentView(),"Manage Accounts");
        tap.addTab( new RegisterAccountMovementsView(),"Manage Movements");
        tap.addTab( new AccountMovementsReportView(),"Reports");
        addComponent(tap);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        initSession();
    }

    private void initSession() {
        username = String.valueOf(getSession().getAttribute("user"));
    }
}
