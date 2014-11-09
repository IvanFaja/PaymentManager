package test.simpleBank.core.dao;

import test.simpleBank.commons.services.NotFound;
import test.simpleBank.core.dao.entity.UserItem;

/**
 * Created by ifAJARD on 4/11/14.
 */
public interface UserDao {

    UserItem getByUserHash(String userHash) throws NotFound;

    int insert(UserItem user);
    void delete(String userHash) throws NotFound;
}
