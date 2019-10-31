package ua.nure.prykhodko.dao.SqlDAO;

import ua.nure.prykhodko.entity.Route;
import ua.nure.prykhodko.entity.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO extends AbstractController<Route,Integer> {

    private static final String SQL_GET_ROUTE_BY_ID = "SELECT * FROM route WHERE id = (?)";
    private static final String SQL_GET_ROUTE_ID_BY_STATION_ID = "SELECT route_id FROM STATION_ROUTE\n" +
            "WHERE station_id=? OR station_id=? GROUP BY route_id HAVING COUNT(*) >= 2";

    ConnectionPool connectionPool;

    @Override
    public List<Route> getAll() {
        return null;
    }

    @Override
    public Route update(Route entity) {
        return null;
    }

    @Override
    public Route getEntityById(Integer id) {
        Route route = new Route();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con=ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_ROUTE_BY_ID);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            if (rs.next()){
                route.setRouteId(rs.getInt(1));
                route.setTrainId(rs.getInt(2));
                return route;
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
     * getting route id by stations id
     * @param id1 start station
     * @param id2 end station
     * @return array list with routes id
     */
    public List<Integer> getRouteByStationId(int id1,int id2 ){
        Connection con= null;
        PreparedStatement pstm = null;
        ResultSet rs=null;
        List<Integer> list = new ArrayList<>();

        try {
            con=ConnectionPool.getInstance().getConnection();
            pstm=con.prepareStatement(SQL_GET_ROUTE_ID_BY_STATION_ID);
            pstm.setInt(1,id1);
            pstm.setInt(2,id2);
            rs = pstm.executeQuery();
            while (rs.next()){
                list.add(rs.getInt(1));
            }
            return list;
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
    public boolean addEntity(Route entity) {
        return false;
    }

}
