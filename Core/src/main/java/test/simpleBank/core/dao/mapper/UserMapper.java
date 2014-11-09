package test.simpleBank.core.dao.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import test.simpleBank.core.dao.entity.UserItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ifAJARD on 4/11/14.
 */
public class UserMapper implements ParameterizedRowMapper {

    public UserItem mapRow(ResultSet resultSet, int i) throws SQLException {
        UserItem user = new UserItem();
        user.setPasswordHash(resultSet.getString("PASSWORD_HASH"));
        user.setUserHash(resultSet.getString("USER_HASH"));
        user.setId(resultSet.getLong("ID"));
        return user;
    }
}
