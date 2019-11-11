package ua.nure.prykhodko.dao.entityDAO;

import ua.nure.prykhodko.entity.Station;

import java.util.List;

public interface stationDAO{
    int getEntityID(String name);
    List<Station> getStationRoute(String name);
    void getTimeForStation(Station station, int id);
    Station getStationByName(String name);
}
