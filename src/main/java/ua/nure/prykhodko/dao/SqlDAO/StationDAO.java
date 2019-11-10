package ua.nure.prykhodko.dao.SqlDAO;

import ua.nure.prykhodko.constants.Fields;
import ua.nure.prykhodko.entity.Station;
import ua.nure.prykhodko.utils.StationComparator;

import java.sql.*;
import java.util.*;

public class StationDAO implements CrudDAO<Station, Integer> {

    private static final String SQL_GET_STATION_BY_NAME = "SELECT * FROM station WHERE name=(?)";
    private static final String SQL_GET_TIME_FOR_STATION_BY_ID = "SELECT arrive_time, depart_time FROM station_route WHERE station_id=(?) and route_id=(?)";
    private static final String SQL_GET_STATION_BY_ID = "SELECT * FROM station WHERE id=(?)";
    private static final String SQL_GET_STATION_BY_RUOTE_ID = "SELECT station_id, arrive_time, depart_time FROM station_route WHERE route_id = (?)";
    private static final String SQL_GET_STATION_BY_LOGIN = "SELECT * FROM station WHERE name=(?)";
    private static final String SQL_GET_STATION_ROUTE = "select station_route.route_id, station_route.arrive_time,station_route.depart_time FROM station\n" +
            "INNER JOIN station_route on station.id = station_route.station_id where station.name=(?)";
    private static final String SQL_GET_TIME_BY_ID = "SELECT arrive_time, depart_time FROM station_route WHERE station_id=(?) and route_id=?";
    private static final String SQL_UPDATE_NAME = "UPDATE station SET name=? WHERE id=?";
    private static final String SQL_GET_ENTITY_ID = "SELECT id FROM station WHERE name=?";
    private static final String SQL_ADD_STATION = "INSERT into station(name) values (?);";
    private static final String SQL_DELETE_STATION_IN_ROUTE = "DELETE FROM station_route WHERE station_id=?";
    private static final String SQL_DELETE_STATION_IN_STATION = "DELETE FROM station WHERE id=?";

    ConnectionPool connectionPool;


    @Override
    public List<Station> getAll() {
        return null;
    }

    @Override
    public Station update(Station entity) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_UPDATE_NAME);
            pstm.setString(1, entity.getName());
            pstm.setInt(2, entity.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getEntityID(String name) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_ENTITY_ID);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt(Fields.ENTITY_ID);
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
        return 0;
    }

    public List<Station> getStationRoute(String name) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Station> stationList = new ArrayList<>();
        Station station = new Station();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
        boolean flag = false;
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_STATION_ROUTE);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            while (rs.next()) {
                station = new Station();
                flag = true;
                station.setName(name);
                station.setRoute_id(rs.getInt(Fields.ROUTE_ID));
                station.setArrive_time(rs.getTimestamp(Fields.STATION_ARRIVE_TIME, cal));
                station.setDepart_time(rs.getTimestamp(Fields.STATION_DEPART_TIME, cal));
                stationList.add(station);
            }
            return flag ? stationList : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setStationTime(Station station) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_TIME_BY_ID);
            pstm.setInt(1, station.getId());
            pstm.setInt(2, station.getRoute_id());
            rs = pstm.executeQuery();
            if (rs.next()) {
                station.setArrive_time(rs.getTimestamp(Fields.STATION_ARRIVE_TIME, cal));
                station.setDepart_time(rs.getTimestamp(Fields.STATION_DEPART_TIME, cal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * getting station by id
     *
     * @param id station id
     * @return station object
     */
    @Override
    public Station getEntityById(Integer id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_STATION_BY_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new Station(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * getting time for station
     *
     * @param station station with time
     * @param id      route id
     */
    public void getTimeForStation(Station station, int id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_TIME_FOR_STATION_BY_ID);
            pstm.setInt(1, station.getId());
            pstm.setInt(2, id);
            rs = pstm.executeQuery();
            if (rs.next()) {

                Timestamp arrive_time = rs.getTimestamp(Fields.STATION_ARRIVE_TIME, cal);
                Timestamp depart_time = rs.getTimestamp(Fields.STATION_DEPART_TIME, cal);

                if (arrive_time != null) {
                    station.setArrive_time(arrive_time);
                }
                if (depart_time != null) {
                    station.setDepart_time(depart_time);
                }
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
    }

    @Override
    public boolean delete(Integer id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_DELETE_STATION_IN_ROUTE);
            pstm.setInt(1, id);
            if (pstm.executeUpdate() == 1) {
                ConnectionPool.getInstance().close(con, pstm, rs);
                con = ConnectionPool.getInstance().getConnection();
                pstm = con.prepareStatement(SQL_DELETE_STATION_IN_STATION);
                pstm.setInt(1, id);
                return pstm.executeUpdate() == 1;
            } else {
                return false;
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
        return false;
    }

    @Override
    public boolean addEntity(Station entity) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_ADD_STATION);
            pstm.setString(1, entity.getName());
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
     * getting station by name
     *
     * @param name Station name
     * @return Station object
     */
    public Station getStationByName(String name) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_STATION_BY_NAME);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return parseStation(rs);
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
        return null;
    }

    /**
     * getting station object
     *
     * @param id route id
     * @return station treeSet
     */
    public TreeSet<Station> getStationByRouteId(int id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        TreeSet<Station> stationTreeSet = new TreeSet<>(new StationComparator());
        Station station = new Station();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));

        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_STATION_BY_RUOTE_ID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                station.setId(rs.getInt(Fields.STATION_ROUTE_STATION_ID));
                station.setArrive_time(rs.getTimestamp(Fields.STATION_ARRIVE_TIME, cal));
                station.setDepart_time(rs.getTimestamp(Fields.STATION_DEPART_TIME, cal));
                station.setName(getEntityById(station.getId()).getName());
                stationTreeSet.add(station);
                station = new Station();
            }
            return stationTreeSet;
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().close(con, pstm, rs);
        }
        return null;
    }

    private Station parseStation(ResultSet rs) throws SQLException {
        return new Station(rs.getInt(Fields.ENTITY_ID), rs.getString(Fields.ENTITY_NAME));
    }

}
