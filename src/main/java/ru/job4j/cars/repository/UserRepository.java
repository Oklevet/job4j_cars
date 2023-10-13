package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private Session session;

    private final SessionFactory sf;

    public UserRepository(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        try {
            session = sf.openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        try {
            session = sf.openSession();
            session.beginTransaction();
            User user = new User();
            user.setId(userId);
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        try {
            session = sf.openSession();
            session.beginTransaction();
            List<User> result =
                    session.createQuery("from user order by id", User.class).list();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return null;
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        try {
            session = sf.openSession();
            session.beginTransaction();
            User result = session.get(User.class, userId);
            session.getTransaction().commit();
            session.close();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return Optional.empty();
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        try {
            session = sf.openSession();
            session.beginTransaction();
            List<User> result =
                    session.createQuery("from user where login like :login", User.class)
                            .setParameter("login", key).list();
            session.getTransaction().commit();
            session.close();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return null;
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        try {
            session = sf.openSession();
            session.beginTransaction();
            User result = session.createQuery("from user where login = :login", User.class)
                    .setParameter("login", login).uniqueResult();
            session.getTransaction().commit();
            session.close();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return Optional.empty();
    }
}