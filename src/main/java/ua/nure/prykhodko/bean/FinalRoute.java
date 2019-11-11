package ua.nure.prykhodko.bean;

import ua.nure.prykhodko.entity.Station;

import java.util.TreeSet;

public class FinalRoute {
    private int trainId;
    private String routeName;
    private String startStationName;
    private String finalStationName;
    private String arrive_time;
    private String depart_time;
    private String timeInRoad;
    private int economyClass;
    private int compartment;
    private int common;
    private double priceForCommon;
    private double priceForCompartment;
    private double priceForEconomy;
    private TreeSet<Station> routeList;

    public TreeSet<Station> getRouteList() {
        return routeList;
    }

    public void setRouteList(TreeSet<Station> routeList) {
        this.routeList = routeList;
    }

    public double getPriceForCommon() {
        return priceForCommon;
    }

    public void setPriceForCommon(double priceForCommon) {
        this.priceForCommon = priceForCommon;
    }

    public double getPriceForCompartment() {
        return priceForCompartment;
    }

    public void setPriceForCompartment(double priceForCompartment) {
        this.priceForCompartment = priceForCompartment;
    }

    public double getPriceForEconomy() {
        return priceForEconomy;
    }

    public void setPriceForEconomy(double priceForEconomy) {
        this.priceForEconomy = priceForEconomy;
    }

    public String getTimeInRoad() {
        return timeInRoad;
    }

    public void setTimeInRoad(String timeInRoad) {
        this.timeInRoad = timeInRoad;
    }

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

    public String  getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String  arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String  getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(String  depart_time) {
        this.depart_time = depart_time;
    }

    public int getEconomyClass() {
        return economyClass;
    }

    public void setEconomyClass(int economyClass) {
        this.economyClass = economyClass;
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
