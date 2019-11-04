package ua.nure.prykhodko.dao.SqlDAO;

import ua.nure.prykhodko.constants.Fields;
import ua.nure.prykhodko.entity.Route;
import ua.nure.prykhodko.entity.Station;

import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class StationDAO extends AbstractController<Station,Integer> {

    private static final String SQL_GET_STATION_BY_NAME = "SELECT * FROM station WHERE name=(?)";
    private static final String SQL_GET_TIME_FOR_STATION_BY_ID= "SELECT arrive_time, depart_time FROM station_route WHERE station_id=(?) and route_id=(?)";
    private static final String SQL_GET_STATION_BY_ID = "SELECT * FROM station WHERE id=(?)";

    ConnectionPool connectionPool;


    @Override
    public List<Station> getAll() {
        return null;
    }

    @Override
    public Station update(Station entity) {
        return null;
    }

    @Override
    public Station getEntityById(Integer id) {
        Connection con=null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_STATION_BY_ID);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new Station(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    public void getTimeForStation(Station station,int id){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
        try {
            con= ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_TIME_FOR_STATION_BY_ID);
            pstm.setInt(1,station.getId());
            pstm.setInt(2,id);
            rs=pstm.executeQuery();
            if (rs.next()){

                Timestamp arrive_time =rs.getTimestamp(Fields.STATION_ARRIVE_TIME, cal);
                Timestamp depart_time = rs.getTimestamp(Fields.STATION_DEPART_TIME, cal);

                if (arrive_time!=null){
                    station.setArrive_time(arrive_time);
                }
                if (depart_time!=null){
                    station.setDepart_time(depart_time);
                }
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,pstm,rs);
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean addEntity(Station entity) {
        return false;
    }

    /**
     * getting station by name
     * @param name Station name
     * @return Station object
     */
    public Station getStationByName(String name){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs=null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_STATION_BY_NAME);
            pstm.setString(1,name);
            rs = pstm.executeQuery();
            if(rs.next()){
                return parseStation(rs) ;
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,pstm, rs);
        }
        return null;
    }

    private  Station parseStation(ResultSet rs) throws SQLException {
        return new Station(rs.getInt(Fields.ENTITY_ID),rs.getString(Fields.ENTITY_NAME));
    }

}
