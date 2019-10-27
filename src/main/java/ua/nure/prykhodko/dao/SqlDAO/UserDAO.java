package ua.nure.prykhodko.dao.SqlDAO;

import ua.nure.prykhodko.constants.Fields;
import ua.nure.prykhodko.dao.entityDAO.userDAO;
import ua.nure.prykhodko.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractController<User, Integer> implements userDAO {

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM USERS";

    /**
     * getting all users in database
     * @return List of users
     */
    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        Statement statement = null;
        ResultSet rs=null;
        Connection con = null;
        try {
            con=ConnectionPool.getInstance().getConnection();
            statement=con.createStatement();
            rs=statement.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next()){
               allUsers.add(parseUser(rs));
            }
        } catch (SQLException e) {
            rollback(con);
            e.printStackTrace();
        }finally {
            close(con,statement,rs);
        }
        return allUsers;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User getEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean addEntity(User entity) {
        return false;
    }

    @Override
    public User parseUser(ResultSet resultSet) throws SQLException {
        User user = new User();
            user.setId(resultSet.getInt(Fields.ENTITY_ID));
            user.setLogin(resultSet.getString(Fields.USER_LOGIN));
            user.setPassword(resultSet.getString(Fields.USER_PASSWORD));
            user.setRoleId(resultSet.getInt(Fields.USER_ROLE_ID));
            return user;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }
}
