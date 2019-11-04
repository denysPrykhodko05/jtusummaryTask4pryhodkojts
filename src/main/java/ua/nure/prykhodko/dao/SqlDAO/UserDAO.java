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
    private static final String SQL_ADD_USER = "INSERT INTO users (login, password, role, email) VALUES (?,?,?,?)";
    private static final String SQL_GET_COUNT_BY_LOGIN = "SELECT count FROM users WHERE login = (?)";
    private static final String SQL_UPDATE_USER_COUNT ="UPDATE users SET count=(?) WHERE login=(?)";

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
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, statement, rs);
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
        final String email = entity.getEmail();

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm=con.prepareStatement(SQL_ADD_USER);

            pstm.setString(1,login);
            pstm.setString(2,password);
            pstm.setInt(3,roleId);
            pstm.setString(4,email);

            if (pstm.executeUpdate()==1){
                return true;
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,pstm,rs);
        }

        return false;
    }

    /**
     * getting current count by user login
     * @param login
     * @return current count
     */

    public Integer getCountByLogin(String login){
        Connection con=null;
        PreparedStatement pstm = null;
        ResultSet rs= null;

        try {
            con= ConnectionPool.getInstance().getConnection();
            pstm=con.prepareStatement(SQL_GET_COUNT_BY_LOGIN);
            pstm.setString(1,login);
            rs=pstm.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,pstm,rs);
        }
        return null;
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
        user.setEmail(resultSet.getString(Fields.USER_EMAIL));
        user.setCount(resultSet.getInt(Fields.USER_COUNT));
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
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,preparedStatement,rs);
        }
        return null;
    }

    /**
     * getting role by id
     * @param id
     * @return user role
     */
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
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,preparedStatement,rs);
        }
        return null;
    }

    public boolean updateCountByLogin(int count, String login){
        Connection con =null;
        PreparedStatement pstm = null;
        ResultSet rs =null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm=con.prepareStatement(SQL_UPDATE_USER_COUNT);
            pstm.setInt(1, count);
            pstm.setString(2,login);
            if (pstm.executeUpdate()==1){
                return true;
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,pstm,rs);
        }
        return false;
    }
}
