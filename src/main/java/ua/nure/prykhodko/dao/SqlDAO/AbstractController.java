package ua.nure.prykhodko.dao.SqlDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import ua.nure.prykhodko.exception.Messages;

public abstract class AbstractController<E, K> {
    private  ConnectionPool connectionPool;
    private Connection connection;

    private static final Logger LOG = Logger.getLogger(AbstractController.class);

    public AbstractController() {
        connectionPool= ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public abstract List<E> getAll();
    public abstract E update(E entity);
    public abstract E getEntityById(K id);
    public abstract boolean delete(K id);
    public abstract boolean addEntity(E entity);

    // Закрытие PrepareStatement
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
