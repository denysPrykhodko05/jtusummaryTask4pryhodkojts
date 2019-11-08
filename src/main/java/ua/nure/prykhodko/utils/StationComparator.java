package ua.nure.prykhodko.utils;

import ua.nure.prykhodko.entity.Station;

import java.util.Comparator;

public class StationComparator implements Comparator<Station> {
    @Override
    public int compare(Station o1, Station o2) {
        if(o1.getDepart_time()==null){
            return 1;
        }
        if(o2.getDepart_time()==null){
            return -1;
        }

        if (o1.getDepart_time().getTime()>o2.getDepart_time().getTime()){
            return 1;
        }else {
            return -1;
        }
    }
}
