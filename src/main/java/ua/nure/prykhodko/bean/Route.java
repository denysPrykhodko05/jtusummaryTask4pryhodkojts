package ua.nure.prykhodko.bean;

public class Route {
    private int routeId;
    private String startPoint;
    private String finalPoint;
    private int trainId;
    private String arrive_time;
    private String depart_time;

    public Route(){}

    public Route(int id){
        this.routeId=id;
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
