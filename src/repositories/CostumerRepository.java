package repositories;

import entities.Costumer;
import repositories.base.CrudRepository;

public interface CostumerRepository extends CrudRepository {
    Costumer findByPhoneNumber(String phoneNumber);
}
