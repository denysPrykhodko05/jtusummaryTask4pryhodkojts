package ua.nure.prykhodko.servlet;

import org.apache.log4j.Logger;
import ua.nure.prykhodko.dao.SqlDAO.TicketDAO;
import ua.nure.prykhodko.dao.SqlDAO.TrainDAO;
import ua.nure.prykhodko.dao.SqlDAO.UserDAO;
import ua.nure.prykhodko.bean.BoughtTicket;
import ua.nure.prykhodko.bean.SoldPlaces;
import ua.nure.prykhodko.entity.Train;
import ua.nure.prykhodko.exception.Messages;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/purchaseTicket")
public class PurchaseTicket extends HttpServlet {
    private static final Logger log = Logger.getLogger(PurchaseTicket.class);
    private String id;
    private String compartment;
    private String common;
    private String economy_class;
    private String from;
    private String to;
    private String price;
    private String arrive_time;
    private String depart_time;

    @Override
    public void init() throws ServletException {
        log.info(Messages.INFO_ENTER);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        TrainDAO trainDAO = (TrainDAO) servletContext.getAttribute("trainDAO");
        Train train;


        stringInit(req);
        train = trainDAO.getEntityById(Integer.parseInt(id));
        log.trace(Messages.TRACE_FOUND_TRAIN + train);
        req.setAttribute("trainId", id);
        if (compartment != null) {
            req.setAttribute("compartment", compartment);
            req.setAttribute("amount_compartment_carriages", train.getCompartment());
        }
        if (common != null) {
            req.setAttribute("common", common);
            req.setAttribute("amount_common_carriages", train.getCommon());
        }
        if (economy_class != null) {
            req.setAttribute("economy_class", economy_class);
            req.setAttribute("amount_economy_carriages", train.getEconomyClass());
        }
        reqInit(req);
        req.getRequestDispatcher("jsp/PurchaseTicketPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        TicketDAO ticketDAO = (TicketDAO) servletContext.getAttribute("ticketDAO");
        HttpSession session = req.getSession();
        TrainDAO trainDAO = (TrainDAO) servletContext.getAttribute("trainDAO");
        UserDAO userDAO = (UserDAO) servletContext.getAttribute("userDAO");
        Train train = null;
        BoughtTicket boughtTicket = new BoughtTicket();
        final String carriage_number = findNumberOfCarriage(req);
        int max = 0;
        boolean emptyPlaces = true;
        double userMoney;


        train = trainDAO.getEntityById(Integer.parseInt(id));
        userMoney = userDAO.getCountByLogin((String) session.getAttribute("login"));
        log.trace(Messages.TRACE_USER_COUNT+userMoney);

        if (userMoney >= Double.parseDouble(price)) {
            List<SoldPlaces> soldPlacesList = null;
            soldPlacesList = trainDAO.getEmptyPlacesByTrain(train, Timestamp.valueOf(depart_time), from, to);
            log.trace(Messages.TRACE_EMPTY_PLACES+soldPlacesList);

            for (SoldPlaces place : soldPlacesList) {
                if (place.getPlace() > max) {
                    max = place.getPlace();
                }
            }

            reqInit(req);
            if (compartment != null && max < 36) {
                boughtTicket.setCarriage("compartment");
            } else if (common != null && max < 68) {
                boughtTicket.setCarriage("common");
            } else if (economy_class != null && max < 58) {
                boughtTicket.setCarriage("economy_class");
            } else {
                if (common != null) {
                    req.setAttribute("amount_common_carriages", train.getCommon());
                }
                if (compartment != null) {
                    req.setAttribute("amount_compartment_carriages", train.getCompartment());
                }
                if (economy_class!=null) {
                    req.setAttribute("amount_economy_carriages", train.getEconomyClass());
                }
                req.setAttribute("errorFullCarriage", true);
                req.getRequestDispatcher("jsp/PurchaseTicketPage.jsp").forward(req, resp);
                emptyPlaces = false;
            }

            if (emptyPlaces) {
                boughtTicket.setTrain_id(Integer.parseInt(id));
                boughtTicket.setCarriage_number(Integer.parseInt(carriage_number));
                boughtTicket.setDate(Timestamp.valueOf(depart_time));
                boughtTicket.setPlace(max + 1);
                boughtTicket.setStart_station(from);
                boughtTicket.setFinal_station(to);
                if (ticketDAO.buyTicket_train(boughtTicket)) {
                    int id = ticketDAO.getTicketId(boughtTicket);
                    log.trace(Messages.TRACE_TICKET_FOUND+id);
                    double userMoneyNew = userMoney - Double.parseDouble(price);
                    ticketDAO.buyTicket_user(id, (String) session.getAttribute("login"));
                    userDAO.updateCountByLogin(userMoneyNew, (String) session.getAttribute("login"));
                    log.trace(Messages.TRACE_SESSION_COUNT+userMoneyNew);
                    resp.sendRedirect("/profile/ticket");
                }
            }
        } else {
            req.setAttribute("errorMoney", "You haven't enough money");
            req.getRequestDispatcher("/profile").forward(req, resp);
        }
    }

    private void reqInit(HttpServletRequest req) {
        req.setAttribute("id", id);
        req.setAttribute("compartment", compartment);
        req.setAttribute("common", common);
        req.setAttribute("economy_class", economy_class);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("price", price);
        req.setAttribute("arrive_time", arrive_time);
        req.setAttribute("depart_time", depart_time);
    }

    private void stringInit(HttpServletRequest req) {

        id = (String) req.getParameter("id");
        compartment = (String) req.getParameter("compartment");
        common = (String) req.getParameter("common");
        economy_class = (String) req.getParameter("economy_class");
        from = (String) req.getParameter("from");
        to = (String) req.getParameter("to");
        price = (String) req.getParameter("price");
        arrive_time = (String) req.getParameter("arrive_time");
        depart_time = (String) req.getParameter("depart_time");
    }

    private String findNumberOfCarriage(HttpServletRequest req) {
        if (req.getParameter("compartment_carriage") != null) {
            return req.getParameter("compartment_carriage");
        }
        if (req.getParameter("common_carriage") != null) {
            return req.getParameter("common_carriage");
        }
        if (req.getParameter("economy_carriage") != null) {
            return req.getParameter("economy_carriage");
        }
        return null;
    }

    @Override
    public void destroy() {
        log.trace(Messages.INFO_EXIT);
    }


}
