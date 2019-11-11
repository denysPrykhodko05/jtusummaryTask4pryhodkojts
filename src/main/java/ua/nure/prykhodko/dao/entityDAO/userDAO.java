package ua.nure.prykhodko.dao.entityDAO;

import ua.nure.prykhodko.entity.ROLE;
import ua.nure.prykhodko.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface userDAO {
    /**
     * parsing user from resultSet
     * @return object of User
     */
    User buildUser(ResultSet resultSet) throws SQLException;

    /**
     * find user by login in database
     * @param login
     * @return object of User
     */
    User findUserByLogin(String login);

     List<User> getAll();
    ROLE getRoleByID(int id);
}
