package ua.nure.prykhodko.entity;

import java.sql.Timestamp;

public class Route {
    private int routeId;
    private String startPoint;
    private int startPoint_id;
    private String finalPoint;
    private int finalPoint_id;
    private int trainId;
    private Timestamp arrive_time;
    private Timestamp depart_time;

    public Route(){}

    public int getStartPoint_id() {
        return startPoint_id;
    }

    public void setStartPoint_id(int startPoint_id) {
        this.startPoint_id = startPoint_id;
    }

    public int getFinalPoint_id() {
        return finalPoint_id;
    }

    public void setFinalPoint_id(int finalPoint_id) {
        this.finalPoint_id = finalPoint_id;
    }

    public Route(int id){
        this.routeId=id;
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

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(String finalPiont) {
        this.finalPoint = finalPiont;
    }


    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPiont) {
        this.startPoint = startPiont;
    }
}
