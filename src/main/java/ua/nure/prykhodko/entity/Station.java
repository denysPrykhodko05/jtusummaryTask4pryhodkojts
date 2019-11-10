package ua.nure.prykhodko.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Station {
    private String name;
    Timestamp arrive_time;
    Timestamp depart_time;
    private int id;
    private int route_id;

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public Station() {

    }

    public Station(String name) {
        this.name = name;
    }

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(int id, String name, Timestamp arrive_time, Timestamp depart_time) {
        this.id = id;
        this.name = name;
        this.arrive_time = arrive_time;
        this.depart_time = depart_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Timestamp getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(Timestamp arrive_time) {
        this.arrive_time = arrive_time;
    }

    public Timestamp getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(Timestamp depart_time) {
        this.depart_time = depart_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
