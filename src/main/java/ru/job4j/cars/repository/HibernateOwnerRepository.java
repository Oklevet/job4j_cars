package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.Collection;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateOwnerRepository implements OwnerRepository {

    CrudRepository crudRepository;

    @Override
    public Owner save(Owner owner) {
        return null;
    }

    @Override
    public Collection<Owner> findAll() {
        return null;
    }

    @Override
    public Optional<Owner> findById() {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean update(Owner owner) {
        return false;
    }
}
