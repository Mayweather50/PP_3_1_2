package springboot.Project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springboot.Project.model.User;


import java.util.List;

@Component
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void removeUser(long id) {
        entityManager.remove(getUserById(id));
    }



    @Override
    @Transactional
    public void updateUser(User user,Long id) {
        User person = getUserById(id);
        person.setName(user.getName());
        person.setSurname(user.getSurname());
        person.setAge(user.getAge());
        entityManager.merge(person);
    }
}
