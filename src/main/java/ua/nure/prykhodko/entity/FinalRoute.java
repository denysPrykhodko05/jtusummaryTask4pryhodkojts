package ua.nure.prykhodko.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class FinalRoute {
    private int trainId;
    private String routeName;
    private String startStationName;
    private String finalStationName;
    private String arrive_time;
    private String depart_time;
    private int economy_class;
    private int compartment;
    private int common;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getFinalStationName() {
        return finalStationName;
    }

    public void setFinalStationName(String finalStationName) {
        this.finalStationName = finalStationName;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(String depart_time) {
        this.depart_time = depart_time;
    }

    public int getEconomy_class() {
        return economy_class;
    }

    public void setEconomy_class(int economy_class) {
        this.economy_class = economy_class;
    }

    public int getCompartment() {
        return compartment;
    }

    public void setCompartment(int compartment) {
        this.compartment = compartment;
    }

    public int getCommon() {
        return common;
    }

    public void setCommon(int common) {
        this.common = common;
    }
}
