package ua.nure.prykhodko.dao.SqlDAO;

import ua.nure.prykhodko.constants.Fields;
import ua.nure.prykhodko.bean.Route;
import ua.nure.prykhodko.entity.Station;
import ua.nure.prykhodko.entity.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class RouteDAO extends AbstractController<Route, Integer> {

    private static final String SQL_GET_ROUTE_BY_ID = "SELECT * FROM route WHERE id = (?)";
    private static final String SQL_GET_ROUTE_ID_BY_STATION_ID = "SELECT route_id FROM STATION_ROUTE\n" +
            "WHERE station_id=? OR station_id=? GROUP BY route_id HAVING COUNT(*) >= 2";
    private static final String SQL_UPDATE_ARRIVE_TIME = "UPDATE station_route SET arrive_time=? WHERE station_id=? and route_id =?";
    private static final String SQL_UPDATE_DEPART_TIME = "UPDATE station_route SET depart_time=? WHERE station_id=? and route_id =?";
    private static final String SQL_ADD_STATION_ROUTE = "INSERT INTO station_route (station_id,  route_id, arrive_time, depart_time) VALUES (?,?,?,?)";
    private static final String SQL_AVAILABLE_TRAIN = "select train.id, amount_of_economy_class, amount_of_compartment, amount_of_common from train LEFT JOIN route on train.id = route.train where route.train IS NULL";
    private static final String SQL_UPDATE_ROUTE_TRAIN = "UPDATE route SET train=? WHERE id=?";
    private static final String SQL_GET_ROUTE_ID_BY_TRAIN = "SELECT id FROM route WHERE train = ?";

    @Override
    public List<Route> getAll() {
        return null;
    }

    @Override
    public Route update(Route entity) {
        return null;
    }


    public void updateRouteArriveTime(int station_id, int route_id, Timestamp arriveTime) {
        updateTime(station_id, route_id, arriveTime, SQL_UPDATE_ARRIVE_TIME);
    }

    public void updateRouteDepartTime(int id, int route_id, Timestamp departTime) {
        updateTime(id, route_id, departTime, SQL_UPDATE_DEPART_TIME);
    }

    /**
     * getting entity by id
     *
     * @param id station id
     * @return object of route
     */
    @Override
    public Route getEntityById(Integer id) {
        Route route = new Route();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_ROUTE_BY_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                route.setRouteId(rs.getInt(Fields.ENTITY_ID));
                route.setTrainId(rs.getInt(Fields.ROUTE_TRAIN));
                return route;
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
        return null;
    }

    public int getRouteIdByTrainId(int id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_ROUTE_ID_BY_TRAIN);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            if (rs.next()){
                return rs.getInt(Fields.ENTITY_ID);
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con, pstm,rs);
        }
        return 0;
    }

    public boolean updateTrainInRoute(int route_id, int train_id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_UPDATE_ROUTE_TRAIN);
            pstm.setInt(1, train_id);
            pstm.setInt(2, route_id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
        return false;
    }

    /**
     * getting not used trains
     *
     * @return List of available trains
     */
    public List<Train> getAvailableTrain() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Train train;
        List<Train> trainList = new ArrayList<>();
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_AVAILABLE_TRAIN);
            rs = pstm.executeQuery();
            while (rs.next()) {
                train = new Train();
                train.setId(rs.getInt(Fields.ENTITY_ID));
                train.setEconomy_class(rs.getInt(Fields.TRAIN_AMOUNT_OF_ECONOMY_CLASS));
                train.setCompartment(rs.getInt(Fields.TRAIN_AMOUNT_OF_COMPARTMENT));
                train.setCommon(rs.getInt(Fields.TRAIN_AMOUNT_OF_COMMON));
                trainList.add(train);
            }
            return trainList;
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
        return null;
    }

    /**
     * getting route id by stations id
     *
     * @param id1 start station
     * @param id2 end station
     * @return array list with routes id
     */
    public List<Integer> getRouteByStationId(int id1, int id2) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Integer> list = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_ROUTE_ID_BY_STATION_ID);
            pstm.setInt(1, id1);
            pstm.setInt(2, id2);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            return list;
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
        return null;
    }

   /* public TreeSet<Station> getRouteListByStation(Station o1){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs= null;

        con=ConnectionPool.getInstance().getConnection();
    }*/

    public boolean addStationToRoute(Station station) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_ADD_STATION_ROUTE);
            pstm.setInt(1, station.getId());
            pstm.setInt(2, station.getRoute_id());
            pstm.setTimestamp(3, station.getArrive_time(), cal);
            pstm.setTimestamp(4, station.getDepart_time(), cal);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean addEntity(Route entity) {
        return false;
    }

    private void updateTime(int id, int route_id, Timestamp timestamp, String SQL) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL);
            pstm.setTimestamp(1, timestamp, cal);
            pstm.setInt(2, id);
            pstm.setInt(3, route_id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
    }
}
