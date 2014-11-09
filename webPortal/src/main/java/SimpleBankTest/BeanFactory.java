package SimpleBankTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.simpleBank.commons.entity.Account;
import test.simpleBank.commons.services.AccountCreationService;
import test.simpleBank.commons.services.AccountMovementManagerService;
import test.simpleBank.commons.services.LoginService;
import test.simpleBank.commons.services.UserCreationService;

/**
 * Created by ifAJARD on 30/10/14.
 */
public class BeanFactory {
    private static BeanFactory ourInstance = new BeanFactory();
    private Account accountCreationService;

    public static BeanFactory getInstance() {
        return ourInstance;
    }
    public static ApplicationContext beanContext = new ClassPathXmlApplicationContext("http-client-context.xml");

    private BeanFactory() {

    }
    static private Object getBean(String beanId){
        return beanContext.getBean(beanId);
    }

    public LoginService geUserLoginValidator() {
        return (LoginService) getBean("loginClient");
    }

    public UserCreationService getUserManagementService() {
        return (UserCreationService) getBean("userManagementService");
    }

    public AccountCreationService getAccountCreationService() {
        return (AccountCreationService)getBean("accountManagementService");
    }

    public AccountMovementManagerService getAccountMovementsManagerService() {
        return  (AccountMovementManagerService)getBean("accountMovementManagerService");
    }
}
