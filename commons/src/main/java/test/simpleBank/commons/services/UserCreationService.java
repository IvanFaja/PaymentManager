package test.simpleBank.commons.services;

import test.simpleBank.commons.entity.User;

/**
 * Created by ifAJARD on 4/11/14.
 */
public interface UserCreationService {
    public boolean create(User user);

    void delete(User user) throws NotFound;
}
