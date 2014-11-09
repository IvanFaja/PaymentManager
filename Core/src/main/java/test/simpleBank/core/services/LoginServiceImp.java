package test.simpleBank.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.simpleBank.commons.entity.User;
import test.simpleBank.commons.services.LoginService;
import test.simpleBank.commons.services.NotFound;
import test.simpleBank.core.dao.UserDao;
import test.simpleBank.core.dao.entity.UserItem;

@Service("loginService")
public class LoginServiceImp implements LoginService {

    @Autowired
    private UserDao dao;


    public boolean validate(User user) {
        try {
            UserItem userFound = dao.getByUserHash(user.getUserHash());
            return userFound.getPasswordHash().equals(user.getPasswordHash());
        } catch (NotFound notFound) { //TODO: catch sql exception
            return false;
        }
    }
}
