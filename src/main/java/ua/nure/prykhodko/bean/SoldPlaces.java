package ua.nure.prykhodko.bean;

import java.sql.Timestamp;

public class SoldPlaces {
        private int train_id;
        private String carriage;
        private int carriage_number;
        private Timestamp date;
        private int place;
        private String start_station;
        private String final_station;

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    public int getCarriage_number() {
        return carriage_number;
    }

    public void setCarriage_number(int carriage_number) {
        this.carriage_number = carriage_number;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getStart_station() {
        return start_station;
    }

    public void setStart_station(String start_station) {
        this.start_station = start_station;
    }

    public String getFinal_station() {
        return final_station;
    }

    public void setFinal_station(String final_station) {
        this.final_station = final_station;
    }
}
