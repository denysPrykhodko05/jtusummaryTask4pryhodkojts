package ua.nure.prykhodko.dao.SqlDAO;

import ua.nure.prykhodko.constants.Fields;
import ua.nure.prykhodko.dao.entityDAO.userDAO;
import ua.nure.prykhodko.entity.ROLE;
import ua.nure.prykhodko.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends AbstractController<User, Integer> implements userDAO {

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM USERS";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = (?)";
    private static final String SQL_FIND_ROLE_BY_ID = "SELECT access_level FROM role WHERE id=(?)";
    private static final String SQL_ADD_USER = "INSERT INTO users (login, password, role) VALUES (?,?,?)";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * getting all users in database
     *
     * @return List of users
     */
    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            statement = con.createStatement();
            rs = statement.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next()) {
                allUsers.add(parseUser(rs));
            }
        } catch (SQLException e) {
            connectionPool.rollback(con);
            e.printStackTrace();
        } finally {
            connectionPool.close(con, statement, rs);
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

    /**
     * add user to database
     * @param entity
     * @return if user added succesfuly method return true
     */
    @Override
    public boolean addEntity(User entity) {

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs=null;

        final String login = entity.getLogin();
        final String password= entity.getPassword();
        final int roleId = entity.getRoleId();

        try {
            con = connectionPool.getConnection();
            pstm=con.prepareStatement(SQL_ADD_USER);

            pstm.setString(1,login);
            pstm.setString(2,password);
            pstm.setInt(3,roleId);

            if (pstm.executeUpdate()==1){
                return true;
            }
        } catch (SQLException e) {
            connectionPool.rollback(con);
            e.printStackTrace();
        }finally {
            connectionPool.close(con,pstm,rs);
        }

        return false;
    }

    /**
     * getting user from result set
     * @param resultSet
     * @return object of User
     * @throws SQLException
     */
    @Override
    public User parseUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(Fields.ENTITY_ID));
        user.setLogin(resultSet.getString(Fields.USER_LOGIN));
        user.setPassword(resultSet.getString(Fields.USER_PASSWORD));
        user.setRoleId(resultSet.getInt(Fields.USER_ROLE_ID));
        return user;
    }

    /**
     * getting user from database by login
     * @param login
     * @return object of User
     */
    @Override
    public User findUserByLogin(String login) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            preparedStatement = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            rs=preparedStatement.executeQuery();
            while (rs.next()){
              return parseUser(rs);
            }
        } catch (SQLException e) {
            connectionPool.rollback(con);
            e.printStackTrace();
        }finally {
            connectionPool.close(con,preparedStatement,rs);
        }
        return null;
    }

    public ROLE getRoleByID(int id){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            preparedStatement = con.prepareStatement(SQL_FIND_ROLE_BY_ID);
            preparedStatement.setString(1,String.valueOf(id));
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                return ROLE.valueOf(rs.getString(1));
            }
        } catch (SQLException e) {
            connectionPool.rollback(con);
            e.printStackTrace();
        }finally {
            connectionPool.close(con,preparedStatement,rs);
        }
        return null;
    }
}
