package test.simpleBank.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.simpleBank.commons.entity.User;
import test.simpleBank.commons.services.NotFound;
import test.simpleBank.commons.services.UserCreationService;
import test.simpleBank.core.dao.entity.UserItem;
import test.simpleBank.core.dao.impl.UserDaoImpl;

/**
 * Created by ifAJARD on 4/11/14.
 */
@Service("userManagementService")
public class UserManagementServiceImpl implements UserCreationService {

    @Autowired
    private UserDaoImpl userDao;

    public boolean create(User user) {
        int id = userDao.insert(new UserItem(user.getUserHash(),user.getPasswordHash()));
        return id >0;
    }

    @Override
    public void delete(User user) throws NotFound {
          userDao.delete(user.getUserHash());
    }
}
