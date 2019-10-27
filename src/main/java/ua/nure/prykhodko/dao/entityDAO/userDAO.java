package ua.nure.prykhodko.dao.entityDAO;

import ua.nure.prykhodko.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface userDAO {
    /**
     * parsing user from resultSet
     * @return object of User
     */
    User parseUser(ResultSet resultSet) throws SQLException;

    /**
     * find user by login in database
     * @param login
     * @return object of User
     */
    User findUserByLogin(String login);
}
