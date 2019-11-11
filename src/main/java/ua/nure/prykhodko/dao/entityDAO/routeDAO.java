package ua.nure.prykhodko.dao.entityDAO;

import ua.nure.prykhodko.entity.Train;

import java.util.List;

public interface routeDAO {
    List<Train> getAvailableTrain();
}
