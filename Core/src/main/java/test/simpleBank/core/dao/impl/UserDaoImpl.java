package test.simpleBank.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import test.simpleBank.commons.services.NotFound;
import test.simpleBank.core.dao.UserDao;
import test.simpleBank.core.dao.entity.UserItem;
import test.simpleBank.core.dao.mapper.UserMapper;

import java.util.List;

/**
 * Created by ifAJARD on 4/11/14.
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate db;

    public void setDb(JdbcTemplate db) {
        this.db = db;
    }


    public UserItem getByUserHash(String userHash) throws NotFound {

        List<UserItem> list = db.query("SELECT ID,USER_HASH,PASSWORD_HASH FROM USERS WHERE USER_HASH=? ", new UserMapper(), userHash );
        if(list.size()==0){
            throw new NotFound();
        }
        return list.get(0);
    }


    public int insert(UserItem user) {
        int id = db.update("INSERT INTO USERS (USER_HASH, PASSWORD_HASH) VALUES(?,?)", user.getUserHash(), user.getPasswordHash());
        return id;
    }

    public void delete(String userHash) throws NotFound{
        int id = db.update("DELETE FROM USERS WHERE USER_HASH = ?", userHash);

    }
}
