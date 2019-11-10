package ua.nure.prykhodko.dao.SqlDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import ua.nure.prykhodko.exception.Messages;

public interface CrudDAO<E, K> {
    public  List<E> getAll();
    public E update(E entity);
    public E getEntityById(K id);
    public boolean delete(K id);
    public boolean addEntity(E entity);

}
