package ua.nure.prykhodko.dao.SqlDAO;

import ua.nure.prykhodko.constants.Fields;
import ua.nure.prykhodko.bean.SoldPlaces;
import ua.nure.prykhodko.entity.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class TrainDAO implements CrudDAO<Train, Integer> {

    private static final String SQL_GET_TRAIN_BY_ID = "SELECT * FROM train WHERE id=(?)";
    private static final String SQL_GET_PLACES_BY_TRAIN="SELECT * FROM sold_tickets WHERE train_id=(?) and date=(?) and start_station=(?) and final_station=(?)"; @Override
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
                return parseTrain(rs);
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

    /**
     * find empty places in train
     * @param train This train we are checking for empty places
     * @param date depart date and time
     * @param startStation depart station
     * @param finalStation arrive station
     * @return list of sold places
     */
    public List<SoldPlaces> getEmptyPlacesByTrain(Train train, Timestamp date, String startStation, String finalStation){
        Connection con= null;
        PreparedStatement pstm = null;
        ResultSet rs=null;
        List<SoldPlaces> soldPlacesList = new ArrayList<>();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm= con.prepareStatement(SQL_GET_PLACES_BY_TRAIN);
            pstm.setInt(1,train.getId());
            pstm.setTimestamp(2,date,cal);
            pstm.setString(3, startStation);
            pstm.setString(4, finalStation);
            rs=pstm.executeQuery();
            while (rs.next()){
                soldPlacesList.add(parseSoldPlaces(rs));
            }
            return soldPlacesList;
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,pstm,rs);
        }
        return null;
    }



    /**
     * convert result set into Tarin object
     * @param rs result set with train params
     * @return object train
     * @throws SQLException
     */
    private Train parseTrain(ResultSet rs) throws SQLException {
        Train train = new Train();
        train.setId(rs.getInt(Fields.ENTITY_ID));
        train.setCommon(rs.getInt(Fields.TRAIN_AMOUNT_OF_COMMON));
        train.setCompartment(rs.getInt(Fields.TRAIN_AMOUNT_OF_COMPARTMENT));
        train.setEconomy_class(rs.getInt(Fields.TRAIN_AMOUNT_OF_ECONOMY_CLASS));
        return train;
    }
    private SoldPlaces parseSoldPlaces(ResultSet rs) throws SQLException {
        SoldPlaces soldPlaces = new SoldPlaces();
        soldPlaces.setCarriage(rs.getString(Fields.SOLD_TICKETS_CARRIAGE));
        soldPlaces.setCarriage_number(rs.getInt(Fields.SOLD_TICKETS_CARRIAGE_NUMBER));
        soldPlaces.setDate(rs.getTimestamp(Fields.SOLD_TICKETS_DATE));
        soldPlaces.setPlace(rs.getInt(Fields.SOLD_TICKETS_PLACE));
        soldPlaces.setStart_station(rs.getString(Fields.SOLD_TICKETS_START_STATION));
        soldPlaces.setFinal_station(rs.getString(Fields.SOLD_TICKETS_FINAL_STATION));
        soldPlaces.setTrain_id(rs.getInt(Fields.SOLD_TICKETS_TRAIN_ID));
        return soldPlaces;
    }

}
