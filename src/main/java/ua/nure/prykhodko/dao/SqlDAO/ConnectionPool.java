package ua.nure.prykhodko.dao.SqlDAO;

import java.sql.*;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    private Connection connection = null;
    private Statement statement=null;

    private static final String login = "root";
    private static final String password= "";
   // private static final String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useLegacyDatetimeCode=false";
    private static final String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private ConnectionPool(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, password);
            statement = connection.prepareStatement("USE  railway_ticket_office");
            ((PreparedStatement) statement).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }

    public void closePrepareStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATMENT + e);
            }
        }
    }

    // Закрытие ResultSet
    public void closeResultSet(ResultSet rs){
        if (rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET + e);
            }
        }
    }

    // Закрытие Connection
    public  void closeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
                instance=null;
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION + e);
            }
        }
    }

    // Закрытие PrepareStatement,Connection, ResultSet
    public void close(Connection con, Statement stmt, ResultSet rs){
        closeConnection(con);
        closePrepareStatement(stmt);
        closeResultSet(rs);
    }

    public void rollback(Connection con){
        if (con!=null){
            try {
                con.rollback();
            } catch (SQLException e) {
                LOG.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION + e);
            }
        }
    }

}
