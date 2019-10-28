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

}
