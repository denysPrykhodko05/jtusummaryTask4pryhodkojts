package ua.nure.prykhodko.entity;

public class Train {
    private int economy_class;
    private int compartment;
    private int common;

    public Train(int economy_class, int compartment, int common){
        this.economy_class = economy_class;
        this.compartment = compartment;
        this.common = common;
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
