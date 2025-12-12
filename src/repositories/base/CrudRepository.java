package repositories.base;

import entities.BaseEntity;

import java.util.List;

public interface CrudRepository {
    void save(BaseEntity baseEntity);

    BaseEntity findById(Integer id);

    void deleteById(Integer id);

    List<String> findAll();
}
