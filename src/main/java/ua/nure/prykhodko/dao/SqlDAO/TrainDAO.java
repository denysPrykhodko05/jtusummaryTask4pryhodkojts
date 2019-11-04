package ua.nure.prykhodko.dao.SqlDAO;

import ua.nure.prykhodko.constants.Fields;
import ua.nure.prykhodko.entity.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrainDAO extends AbstractController<Train, Integer> {

    private static final String SQL_GET_TRAIN_BY_ID = "SELECT * FROM train WHERE id=(?)";

    @Override
    public List<Train> getAll() {
        return null;
    }

    @Override
    public Train update(Train entity) {
        return null;
    }

    /**
     * getting train by his id
     * @param id
     * @return object train
     */
    @Override
    public Train getEntityById(Integer id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm=con.prepareStatement(SQL_GET_TRAIN_BY_ID);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            if (rs.next()){
                return new Train(rs.getInt(Fields.TRAIN_AMOUNT_OF_ECONOMY_CLASS),
                        rs.getInt(Fields.TRAIN_AMOUNT_OF_COMPARTMENT),rs.getInt(Fields.TRAIN_AMOUNT_OF_COMMON));
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,pstm,rs);
        }

        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean addEntity(Train entity) {
        return false;
    }
}
