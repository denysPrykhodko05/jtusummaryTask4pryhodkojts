package ua.nure.prykhodko.constants;

public class Fields {
    public static final String ENTITY_ID = "id";
    public static final String ENTITY_NAME = "name";

    // User fields
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE_ID = "role";
    public static final String USER_EMAIL = "email";
    public static final String USER_COUNT = "count";
    public static final String USER_TICKET_ID = "ticket_id";

    //Station fields
    public static final String STATION_ARRIVE_TIME = "arrive_time";
    public static final String STATION_DEPART_TIME = "depart_time";

    //Station_route fields
    public static final String STATION_ROUTE_STATION_ID = "station_id";

    //Train fields
    public static final String TRAIN_AMOUNT_OF_ECONOMY_CLASS = "amount_of_economy_class";
    public static final String TRAIN_AMOUNT_OF_COMMON = "amount_of_common";
    public static final String TRAIN_AMOUNT_OF_COMPARTMENT = "amount_of_compartment";
    public static final String TRAIN_BOUGHT_PLACES_ECONOMY = "bought_places_economy";
    public static final String TRAIN_BOUGHT_PLACES_COMPARTMENT = "bought_places_compartment";
    public static final String TRAIN_BOUGHT_PLACES_COMMON = "bought_places_common";

    //Route fields
    public static final String ROUTE_TRAIN = "train";
    public static final String ROUTE_ID = "route_id";


    //Sold tickets fields
    public static final String SOLD_TICKETS_CARRIAGE_NUMBER = "carriage_number";
    public static final String SOLD_TICKETS_PLACE = "place";
    public static final String SOLD_TICKETS_START_STATION = "start_station";
    public static final String SOLD_TICKETS_FINAL_STATION = "final_station";
    public static final String SOLD_TICKETS_CARRIAGE = "carriage";
    public static final String SOLD_TICKETS_DATE = "date";
    public static final String SOLD_TICKETS_TRAIN_ID = "train_id";

}
