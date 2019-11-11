package ua.nure.prykhodko.entity;

public class Train {
    private int id;
    private int economyClass;
    private int compartment;
    private int common;
    private int boughtPlacesEconomy;
    private int boughtPlacesCompartment;
    private int boughtPlacesCommon;

    public Train(int id, int economyClass, int compartment, int common){
        this.id = id;
        this.economyClass = economyClass;
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

    public int getBoughtPlacesEconomy() {
        return boughtPlacesEconomy;
    }

    public void setBoughtPlacesEconomy(int boughtPlacesEconomy) {
        this.boughtPlacesEconomy = boughtPlacesEconomy;
    }

    public int getBoughtPlacesCompartment() {
        return boughtPlacesCompartment;
    }

    public void setBoughtPlacesCompartment(int boughtPlacesCompartment) {
        this.boughtPlacesCompartment = boughtPlacesCompartment;
    }

    public int getBoughtPlacesCommon() {
        return boughtPlacesCommon;
    }

    public void setBoughtPlacesCommon(int boughtPlacesCommon) {
        this.boughtPlacesCommon = boughtPlacesCommon;
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
