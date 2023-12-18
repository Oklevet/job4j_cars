package ru.job4j.cars.repository;

import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Collection<Post> findAll();

    Optional<Post> findById(int id);

    boolean deleteById(int id);

    boolean update(Post post);

    Collection<Post> findAllByDay(LocalDate date);

    Collection<Post> findAllWithPhoto(int fileId);

    Collection<Post> findAllByBrand(Brand brand);
}
