package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernatePostRepository implements PostRepository {

    CrudRepository crudRepository;

    @Override
    public Post save(Post post) {
        return crudRepository.run(session -> session.save(post)) ? post : null;
    }

    @Override
    public Collection<Post> findAll() {
        return crudRepository.query("select distinct x from Post x JOIN FETCH x.car JOIN FETCH x.priceHistory "
                + "JOIN FETCH x.priceHistory "
                + "where x.user.id = :us_id", Post.class);
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional("from Post x where x.id = :id", Post.class, Map.of("id", id));
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.run(
                "delete Post as t where t.id = :id", Map.of("id", id));
    }

    @Override
    public boolean update(Post post) {
        return crudRepository.run(session -> session.merge(post));
    }

    @Override
    public Collection<Post> findAllByLastDay() {
        LocalDateTime date = LocalDateTime.now().minus(1, ChronoUnit.DAYS);
        return crudRepository.query("select distinct x from Post x JOIN FETCH x.car JOIN FETCH x.priceHistory "
                + "JOIN FETCH x.priceHistory "
                + "where x.created >= :date",
                Post.class, Map.of("date", date));
    }

    @Override
    public Collection<Post> findAllWithPhoto(int fileId) {
        return crudRepository.query("select distinct x from Post x JOIN FETCH x.car JOIN FETCH x.priceHistory "
                + "JOIN FETCH x.priceHistory "
                + "where x.fileId = :fileId", Post.class, Map.of("fileId", fileId));
    }

    @Override
    public Collection<Post> findAllByBrand(Brand brand) {
        return crudRepository.query("select distinct x from Post x JOIN FETCH x.car JOIN FETCH x.priceHistory "
                        + "JOIN FETCH x.priceHistory "
                        + "where x.brand = :brand",
                Post.class, Map.of("brand", brand));
    }
}
