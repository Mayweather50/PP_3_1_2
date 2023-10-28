package springboot.Project.service;



import springboot.Project.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers ();
    User getUserById(long id);
    void addUser(User user);
    void removeUser(long id);
    void updateUser(User user,Long id);
}
