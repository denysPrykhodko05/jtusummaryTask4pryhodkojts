package ua.nure.prykhodko.dao.SqlDAO;

import ua.nure.prykhodko.constants.Fields;
import ua.nure.prykhodko.bean.BoughtTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class TicketDAO implements CrudDAO<BoughtTicket,Integer> {
    private static final String SQL_BUY_TICKET_USER_INIT = "UPDATE users SET ticket_id=(?) WHERE login=(?)";
    private static final String SQL_BUY_TICKET_TRAIN_INIT = "insert into sold_tickets(train_id, date, carriage, carriage_number, place, start_station, final_station) values (?,?,?,?,?,?,?)";
    private static final String SQL_GET_TICKET_ID = "SELECT id FROM sold_tickets WHERE train_id=(?) and date=(?) and carriage=(?) and carriage_number=(?) and place=(?) and start_station=(?) and final_station=(?)";
    private static final String SQL_GET_TICKET_BY_ID = "SELECT * FROM sold_tickets WHERE id=(?)";
    @Override
    public List<BoughtTicket> getAll() {
        return null;
    }

    @Override
    public BoughtTicket update(BoughtTicket entity) {
        return null;
    }

    @Override
    public BoughtTicket getEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean addEntity(BoughtTicket entity) {
        return false;
    }

    public BoughtTicket getTicketById(int id){
        Connection con =null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        BoughtTicket boughtTicket = new BoughtTicket();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_GET_TICKET_BY_ID);
            pstm.setInt(1,id);
            rs= pstm.executeQuery();
            if (rs.next()){
                initBoughtTicketByResultSet(boughtTicket,rs,cal);
                return boughtTicket;
            }
        } catch (SQLException e) {
            ConnectionPool.getInstance().rollback(con);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,pstm,rs);
        }
        return null;
    }

    public void buyTicket_user(int id, String login){
        Connection con= null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm=con.prepareStatement(SQL_BUY_TICKET_USER_INIT);
            pstm.setInt(1,id);
            pstm.setString(2,login);
            pstm.executeUpdate();
        } catch (SQLException e) {
            ConnectionPool.getInstance().close(con,pstm,rs);
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().close(con,pstm,rs);
        }

    }

    public int getTicketId(BoughtTicket boughtTicket){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm =con.prepareStatement(SQL_GET_TICKET_ID);
            initPSTMbyBoughtTicket(boughtTicket,pstm);
            rs=pstm.executeQuery();
            if(rs.next()){
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

    public boolean buyTicket_train(BoughtTicket bt){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
        try {
            con = ConnectionPool.getInstance().getConnection();
            pstm=con.prepareStatement(SQL_BUY_TICKET_TRAIN_INIT);
            initPSTMbyBoughtTicket(bt,pstm);
            if(pstm.executeUpdate()==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void initPSTMbyBoughtTicket(BoughtTicket bt, PreparedStatement pstm) throws SQLException {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
        int i=1;
        pstm.setInt(i++,bt.getTrain_id());
        pstm.setTimestamp(i++,bt.getDate(),cal);
        pstm.setString(i++,bt.getCarriage());
        pstm.setInt(i++,bt.getCarriage_number());
        pstm.setInt(i++,bt.getPlace());
        pstm.setString(i++,bt.getStart_station());
        pstm.setString(i++,bt.getFinal_station());
    }

    private void initBoughtTicketByResultSet(BoughtTicket bt, ResultSet rs, Calendar cal){
        try {
            bt.setTrain_id(rs.getInt(Fields.SOLD_TICKETS_TRAIN_ID));
            bt.setDate(rs.getTimestamp(Fields.SOLD_TICKETS_DATE,cal));
            bt.setPlace(rs.getInt(Fields.SOLD_TICKETS_PLACE));
            bt.setCarriage(rs.getString(Fields.SOLD_TICKETS_CARRIAGE));
            bt.setCarriage_number(rs.getInt(Fields.SOLD_TICKETS_CARRIAGE_NUMBER));
            bt.setStart_station(rs.getString(Fields.SOLD_TICKETS_START_STATION));
            bt.setFinal_station(rs.getString(Fields.SOLD_TICKETS_FINAL_STATION));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
