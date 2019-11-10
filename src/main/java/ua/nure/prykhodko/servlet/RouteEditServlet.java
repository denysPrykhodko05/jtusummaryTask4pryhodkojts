package ua.nure.prykhodko.servlet;

import ua.nure.prykhodko.entity.Route;
import ua.nure.prykhodko.dao.SqlDAO.RouteDAO;
import ua.nure.prykhodko.dao.SqlDAO.StationDAO;
import ua.nure.prykhodko.entity.Station;
import ua.nure.prykhodko.entity.Train;
import ua.nure.prykhodko.utils.TimeUtils;
import ua.nure.prykhodko.utils.Validation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/admin/routeEdit")
public class RouteEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String route_number = req.getParameter("route_number");
        final String choice = req.getParameter("choice");
        ServletContext servletContext = req.getServletContext();
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");


        if (!routeDAO.isExistRoute(Integer.parseInt(route_number))) {
            req.setAttribute("errorFindRoute", true);
            req.setAttribute("route_number", route_number);
            req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
        } else {
            if (choice == null) {
                req.setAttribute("errorNothingChosen", true);
                req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
            }
            switch (choice) {
                case "startStation":
                    if (!route_number.equals("")) {
                        req.setAttribute("input", true);
                        req.setAttribute("startStation", true);
                        req.setAttribute("route_number", route_number);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errorIncorrectRouteInput", true);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    }
                    break;

                case "finalStation":
                    if (!route_number.equals("")) {
                        req.setAttribute("input", true);
                        req.setAttribute("finalStation", true);
                        req.setAttribute("route_number", route_number);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errorIncorrectRouteInput", true);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    }
                    break;

                case "train":
                    if (route_number != null && !route_number.equals("")) {
                        Route route = routeDAO.getEntityById(Integer.parseInt(route_number));
                        if (route != null) {
                            List<Train> availableTrain = routeDAO.getAvailableTrain();
                            req.setAttribute("trainList", availableTrain);
                            req.setAttribute("input", true);
                            req.setAttribute("train", true);
                            req.setAttribute("route_number", route_number);
                            req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                        } else {
                            req.setAttribute("errorFindRoute", true);
                            req.setAttribute("route_number", route_number);
                            req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                        }
                    } else {
                        req.setAttribute("errorIncorrectRouteInput", true);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    }
                    break;

                case "deleteStation":
                    if (route_number != null && !route_number.equals("")) {
                        req.setAttribute("input", true);
                        req.setAttribute("deleteStation", true);
                        req.setAttribute("route_number", route_number);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errorIncorrectRouteInput", true);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    }
                    break;

                case "addStation":
                    if (route_number != null && !route_number.equals("")) {
                        req.setAttribute("input", true);
                        req.setAttribute("addStation", true);
                        req.setAttribute("route_number", route_number);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errorIncorrectRouteInput", true);
                        req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    }
                    break;

                default:
                    req.setAttribute("errorNothingChosen", true);
                    req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        RouteDAO routeDAO = (RouteDAO) servletContext.getAttribute("routeDAO");
        StationDAO stationDAO = (StationDAO) servletContext.getAttribute("stationDAO");
        final String train_id = req.getParameter("id");
        final String route_id = req.getParameter("route_number");
        final String startStationName = req.getParameter("startName");
        final String finalStationName = req.getParameter("finalName");
        final String deleteStation = req.getParameter("deleteStation");
        final String addStation = req.getParameter("addStation");
        final String startDate = req.getParameter("departDate");
        final String startTime = req.getParameter("departTime");
        final String finalDate = req.getParameter("arriveDate");
        final String finalTime = req.getParameter("arriveTime");
        Timestamp departTime = null;
        Timestamp arriveTime = null;


        if (startDate != null && startTime != null) {
            departTime = TimeUtils.parseStringToTimestamp(startDate, startTime);
        }
        if (finalDate != null && finalTime != null) {
            arriveTime = TimeUtils.parseStringToTimestamp(finalDate, finalTime);
        }
        if (train_id != null && !train_id.equals("") && !route_id.equals("") && routeDAO.updateTrainInRoute(Integer.parseInt(route_id), Integer.parseInt(train_id))) {
            resp.sendRedirect("/admin");
        } else {
            if (startStationName != null && !startStationName.equals("") && Validation.isCorrectStationName(startStationName) && departTime != null) {
                Station station = stationDAO.getStationByName(startStationName);
                int route = Integer.parseInt(route_id);
                if (station != null) {
                    int startStationOld = routeDAO.getStartStationInRoute(route);
                    routeDAO.deleteStationFromRoute(startStationOld, route);
                    routeDAO.updateStartStationInRoute(route, station.getId(), departTime);
                    station.setRoute_id(route);
                    station.setDepart_time(departTime);
                    routeDAO.addStartStationInStationRoute(station);
                    resp.sendRedirect("/admin");
                } else {
                    station = new Station();
                    station.setName(startStationName);
                    station.setRoute_id(route);
                    station.setDepart_time(departTime);
                    stationDAO.addEntity(station);
                    int startStationOld = routeDAO.getStartStationInRoute(route);
                    routeDAO.deleteStationFromRoute(startStationOld, route);
                    routeDAO.updateStartStationInRoute(station.getRoute_id(), station.getId(), departTime);
                    routeDAO.addStartStationInStationRoute(station);
                    resp.sendRedirect("/admin");
                }
            } else if (finalStationName != null && !finalStationName.equals("") && Validation.isCorrectStationName(finalStationName) && arriveTime != null) {
                Station station = stationDAO.getStationByName(finalStationName);
                int route = Integer.parseInt(route_id);
                if (station != null) {
                    addFinalStation(routeDAO, route, station, arriveTime);
                    resp.sendRedirect("/admin");
                } else {
                    station = new Station();
                    stationParse(station, finalStationName, route, arriveTime, stationDAO);
                    addFinalStation(routeDAO, route, station, arriveTime);
                    resp.sendRedirect("/admin");
                }
            } else if (deleteStation != null && !deleteStation.equals("")) {
                final String stationName = req.getParameter("stationName");
                if (stationName != null && Validation.isCorrectStationName(stationName) && route_id != null) {
                    int station_id = stationDAO.getEntityID(stationName);
                    routeDAO.deleteStationFromRoute(station_id, Integer.parseInt(route_id));
                    resp.sendRedirect("/admin");
                }
            } else if (addStation != null && !addStation.equals("")) {
                final String station_name = req.getParameter("stationName");
                final String arrive_date = req.getParameter("arriveDate");
                final String arrive_time = req.getParameter("arriveTime");
                final String depart_date = req.getParameter("departDate");
                final String depart_time = req.getParameter("departTime");
                Timestamp timestampArrive = null;
                Timestamp timestampDepart = null;
                Station station = new Station();
                int station_id = 0;
                if (arrive_date != null && !arrive_date.equals("") && arrive_time != null && !arrive_time.equals("")) {
                    timestampArrive = TimeUtils.parseStringToTimestamp(arrive_date, arrive_time);
                    station.setArrive_time(timestampArrive);
                }
                if (depart_date != null && !depart_date.equals("") && depart_time != null && !depart_time.equals("")) {
                    timestampDepart = TimeUtils.parseStringToTimestamp(depart_date, depart_time);
                    station.setDepart_time(timestampDepart);
                }
                if (station_name != null && Validation.isCorrectStationName(station_name)) {
                    station_id = stationDAO.getEntityID(station_name);
                    station.setName(station_name);
                } else {
                    req.setAttribute("errorAddStationName", true);
                    req.setAttribute("input", true);
                    req.setAttribute("addStation", true);
                    req.setAttribute("route_number", route_id);
                    req.getRequestDispatcher("/jsp/EditRoutePage.jsp").forward(req, resp);
                }

                if (station_id == 0) {
                    stationDAO.addEntity(station);
                    station_id = stationDAO.getEntityID(station_name);
                    station.setId(station_id);
                    station.setRoute_id(Integer.parseInt(route_id));
                    station.setArrive_time(timestampArrive);
                    station.setDepart_time(timestampDepart);
                    routeDAO.addStationToRoute(station);
                    resp.sendRedirect("/admin");
                } else {
                    station.setRoute_id(Integer.parseInt(route_id));
                    station.setId(station_id);
                    routeDAO.addStationToRoute(station);
                    resp.sendRedirect("/admin");
                }
            }
        }
    }

    private void addFinalStation(RouteDAO routeDAO, int route, Station station, Timestamp arriveTime) {
        int finalStationOld = routeDAO.getFinalStationInRoute(route);
        routeDAO.deleteStationFromRoute(finalStationOld, route);
        routeDAO.updateFinalStationInRoute(route, station.getId(), arriveTime);
        station.setRoute_id(route);
        station.setArrive_time(arriveTime);
        routeDAO.addFinalStationInRoute(station);
    }

    private void stationParse(Station station, String finalStation, int route, Timestamp arriveTime, StationDAO stationDAO) {
        station.setName(finalStation);
        station.setRoute_id(route);
        station.setArrive_time(arriveTime);
        stationDAO.addEntity(station);
        int stationNewId = stationDAO.getEntityID(station.getName());
        station.setId(stationNewId);
    }

}
