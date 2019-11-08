package ua.nure.prykhodko.entity;

public class Train {
    private int id;
    private int economy_class;
    private int compartment;
    private int common;
    private int bought_places_economy;
    private int bought_places_compartment;
    private int bought_places_common;

    public Train(int economy_class, int compartment, int common){
        this.economy_class = economy_class;
        this.compartment = compartment;
        this.common = common;
    }

    public Train(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBought_places_economy() {
        return bought_places_economy;
    }

    public void setBought_places_economy(int bought_places_economy) {
        this.bought_places_economy = bought_places_economy;
    }

    public int getBought_places_compartment() {
        return bought_places_compartment;
    }

    public void setBought_places_compartment(int bought_places_compartment) {
        this.bought_places_compartment = bought_places_compartment;
    }

    public int getBought_places_common() {
        return bought_places_common;
    }

    public void setBought_places_common(int bought_places_common) {
        this.bought_places_common = bought_places_common;
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
